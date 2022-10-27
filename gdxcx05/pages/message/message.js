// pages/demo02/demo02.js
const backgroundAudioManager = wx.getBackgroundAudioManager()
Page({
  
  /**
   * 页面的初始数据
   */
  data: {
    imgs:['http://localhost:8000/videos/wximages/ba.jpg','http://localhost:8000/videos/wximages/bc.jpg','http://localhost:8000/videos/wximages/bz.jpg']
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    // wx.getStorageSync({
    //   key: 'user',
    //   success (res) {
    //     console.log("-----------"+res.data.name+","+res.data.password)
    //   },
    //   fail(res){
    //     wx.reLaunch({
    //       url: '/pages/demo03/demo03',
    //     })
    //   }
    // })

  var user = wx.getStorageSync('user')
   if(user.name == '666' && user.password){
      console.log("-----------"+user.name+","+user.password)
    }

    console.log("直接执行了....");

   
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
   

    backgroundAudioManager.title = '此时此刻'
    backgroundAudioManager.epname = '此时此刻'
    backgroundAudioManager.singer = '许巍'
    backgroundAudioManager.coverImgUrl = 'http://y.gtimg.cn/music/photo_new/T002R300x300M000003rsKF44GyaSk.jpg?max_age=2592000'
    // 设置了 src 之后会自动播放
    backgroundAudioManager.src = 'http://localhost:8000/videos/haikuotiankong.mp3'

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

  }
})