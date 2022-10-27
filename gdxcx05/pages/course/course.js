// pages/course/course.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    init:{
      kc:[]
    }
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    let that = this;
    wx.request({
      url: 'http://localhost:8001/Wxdata/course', //仅为示例，并非真实的接口地址
      success (res) {
        console.log(res.data)
        that.setData({
          init:res.data
        })
      }
    })
  },
  showchapter(e){
    console.log(e);
    let courseId = e.currentTarget.dataset.courseid;
    if(e.target.dataset.courseid){
      courseId = e.target.dataset.courseid;
    }
    console.log(courseId);
    wx.navigateTo({
      url: '/pages/chapter/chapter?courseId='+courseId,
    })
  }
})