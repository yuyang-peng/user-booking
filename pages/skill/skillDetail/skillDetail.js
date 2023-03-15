// pages/skill/skillDetail/skillDetail.js
const app = getApp()  

Page({

  /**
   * 页面的初始数据
   */
  data: {
    service: {
      // id: 0,
      // barberName: "彭技师",
      // barberAge:"22",
      // barberPhone:"18062790665",
      // image: "/pages/images/skilledt_img_01.png",
      // score: 5,
    },
    skillList:[
      // {
      //   skillNo:0,
      //   skillName:"洗剪吹",
      //   skillAmount:"30"
      // },
    ]
  },
  toAppointment:function(e){
    const index = e.currentTarget.dataset.index;
    console.log("当前点击的技术是：" + index);
    app.globalData.skillNo = index
    wx.navigateTo({
      url:"/pages/appointment/appointment"
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function () {
    var barberList = app.globalData.barberList
    var barberNo = app.globalData.barberNo
    var that = this
    if (barberNo != null && barberList != null){
      for (var i = 0; i < barberList.length; i++) {
        if (barberList[i].barberNo == barberNo){
          console.log(barberList[i])
          app.globalData.barber = barberList[i]
          wx.request({
            url: 'http://localhost:8080/api/getSkillByBarberNo',
            method: "POST",
            data: {
              barberNo: barberNo
            },
            header: {
              'content-type': 'application/json' // 默认值
            },
            success (res) {
              if (res.statusCode == 200){
                that.setData({
                  skillList: res.data.data.barberSkillList
                })
              }else{
                wx.showToast({
                  title: "网络繁忙",
                  icon: 'error',
                  duration: 1000
                })
              }
            },
          })
          this.setData({
            service:barberList[i]
        })
        }
      }
  }else{
    wx.showToast({
      title: "网络繁忙",
      icon: 'error',
      duration: 1000
    })
    }

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})