// pages/user/user.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    userInfo:{
      nickName:"未知",
      gender:"未知",
      avatarUrl:"/pages/images/avatar.png",
    },
    phone:"***********",
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var gender = ["未知","男","女"];
    if (app.globalData.userInfo != null){
      this.setData({
        userInfo: {
          nickName: app.globalData.userInfo.nickName,
          gender: gender[app.globalData.userInfo.gender],
          avatarUrl: app.globalData.userInfo.avatarUrl,
        },
    })}
    if (app.globalData.phoneInfo != null){
      this.setData({
        phone:app.globalData.phoneInfo.phone,
    })}
    // 获取用户信息
    wx.getUserProfile({
      success: function (res) {
        console.log(res)
        var userInfo = res.userInfo
        var nickName = userInfo.nickName
        var avatarUrl = userInfo.avatarUrl
        var province = userInfo.province
        var city = userInfo.city
        var country = userInfo.country
        // 修改用户信息数据
        this.setData({
          userInfo: {
            nickName: userInfo.nickName,
            gender: gender[userInfo.gender],
            avatarUrl: userInfo.avatarUrl,
          }
        })
      }
    })
    
  },
  getUserProfile(e) {
    if (this.data.userInfo.nickName == "未知"){
      wx.getUserProfile({
        desc: '用于完善会员资料', // 声明获取用户个人信息后的用途，后续会展示在弹窗中，请谨慎填写
        success: (res) => {
          var gender = ["未知","男","女"];
          app.globalData.userInfo = res.userInfo
          res.userInfo.gender = gender[res.userInfo.gender]
          this.setData({
            userInfo: res.userInfo,
            hasUserInfo: true
          })
        }
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