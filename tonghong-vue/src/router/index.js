import { createRouter, createWebHistory } from 'vue-router'


const routes = [
  {
    path: '/layout',
    name: 'layout',
    component: () => import('../layout/Layout.vue'),
    redirect: '/home',
    children:[
      {
        path: '/home',
        name: 'home',
        component: ()=>import("../views/HomeView.vue")
      },
      {
        path: '/teacher',
        name: 'teacher',
        component: () => import('../views/TeacherView.vue')
      },
      {
        path: '/customer',
        name: 'customer',
        component: () => import('../views/CustomerView.vue')
      },
      {
        path: '/course',
        name: 'course',
        component: () => import('../views/CourseView.vue')
      },
      {
        path: '/chapter',
        name: 'chapter',
        component: () => import('../views/ChapterView.vue')
      }
    ]
  },
  {
    path: '/',
    name: 'login',
    component: () => import('../views/LoginView.vue')
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})



// 全局前置守卫：初始化时执行、每次路由切换前执行
// router.beforeEach((to,from,next)=>{

//       //sessionStorage.getItem('key名称') 获取存储在本地的权限变量
//       if(sessionStorage.getItem('key-yours')){  //权限控制的具体规则
//           // 在请求头中添加token
//           const AUTH_TOKEN = sessionStorage.getItem('key-yours');
//           // 创建实例时设置配置的默认值
//           var instance = axios.create({
//             //baseURL: 'https://localhost:8001/'
//           });
//           // 在实例已创建后修改默认值
//           instance.defaults.headers.common['Authorization'] = AUTH_TOKEN;
//       }else{
//           // alert('暂无权限查看');
//           //router.push("/")
//       }
    
// })




export default router
