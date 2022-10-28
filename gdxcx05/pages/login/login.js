let app = getApp();
// pages/login/login.js
Page({

  /**
   * 页面的初始数据
   */
  data: {

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    console.log("开始运行，在login")
    var token = wx.getStorageSync('auth-code');
    if (typeof (token) == "undefined" || token == "") {
      console.log('没有token或token被清除，请先使用微信登录')
    } else {
      console.log("有token");
      console.log("token→" + token);
      //验证token（）
      wx.request({
        url: 'http://localhost:8001/checktoken?token=' + token,
        success: res => {
          console.log("http://localhost:8001/checktoken?token=" + token)
          console.log("我要开始解析了")
          console.log(res.data);
          if (res.data == false) {
            //解析失败
            console.log("解析失败");
          } else {
            console.log(typeof (name));
            console.log(name);
            console.log(typeof (password));
            console.log(password);
            
            var name = wx.getStorageSync('name');
            var password = wx.getStorageSync('password');
            if (name == undefined || name == "" || password == undefined || password == "") {
              //未保存有这些信息，第一次使用或者被清除，跳转云课堂登录
              console.log("未保存有所需信息，跳转登录");
            }
            //有token，有邮箱和密码，直接登录进入主界面
            console.log()
            wx.request({
              url: 'http://localhost:8001/login',
              data: {
                nickname: wx.getStorageSync('uinfo').nickName,
                name: wx.getStorageSync('name'),
                password: wx.getStorageSync('password'),
              },
              success: res => {
                console.log("创建tooken成功并返回数据")
                console.log(res.data);
                if (res.data) {
                  console.log("登录成功")
                  wx.setStorageSync('name', name);
                  wx.switchTab({
                    url: '/pages/index/index',
                  })
                }
                else{
                  console.log("登录信息↓")
                  console.log("登录信息:"+wx.getStorageSync('name'))
                  console.log("登录信息:"+wx.getStorageSync('password'))
                  console.log("登录信息:"+wx.getStorageSync('uinfo').nickName)
                  

                  console.log('登录失败')
                  this.password = "";
                  // wx.setStorageSync('password', "");
                  wx.navigateTo ({
                    url: '/pages/register/register',
                  })
                }
              }
            })

          }
        }
      })

    }
  },
  toIndex() {
    wx.navigateTo({
      url: '/pages/index/index',
    })
  },
  getUserProfile() {
    wx.getUserProfile({
      desc: '用于完善会员资料', // 声明获取用户个人信息后的用途，后续会展示在弹窗中，请谨慎填写
      success: (res) => {
        console.log('用户数据→' + res);
        wx.setStorageSync('uinfo', res.userInfo);
        this.login(res.userInfo);
        this.setData({
          userInfo: res.userInfo
        })
      }
    })
  },

  login(uinfo) {
    wx.login({
      success(res) {
        if (res.code) {
          var uinfo = wx.getStorageSync('uinfo')
          console.log(uinfo);
          console.log(uinfo.nickName);

          console.log(res.code);
          console.log(uinfo.nickName);
          console.log(uinfo.avatarUrl);
          
          //发起网络请求
          wx.request({
            url: 'http://localhost:8001/createOpenKey',
            data: {
              code: res.code,
              nickname: uinfo.nickName,
              avatarUrl: uinfo.avatarUrl
            },
            success: res => {
              console.log("创建tooken成功并返回数据")
              console.log(res.data);
              app.globalData.userInfo = uinfo;
              console.log(app.globalData.userInfo);
              wx.setStorageSync('auth-code', res.data);
              console.log("已存储的数据");
              wx.setStorageSync('name', "");
              wx.setStorageSync('password', "");
              // // var token =  wx.getStorageSync('auth-code')
              // // console.log(token);
              wx.navigateTo({
                url: '/pages/register/register',
              })
            }
          })
        } else {
          console.log('登录失败！' + res.errMsg)
        }
      }
    })
  }
})