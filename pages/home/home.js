var app = getApp();
// pages/home/home.js

Page({

  /**
   * 页面的初始数据
   */
  data: {
    phone:12345678,
    name:'一剪梅',
    img_url:['/pages/images/理发店1.png','/pages/images/理发店2.png','/pages/images/理发店3.png','/pages/images/理发店4.png','/pages/images/理发店5.png',],
    latitude:30.38995108997712,
    longitude:114.54009438938141,
    markers: [
      {
      latitude: 30.38995108997712,
      longitude: 114.54009438938141,
      width: 30,
      height: 30
      }
      ],
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
    })
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