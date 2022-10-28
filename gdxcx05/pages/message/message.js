// pages/demo02/demo02.js
const backgroundAudioManager = wx.getBackgroundAudioManager()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    userInfo : wx.getStorageSync('uinfo'),
    init: {
      messagelist: [{
        text: "文本1",
        time: "key1"
      }],
      status:""
    },
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    
    this.getinit();

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
    console.log("显示了");
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide() {
    // const backgroundAudioManager = wx.getBackgroundAudioManager()
    // backgroundAudioManager.stop();
    console.log("隐藏了");
    backgroundAudioManager.stop();
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

  },

  getinit(){
    let that = this;
    var user =  wx.getStorageSync('uinfo')
    // console.log(that.userInfo);
    wx.request({
      url: 'http://localhost:8001/Wxdata/getmessagebynickname?nickName='+user.nickName , //仅为示例，并非真实的接口地址
      success (res) {
        console.log(res.data)
        that.setData({
          init:res.data
        })
      }
    })
  }


})