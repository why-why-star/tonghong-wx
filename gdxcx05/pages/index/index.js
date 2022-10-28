// pages/demo02/demo02.js
const backgroundAudioManager = wx.getBackgroundAudioManager()
Page({
  /**
   * 页面的初始数据
   */
  data: {
    imgs: ['http://localhost:8001/files/%E9%A6%96%E9%A1%B5%E8%BD%AE%E6%92%AD%E5%9B%BE/1.png', 'http://localhost:8001/files/%E9%A6%96%E9%A1%B5%E8%BD%AE%E6%92%AD%E5%9B%BE/2.png', 'http://localhost:8001/files/%E9%A6%96%E9%A1%B5%E8%BD%AE%E6%92%AD%E5%9B%BE/3.png'],
    select: 0,
    height: 0,
    sortList: [{
        name: '热门课程'
      },
      {
        name: '班级'
      },
      {
        name: '测评'
      },
      {
        name: '培训'
      },
      {
        name: '组群'
      }
    ],
    showview:["one","none","none","none","none"],
    init:{
      kc:[],
      bj:[],
      cp:[],
      px:[],
      zq:[]
    }
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    this.watchHeight()
    this.getinit()
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



  // 触发tab导航栏
  activeTab(e) {
    var index = e.currentTarget.dataset.index
    console.log(index)
    this.setData({
      select: index
    })
    this.generalEv()
    this.watchHeight()
    this.changeView(index)
  },

  // 滑动swiper
  activeSw(e) {
    var index = e.detail.current
    console.log(index)
    this.setData({
      select: index
    })
    this.generalEv()
    this.watchHeight()
    this.changeView(index)
  },

  // 监听swiper高度
  watchHeight() {
    var query = wx.createSelectorQuery()
    query.select('.box').boundingClientRect((res) => {
      this.setData({
        height: parseInt(res.height)
      })
    }).exec()
  },

  // 初始化值
  generalEv() {
    this.setData({
      placeList: [1, 2, 3, 4]
    })
    // 回到顶部
    wx.pageScrollTo({
      scrollTop: 0
    })
  },

  onReachBottom: function () {
    var list = this.data.placeList
    this.setData({
      placeList: list
    })
    this.watchHeight()
  },

  changeView(index) {
    console.log("现在是第"+index+"页面")
    switch (index) {
      case 0:
        this.showview=["one","none","none","none","none"]
        console.log("??")
        this.setData({
          showview : this.showview
        });
        break;
      case 1:
        this.showview=["none","two","none","none","none"]
        console.log("??")
        this.setData({
          showview : this.showview
        });
        break;
      case 2:
        this.showview=["none","none","three","none","none"]
        console.log("??")
        this.setData({
          showview : this.showview
        });
        break;
      case 3:
        this.showview=["none","none","none","four","none"]
        console.log("??")
        this.setData({
          showview : this.showview
        });
        break;
      case 4:
        this.showview=["none","none","none","none","five"]
        console.log("??")
        this.setData({
          showview : this.showview
        });
        break;
      default:
        // statements // 如果没有与表达式相同的值，则执行该代码
        break;
    } 
    var list = this.data.placeList
    list.push(1, 2, 3, 4)
    this.setData({
      placeList: list
    })
    this.watchHeight();
    this.onLoad()
  },

  getinit(){
    let that = this;
    wx.request({
      url: 'http://localhost:8001/Wxdata/index', //仅为示例，并非真实的接口地址
      success (res) {
        console.log(res.data)
        that.setData({
          init:res.data
        })
      }
    })
  }

})