// pages/skill/skillDetail/skillDetail.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    skillList:{
      id:0,
      skill:[{
        id:0,
        name:"洗剪吹",
        price:"30"
      },{
          id: 1,
          name: "烫发",
          price: "666"
        }, {
          id: 2,
          name: "染发",
          price: "999"
        }]
    }
  },
  toAppointment:function(e){
    const index = e.currentTarget.dataset.index;
    console.log("当前点击的技术是：" + index);
    wx.navigateTo({
      url:"/pages/appointment/appointment"
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

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