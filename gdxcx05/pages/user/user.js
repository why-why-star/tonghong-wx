// pages/user/user.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    userInfo : wx.getStorageSync('uinfo'),
    name:wx.getStorageSync('name'),
    class:0
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    console.log("我要加载个人信息")
    this.setData({
      userInfo:wx.getStorageSync('uinfo')
    });
    this.setData({
      name:wx.getStorageSync('name')
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow() {
    this.setData({
      class:wx.getStorageSync('class')
    })
     
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage() {

  }
})