<template>

  <a-button type="primary" @click="onAdd" style="display:flex;justify-content: flex-start;">
    <template #icon>
      <PlusOutlined />
    </template>
    添加课程
  </a-button>

  <a-table :columns="columns" :pagination="false" :data-source="data">
    <template #headerCell="{ column }">
      <template v-if="column.key === 'name'">
        <span>
          <smile-outlined />
          课程名称
        </span>
      </template>
    </template>

    <template #bodyCell="{ column, record }">
      <template v-if="column.key === 'name'">
        <a>
          {{ record.name }}
        </a>
      </template>
      <template v-else-if="column.key === 'faceImg'">
        <span>
          <img :src="'/api/' + record.faceImg" style="width:150px;height:50px" />
        </span>
      </template>
      <template v-else-if="column.key === 'action'">
        <span>
          <a>进入章节</a>
          <a-divider type="vertical" />
          <a class="edit" @click="onEdit(record)">编辑</a>
          <a-divider type="vertical" />
          <a class="edit" @click="onDelete(record)">刪除</a>
        </span>
      </template>
    </template>
  </a-table>
  <a-pagination v-model:current="pageIndex" :pageSize="pageSize" :total="count" @change="changePageIndex"
    show-less-items style="text-align:right;margin-top:10px;" />


  <!-- 添加抽屉 -->

  <a-drawer :title="title" :width="400" :visible="visible" :body-style="{ paddingBottom: '80px' }"
    :footer-style="{ textAlign: 'right' }" @close="onClose">
    <a-form :model="form" :rules="rules" layout="vertical">
      <a-row :gutter="16">
        <a-col :span="24">
          <a-form-item label="课程名称" name="name">
            <a-input v-model:value="form.name" placeholder="请输入课程名称" />
          </a-form-item>
        </a-col>
      </a-row>
      <a-row :gutter="16">
        <a-col :span="24">
          <a-form-item label="主讲老师" name="teacherId">
            <a-select v-model:value="form.teacherId" placeholder="请选择主讲老师">
              <a-select-option v-for="item in teachers" :value="item[0]" :key="item[0]">{{ item[1] }}→{{ item[0] }}
              </a-select-option>
            </a-select>
          </a-form-item>
        </a-col>
      </a-row>
      <a-row :gutter="16">
        <a-col :span="24">
          <a-form-item label="课程类型" name="attribute">
            <a-select v-model:value="form.attribute" placeholder="请选择课程类型">
              <a-select-option value="1">公开课</a-select-option>
              <a-select-option value="2">标准课</a-select-option>
            </a-select>
          </a-form-item>
        </a-col>
      </a-row>
      <a-row :gutter="16">
        <a-col :span="24">
          <a-form-item label="课时长度" name="ks">
            <a-input v-model:value="form.ks" placeholder="请输入课时长度" />
          </a-form-item>
        </a-col>
      </a-row>
      <a-row :gutter="24">
        <a-col :span="12">
          <a-form-item label="封面" name="faceImg">

            <a-upload v-model:file-list="fileList" name="faceImg" action="/api/course/upload" :headers="headers"
              @change="handleChange">
              <a-button>
                <upload-outlined></upload-outlined>
                上传封面
              </a-button>
            </a-upload>

          </a-form-item>
        </a-col>
      </a-row>
    </a-form>
    <template #extra>
      <a-space>
        <a-button @click="onClose">关闭</a-button>
        <a-button type="primary" @click="onSaveDataOFix">{{ tj }}</a-button>
      </a-space>
    </template>
  </a-drawer>

