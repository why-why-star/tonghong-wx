// pages/login/login.js
Page({

  /**
   * 
   */
  data: {
    token: "",
    name: wx.getStorageSync('name'),
    password: wx.getStorageSync('password'),
    phone: wx.getStorageSync('phone'),
    userInfo: {}
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    console.log("开始运行，在register")
    //有token还在这个界面，初次使用或者清除数据。等待操作
  },
  formSubmit(e) {
    console.log(e.detail.value)
    wx.setStorageSync('name', e.detail.value['name']);
    wx.setStorageSync('password', e.detail.value['password']);

    var userInfo = wx.getStorageSync('uinfo');
    wx.request({
      url: 'http://localhost:8001/login',
      data: {
        nickname: userInfo.nickName,
        name: e.detail.value['name'],
        password: e.detail.value['password']
      },
      success: res => {
        console.log("创建tooken成功并返回数据")
        console.log(res.data);
        if (res.data) {
          console.log("登录成功")
          wx.switchTab({
            url: '/pages/index/index',
          })
        }
        else{
          console.log('登录失败')
          this.password = "";
          wx.setStorageSync('password', "");
        }
        var token = wx.getStorageSync('auth-code');
        // if (typeof (res.data) == "undefined" || res.data == "") {
        //   console.log('登录失败')
        // } else {
        //   wx.navigateTo({
        //     url: '/pages/index/index?token=' + token,
        //   })
        // }

      }
    })
  },






})