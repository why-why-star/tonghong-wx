<template>
    <div class="container">
        <div class="content">
            <div style="text-align:center;margin-bottom: 30px;">用户登陆</div>
            <a-form
                :model="formState"
                name="basic"
                :label-col="{ span: 8 }"
                :wrapper-col="{ span: 16 }"
                autocomplete="off"
                @finish="onFinish"
                @finishFailed="onFinishFailed"
                >
                <a-form-item
                    label="用户名"
                    name="username"
                    :rules="[{ required: true, message: 'Please input your username!' }]"
                >
                    <a-input v-model:value="formState.username" />
                </a-form-item>
            
                <a-form-item
                    label="密码"
                    name="password"
                    :rules="[{ required: true, message: 'Please input your password!' }]"
                >
                    <a-input-password v-model:value="formState.password" />
                </a-form-item>
            
                <a-form-item name="remember" :wrapper-col="{ offset: 8, span: 16 }">
                    <a-checkbox v-model:checked="formState.remember">记住我</a-checkbox>
                </a-form-item>
            
                <a-form-item :wrapper-col="{ offset: 8, span: 16 }">
                    <a-button type="primary" html-type="submit">登陆</a-button>
                </a-form-item>
                </a-form>
        </div>
    </div>    
</template>
  <script setup>

    import { defineComponent, reactive  } from 'vue';
    // 导入路由器对象
    import {useRouter} from 'vue-router'
    let $router = useRouter()

      const formState = reactive({
        username: '',
        password: '',
        remember: true,
      });
  
      const onFinish = values => {
        console.log('Success:', values);

        // let url = "/"
        // axios.get(url,params).then(function(result){
        //     console.log(result.data)
        //     that.onClose();
        //     that.getData();

        //sessionStorage.setItem("key-yours",result.data)

        // }).catch(function (reason) {
        //     console.log(reason)
        // })


        $router.push("/layout");
      };
  
      const onFinishFailed = errorInfo => {
        console.log('Failed:', errorInfo);
      };
      
   
  </script>
  <style>

    .container{
        height:100vh;
        width:100vw;
        display:flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        background:#A9AEB1;
    }

    .content{
       border:1px solid gray;
       padding: 30px 20px;
       background:#fff;
    }

  </style>