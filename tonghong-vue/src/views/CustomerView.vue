<template>

    <a-table :columns="columns" :pagination="false" :data-source="data">
        <template #headerCell="{ column }">
            <template v-if="column.key === 'name'">
                <span>
                    <smile-outlined />
                    用户账号
                </span>
            </template>
        </template>

        <template #bodyCell="{ column, record }">
            <template v-if="column.key === 'name'">
                <a>
                    {{ record.name }}
                </a>
            </template>
            <template v-else-if="column.key === 'avatarUrl'">
                <span>
                    <img :src="record.avatarUrl" style="width:50px;height:50px" />
                </span>
            </template>
            <template v-else-if="column.key === 'action'">
                <span>
                    <a class="edit" @click="onEdit(record)">编辑</a>
                    <a-divider type="vertical" />
                    <a class="edit" @click="onDelete(record)">刪除</a>
                    <a-popover title="Title" trigger="click">
                        <template #content>
                            
                            <div style="overflow-y:scroll;overflow-x:auto ;height: 100px;"><p v-for="item in messages">{{ item.text }}<a-divider /></p></div>
                            <a-divider style="height: 2px; background-color: #7cb305" />


                            <a-form :model="message" @finish="onFinish">
                                <a-form-item name="text">
                                    <a-input v-model:value="message.text" placeholder="请输入要发送的内容" />
                                </a-form-item>
                                <a-form-item name="userid" hidden>
                                    <a-input v-bind:value="message.userid"/>
                                </a-form-item>
                                <a-form-item>
                                    <a-button type="primary" html-type="submit">发送</a-button>
                                </a-form-item>
                            </a-form>
                        </template>
                        <a-button @click="messagefun(record)">发送信息</a-button>
                    </a-popover>
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
                    <a-form-item label="密码" name="password">
                        <a-input v-model:value="form.password" placeholder="请输入密码" />
                    </a-form-item>
                </a-col>
            </a-row>
        </a-form>
        <template #extra>
            <a-space>
                <a-button @click="onClose">关闭</a-button>
                <a-button type="primary" @click="onSaveData">{{ tj }}</a-button>
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
            drowtitle: "修改",
            form: {
                nickname: '',
                avatarUrl: '',
                phone: '',
                password: '',
                name: '',
                password: ''
            },
            visible: false,
            data: null,
            pageIndex: 1,
            count: 2,
            pageSize: 2,
            columns: [{
                title: '用户名称',
                dataIndex: 'name',
                key: 'name',
            }, {
                title: '用户密码',
                dataIndex: 'password',
                key: 'password',
            }, {
                title: '用户手机号',
                dataIndex: 'phone',
                key: 'phone',
            }, {
                title: '用户昵称',
                dataIndex: 'nickname',
                key: 'nickname',
            }, {
                title: '头像',
                key: 'avatarUrl',
                dataIndex: 'avatarUrl',
            }, {
                title: '操作',
                key: 'action',
            }],
            fixid: 0,
            delid: 0,
            messageid: 0,
            tj: "保存修改",
            nowfun: 1,
            messages: [{ text: "记得交学费了" }, { text: "有新的课程加入哦" }],
            message: {
                text: "",
                userid: 0
            }

        };
    },

    mounted: function () {
        this.getData()
    },
    methods: {

        getData() {
            // `this` 在方法里指向当前 Vue 实例
            var that = this
            var url = "/api/Customer/getPage?pageIndex=" + that.pageIndex + "&pageSize=" + that.pageSize
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
                + "&avatarUrl=" + this.form.avatarUrl

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
        onDelete(record) {
            let that = this;
            this.delid = record.id
            this.$confirm({
                content: '确定要进行此删除操作么？',
                okText: "确定删除",
                onOk() {
                    console.log("你点击了删除")
                },
                cancelText: '取消',
                onCancel() {
                    return;
                },
            });

        },
        onFinish(values) {
            let that = this;
            values.userid = that.messageid;
            var url = "/api/Customer/message/add?text="
                + values.text + "&userid=" + values.userid
            console.log(url);
            if (values.text == "") {
                message.error("不能发送空内容");
                return
            }

            axios.get(url).then(function (result) {
                console.log(result.data)
                that.message = { text: "", userid: that.messageid }
                message.success("消息发送成功")
            }).catch(function (reason) {
                console.log(reason)
                message.error("消息发送失败")
            })
            that.messageupdata();
            console.log('Success:', values);
        },
        messagefun(record) {
            let that = this;
            that.messageid = record.id
            console.log("你要给" + record.id + "发送消息");
            that.messageupdata();
            console.log("我保存的" + that.messages)
        },
        messageupdata() {
            let that = this;
            var url = "/api/Customer/message/get?&userid=" + that.messageid;
            axios.get(url).then(function (result) {

                console.log(result.data)
                that.messages = result.data;
            }).catch(function (reason) {
                console.log(reason)
            })
        }
    }

});
</script>