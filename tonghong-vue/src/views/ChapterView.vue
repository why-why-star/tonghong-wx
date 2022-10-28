<template>


  <a-button type="primary" @click="onAdd" style="display:flex;justify-content: flex-start;">
    <template #icon>
      <PlusOutlined />
    </template>
    添加章节
  </a-button>


  <a-row :gutter="16">
    <a-col :span="24">
      <a-form-item label="选择课程">
        <a-select v-model:value="nowcourseId" placeholder="请选择课程" @change="changeCourse($event)">
          <a-select-option v-for="item in courselist" :value="item.id" :key="item.id">{{ item.name }}→{{ item.id }}
          </a-select-option>
        </a-select>
      </a-form-item>
    </a-col>
  </a-row>

  <a-table :columns="columns" :pagination="false" :data-source="data">
    <template #headerCell="{ column }">
      <template v-if="column.key === 'name'">
        <span>
          <smile-outlined />
          章节名称
        </span>
      </template>
    </template>






    <template #bodyCell="{ column, record }">
      <template v-if="column.key === 'name'">
        <a>
          {{ record.name }}
        </a>
      </template>
      <template v-else-if="column.key === 'video'">
        <span>
          <a :href="'/api/' + record.video" target="_blank">{{ record.video }}</a>
          <!-- <img :src="'/api/' + record.video" style="width:150px;height:50px" /> -->
        </span>
      </template>
      <template v-else-if="column.key === 'action'">
        <span>
          <a>查看</a>
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
          <a-form-item label="章节名称" name="name">
            <a-input v-model:value="form.name" placeholder="请输入章节名称" />
          </a-form-item>
        </a-col>
      </a-row>
      <a-row :gutter="16">
        <a-col :span="24">
          <a-form-item label="内容描述" name="info">
            <a-input v-model:value="form.info" placeholder="内容描述" />
          </a-form-item>
        </a-col>
      </a-row>
      <a-row :gutter="24">
        <a-col :span="12">
          <a-form-item label="内容" name="video">
            <a-upload v-model:file-list="fileList" name="video" action="/api/chapter/upload" :headers="headers"
              @change="handleChange">
              <a-button>
                <upload-outlined></upload-outlined>
                上传内容
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
import { defineComponent, reactive, ref } from 'vue';

