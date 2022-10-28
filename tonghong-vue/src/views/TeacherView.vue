<template>

    <a-button type="primary" @click="onAdd" style="display:flex;justify-content: flex-start;">
        <template #icon>
            <PlusOutlined />
        </template>
        添加教师
    </a-button>

    <a-table :columns="columns" :pagination="false" :data-source="data">
        <template #headerCell="{ column }">
            <template v-if="column.key === 'name'">
                <span>
                    <smile-outlined />
                    教师名称
                </span>
            </template>
        </template>

        <template #bodyCell="{ column, record }">
            <template v-if="column.key === 'name'">
                <a>
                    {{ record.name }}
                </a>
            </template>
            <template v-else-if="column.key === 'photo'">
                <span>
                    <img :src="'/api/' + record.photo" style="width:150px;height:50px" />
                </span>
            </template>
            <template v-else-if="column.key === 'action'">
                <span>
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

    <a-drawer :title="drowtitle" :width="400" :visible="visible" :body-style="{ paddingBottom: '80px' }"
        :footer-style="{ textAlign: 'right' }" @close="onClose">
        <a-form :model="form" :rules="rules" layout="vertical">
            <a-row :gutter="16">
                <a-col :span="24">
                    <a-form-item label="教师名称" name="name">
                        <a-input v-model:value="form.name" placeholder="请输入教师名称" />
                    </a-form-item>
                </a-col>
            </a-row>
            <a-row :gutter="16">
                <a-col :span="24">
                    <a-form-item label="教师手机号" name="phone">
                        <a-input v-model:value="form.phone" placeholder="请输入手机号" />
                    </a-form-item>
                </a-col>
            </a-row>
            <a-row :gutter="16">
                <a-col :span="24">
                    <a-form-item label="邮箱" name="mail">
                        <a-input v-model:value="form.mail" placeholder="请输入教师邮箱" />
                    </a-form-item>
                </a-col>
            </a-row>
            <a-row :gutter="16">
                <a-col :span="24">
                    <a-form-item label="等级" name="level">
                        <a-input v-model:value="form.level" placeholder="请输入教师等级" />
                    </a-form-item>
                </a-col>
            </a-row>
            <a-row :gutter="24">
                <a-col :span="12">
                    <a-form-item label="照片" name="photo">

                        <a-upload v-model:file-list="fileList" name="photo" action="/api/teacher/upload"
                            :headers="headers" @change="handleChange">
                            <a-button>
                                <upload-outlined></upload-outlined>
                                上传照片
                            </a-button>
                        </a-upload>

                    </a-form-item>
                </a-col>
            </a-row>
        </a-form>
        <template #extra>
            <a-space>
                <a-button @click="onClose">关闭</a-button>
                <a-button type="primary" @click="onSaveDataOrFix">{{ tj }}</a-button>
            </a-space>
        </template>
    </a-drawer>



</template>
<script>
import axios from 'axios';
import { message } from 'ant-design-vue';
import { UploadOutlined, PlusOutlined } from '@ant-design/icons-vue';
import $ from 'jquery'
import { defineComponent, reactive, ref } from 'vue';
export default ({
    data() {
        return {
            drowtitle: "添加教师",
            fileList: [],
            teachers: ["张老师", "黄老师", "李老师"],
            form: {
                name: '',
                phone: '',
                mail: '',
                level: '',
                photo: ''
            },
            visible: false,
            data: null,
            pageIndex: 1,
            count: 2,
            pageSize: 2,
            columns: [{
                title: '教师名称',
                dataIndex: 'name',
                key: 'name',
            }, {
                title: '教师手机号',
                dataIndex: 'phone',
                key: 'phone',
            }, {
                title: '邮箱',
                dataIndex: 'mail',
                key: 'mail',
            }, {
                title: '等级',
                key: 'level',
                dataIndex: 'level',
            }, {
                title: '照片',
                key: 'photo',
                dataIndex: 'photo',
            }, {
                title: '操作',
                key: 'action',
            }],
            fixid: "",
            tj: "提交",
            nowfun: 1
        };
    },

    mounted: function () {
        this.getData()
    },
    methods: {

        getData() {
            $("#bc").hide();
            // `this` 在方法里指向当前 Vue 实例
            var that = this
            var url = "/api/teacher/getPage?pageIndex=" + that.pageIndex + "&pageSize=" + that.pageSize
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
            var url = "/api/teacher/add?name="
                + this.form.name + "&phone=" + this.form.phone
                + "&mail=" + this.form.mail + "&level=" + this.form.level
                + "&photo=" + this.form.photo

            console.log(url);

            var params = this.form
            console.log("我传递的教师数据是" + params.name)
            axios.get(url, params).then(function (result) {
                console.log(result.data)
                that.onClose();
                that.getData();
            }).catch(function (reason) {
                console.log(reason)
            })
        },
        //当点击修改数据时
        onFixData() {
            let that = this;
            console.log("+", this.form.id);
            var url = "/api/teacher/update?name="
                + this.form.name + "&phone=" + this.form.phone
                + "&mail=" + this.form.mail + "&level=" + this.form.level
                + "&photo=" + this.form.photo + "&id=" + this.fixid

            console.log(url);

            var params = this.form
            console.log("我传递的教师数据是" + params.name)
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
                this.form.photo = info.file.response.data;

                console.log(this.form);
            } else if (info.file.status === 'error') {
                message.error(`${info.file.name} file upload failed.`);
            }
        },

        //编辑
        onEdit(record) {

            this.fixid = record.id;
            console.log(record.id);
            this.drowtitle = "修改"
            this.tj = "保存修改"
            this.form = record;
            this.nowfun = 2
            this.showDrawer();
        },
        onAdd() {

            this.drowtitle = "添加教师",
                this.tj = "提交",
                this.nowfun = 1
            this.showDrawer();
        },

        onSaveDataOrFix() {
            if (this.nowfun == 1) {
                this.onSaveData()
            } else {
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
                    var url = "/api/teacher/delete?id=" + record.id;
                    console.log(url);
                    console.log("我删除的教师信息是" + record.id)
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