</template>
<script>
import axios from 'axios';
import { message } from 'ant-design-vue';
import { UploadOutlined, PlusOutlined } from '@ant-design/icons-vue';
// import { defineComponent, reactive, ref } from 'vue';
// import { array } from 'vue-types';
export default ({
  data() {
    return {
      fileList: [],
      teachers: "",
      form: {
        name: '',
        teacherId: '',
        attribute: '',
        ks: '',
        faceImg: ''
      },
      visible: false,
      data: null,
      pageIndex: 1,
      count: 2,
      pageSize: 2,
      columns: [{
        title: '课程名称',
        dataIndex: 'name',
        key: 'name',
      }, {
        title: '讲师',
        dataIndex: 'teacherName',
        key: 'teacherName',
      }, {
        title: '类别',
        dataIndex: 'attribute',
        key: 'attribute',
      }, {
        title: '封面',
        key: 'faceImg',
        dataIndex: 'faceImg',
      }, {
        title: '课时',
        key: 'ks',
        dataIndex: 'ks',
      }, {
        title: '操作',
        key: 'action',
      }],
      fixid: "",
      delid: "",
      title: "添加课程",
      tj: "添加",
      nowfun: 1
    };
  },
  mounted: function () {
    this.getData()
  },
  methods: {

    getData() {
      // `this` 在方法里指向当前 Vue 实例
      var that = this
      var url = "/api/course/getPage?pageIndex=" + that.pageIndex + "&pageSize=" + that.pageSize
      //var params = "pageIndex="+that.pageIndex+"&pageSize="+that.pageSize
      var params = "pageIndex=1&pageSize=2"
      axios.get(url).then(function (result) {
        console.log(result.data)
        that.data = result.data.data
        that.pageIndex = result.data.pageIndex
        that.count = result.data.totalRecord
        console.log(that.count, that.pageIndex, "-------------------------");
      }).catch(function (reason) {
        console.log(reason)
      })

      var url = "/api/teacher/getlist"
      axios.get(url).then(function (result) {

        var teacherlist = new Array()

        for (var i = 0; i < result.data.length; i++) {
          console.log(i)
          var temp = new Array();
          temp.push(result.data[i].id);
          temp.push(result.data[i].name);
          teacherlist.push(temp)
        }
        that.teachers = teacherlist
        console.log(teacherlist)

        console.log(result.data)
      }).catch(function (reason) {
        console.log(reason)
      })

    },

    changePageIndex() {
      this.getData()
    },
    //显示抽屉
    showDrawer() {
      this.visible = true;
    },
    //隐藏抽屉
    onClose() {
      this.visible = false;
    },
    //当点击保存数据时
    onSaveData() {
      let that = this;
      console.log(this.form);
      var url = "/api/course/add?name="
        + this.form.name + "&teacherId=" + this.form.teacherId
        + "&attribute=" + this.form.attribute + "&ks=" + this.form.ks
        + "&faceImg=" + this.form.faceImg

      console.log(url);

      var params = this.form
      axios.get(url, params).then(function (result) {
        console.log(result.data)
        that.onClose();
        that.getData();
      }).catch(function (reason) {
        console.log(reason)
      })
    },
    onFixData() {
      var tempattribute = 1
      if (this.form.attribute == "公开课") {
        tempattribute = 1
      }
      else {
        tempattribute = 2
      }
      let that = this;
      console.log("+", this.form.id);
      var url = "/api/course/update?name="
        + this.form.name + "&teacherId=" + this.form.teacherId
        + "&attribute=" + tempattribute + "&ks=" + this.form.ks
        + "&faceImg=" + this.form.faceImg + "&id=" + this.fixid

      console.log(url);

      var params = this.form
      console.log("我传递的课程数据是" + params.name)
      axios.get(url, params).then(function (result) {
        console.log(result.data)
        that.onClose();
        that.getData();
      }).catch(function (reason) {
        console.log(reason)
      })
    },

    // 上传文件
    handleChange(info) {

      if (info.file.status !== 'uploading') {
        console.log(info.file, info.fileList);
      }

      if (info.file.status === 'done') {
        message.success(`${info.file.name} file uploaded successfully`);
        console.log('上传文件', info.file.response.data)
        this.form.faceImg = info.file.response.data;

        console.log(this.form);
      } else if (info.file.status === 'error') {
        message.error(`${info.file.name} file upload failed.`);
      }
    },

    //编辑
    onEdit(record) {
      console.log(record.id);
      this.form = record;
      this.fixid = record.id;
      this.nowfun = 2,
        this.title = "修改",
        this.tj = "保存修改",
        this.showDrawer();
    },
    onAdd() {
      this.nowfun = 1,
        this.title = "添加课程",
        this.tj = "提交",
        this.showDrawer();
    },


    onSaveDataOFix() {
      if (this.nowfun == 1) {
        console.log("你现在想添加数据")
        this.onSaveData()
      } else {
        console.log("你现在想修改数据")
        this.onFixData()
      }
    },
    onDelete(record) {
      let that = this;
      this.delid = record.id
      this.$confirm({
        content: '确定要进行此删除操作么？',
        okText: "确定删除",
        onOk() {
          var url = "/api/course/delete?id=" + record.id;
            console.log(url);
            console.log("我删除的课程是" + record.id)
            axios.get(url).then(function (result) {
              message.success('删除成功');
              console.log(result)
              that.getData()
            }).catch(function (reason) {
              console.log(reason)
            })
        },
        cancelText: '取消',
        onCancel() {
          return;
        },
      });
    
    }

  }

});
</script>