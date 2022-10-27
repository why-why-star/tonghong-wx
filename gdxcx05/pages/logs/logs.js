// logs.js
const util = require('../../utils/util.js')
const app = getApp();
Page({
  data: {
    userInfo: {}
  },
  onLoad() {
    console.log(app.globalData.userInfo.nickName)
    console.log(app.globalData.userInfo.avatarUrl)
    this.setData({
      userInfo:app.globalData.userInfo
    })
  }
})