export default ({
  data() {
    return {
      fileList: [],
      courselist: [],
      nowcourseId: 0,
      nowcoursename: "太辣了",
      form: {
        name: '',
        info: '',
        video: ''
      },
      visible: false,
      data: null,
      pageIndex: 1,
      count: 2,
      pageSize: 2,
      columns: [{
        title: '章节名称',
        dataIndex: 'name',
        key: 'name',
      }, {
        title: '章节内容',
        dataIndex: 'info',
        key: 'info',
      }, {
        title: '查看视频',
        dataIndex: 'video',
        key: 'video',
      }, {
        title: '操作',
        key: 'action',
      }],
      fixid: "",
      delid: "",
      title: "添加章节",
      tj: "提交",
      nowfun: 1,
      nowchapterid: 0
    };
  },
  watch: {
    // 每当 question 改变时，这个函数就会执行
    question(newQuestion, oldQuestion) {
      if (newQuestion.includes('?')) {
        this.getAnswer()
      }
    }
  },
  mounted: function () {
    this.turnpage();
    this.getData()
  },
  methods: {
    turnpage() {
      var that = this;
      var url = "/api/chapter/getPage?pageIndex=" + that.pageIndex + "&pageSize=" + that.pageSize + "&courseId=" + that.nowcourseId + "&chapterName=" + that.nowcoursename
      axios.get(url).then(function (result) {
        console.log(result.data)
        that.data = result.data.data
        that.pageIndex = result.data.pageIndex
        that.count = result.data.totalRecord
        console.log(that.count, that.pageIndex, "-------------------------");
      }).catch(function (reason) {
        console.log(reason)
      })
    },
    getData() {
      // `this` 在方法里指向当前 Vue 实例
      var that = this
      var url = "/api/course/getlist"
      axios.get(url).then(function (result) {
        console.log(result.data)
        that.courselist = result.data
      }).catch(function (reason) {
        console.log(reason)
      })
    },

    changePageIndex() {
      this.turnpage()
    },
    //显示抽屉
    showDrawer() {
      if (this.nowcourseId == 0) {
        message.error('请先选择课程');
        return;
      }
      this.visible = true;
    },
    //隐藏抽屉
    onClose() {
      this.visible = false;
    },
    //当点击保存数据时
    onSaveData() {
      let that = this;
      console.log("我要上传的数据" + this.form);
      var url = "/api/chapter/add?name="
        + this.form.name + "&courseId=" + this.nowcourseId + "&info=" + this.form.info
        + "&video=" + this.form.video
      console.log(url);
      var params = this.form
      axios.get(url).then(function (result) {
        console.log(result.data)
        that.onClose();
        that.turnpage();
      }).catch(function (reason) {
        console.log(reason)
      })
      this.turnpage()
    },


    onFixData() {
      let that = this;
      var url = "/api/chapter/update?id=" + this.nowchapterid
        + "&name="
        + this.form.name
        + "&info=" + this.form.info
        + "&video="

      console.log(url);

      var params = this.form
      console.log("我传递的章节数据是" + params.name)
      axios.get(url, params).then(function (result) {
        console.log(result.data)
        that.onClose();
        that.getData();
      }).catch(function (reason) {
        console.log(reason)
      })
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


    // 上传文件
    handleChange(info) {

      console.log("文件信息" + info)
      if (info.file.status !== 'uploading') {
        console.log(info.file, info.fileList);
      }

      if (info.file.status === 'done') {
        message.success(`${info.file.name} file uploaded successfully`);
        console.log('上传文件', info.file.response.data)
        this.form.video = info.file.response.data;

        console.log(this.form);
      } else if (info.file.status === 'error') {
        message.error(`${info.file.name} file upload failed.`);
      }
    },

    //编辑
    onEdit(record) {
      this.nowfun = 2;
      this.title = "修改";
      this.tj = "保存修改";
      console.log(record.id);
      this.nowchapterid = record.id;
      this.form = record;
      this.showDrawer();
    },


    onAdd() {
      this.nowfun = 1;
      this.title = "添加课程";
      this.tj = "提交";
      this.showDrawer();
    },

    changeCourse(event) {
      var that = this
      console.log(this.courselist)
      console.log("当前课程id:" + event);
      that.nowcourseId = event
      console.log(that.nowcoursename)
      for (var i = 0; i < that.courselist.length; i++) {
        if (event == that.courselist[i].id) {
          console.log("到了" + that.courselist[i].name);
          that.nowcoursename = that.courselist[i].name
          console.log(that.nowcoursename)
          console.log("到了");
        }

      }
      console.log("当前课程id:" + event);
      console.log(that.nowcoursename)

      var url = "/api/chapter/getPage?pageIndex=" + that.pageIndex + "&pageSize=" + that.pageSize + "&courseId=" + event + "&chapterName=" + that.nowcoursename
      axios.get(url).then(function (result) {
        console.log(result.data)
        that.data = result.data.data
        that.pageIndex = result.data.pageIndex
        that.count = result.data.totalRecord
        console.log(that.count, that.pageIndex, "-------------------------");
      }).catch(function (reason) {
        console.log(reason)
      })

    },
    onDelete(record) {
      let that = this;
      this.delid = record.id
      this.$confirm({
        content: '确定要进行此删除操作么？',
        okText: "确定删除",
        onOk() {
          var url = "/api/chapter/delete?id=" + record.id;
          console.log(url);
          console.log("我删除的章节是" + record.id)
          axios.get(url).then(function (result) {
            message.success('删除成功');
            console.log(result);
            console.log("删除完了,一个刷新")
            that.turnpage();
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