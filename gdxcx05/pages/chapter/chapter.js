// pages/chapter/chapter.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    chapters:[],
    chapterIcon:"http://localhost:8001/files/图标/播放.png",
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    // console.log(options.courseId);
    let that = this;
    wx.request({
      url: 'http://localhost:8001/chapter/getlistbyid?courseId='+options.courseId, //仅为示例，并非真实的接口地址
      success (res) {
        console.log(res.data);
        console.log("该课程包括"+res.data);
        that.setData({
          chapters:res.data
        })
      }
    })
  },
  bfvideo(e){
    let videoUrl ="http://localhost:8081/api/" +e.currentTarget.dataset.videourl;
    console.log("你想播放"+videoUrl)
    if(e.target.dataset.videourl){
      videoUrl = "http://localhost:8081/api/"+ e.target.dataset.videourl;
      console.log("现在是"+videoUrl)
    }
    wx.navigateTo({
      url: "../video/video?videoUrl="+videoUrl
    })
  }
})