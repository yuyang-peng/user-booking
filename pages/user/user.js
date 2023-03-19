// pages/user/user.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    date:new Date(new Date().getTime()+24*60*60*1000).toJSON().substring(0, 10),
    userInfo:{
      nickName:"点击头像授权",
      gender:"点击头像授权",
      avatarUrl:"/pages/images/avatar.png",
    },
    bookInfo:{
      // barberNo: "2",
      // bookingType: 2,
      // skillNo: "洗剪吹"
    },
    phone:"暂未授权",
    openId:app.globalData.openId,
    hasPhone:false,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var gender = ["未知","男","女"];
    var bookingType = ["上午","下午"]
    var that = this;
    if (app.globalData.userInfo != null){
      this.setData({
        userInfo: {
          nickName: app.globalData.userInfo.nickName,
          gender: gender[app.globalData.userInfo.gender],
          avatarUrl: app.globalData.userInfo.avatarUrl,
        },
    })}
    if (app.globalData.phone != null){
      this.setData({
        phone:app.globalData.phone,
    })}
    if(app.globalData.openId !=null){
      this.setData({
        hasPhone: true,
        })
    }
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

    wx.request({
      url: 'http://localhost:8080/api/getBookByOpenId',
      method: "POST",
      data: {
        openId: app.globalData.openId
      },
      header: {
        'content-type': 'application/json' // 默认值
      },
      success (res) {
        if (res.statusCode == 200){
          if (JSON.stringify(res.data.data) != "{}" ){
            res.data.data.bookInfo.bookingType = bookingType[res.data.data.bookInfo.bookingType - 1 ]
            app.globalData.barberNo = res.data.data.bookInfo.barberNo
            that.setData({
              bookInfo: res.data.data.bookInfo
            })
          }else{
            that.setData({
              bookInfo: "暂未预约"
            }) 
          }
        }else{
          wx.showToast({
            title: "网络繁忙",
            icon: 'error',
            duration: 1000
          })
        }
      },
    })
    
  },
  getUserProfile(e) {
    if (this.data.userInfo.nickName == "点击头像授权"){
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
    //获取用户手机号
    getPhoneNumber (e) {
      var that = this
      wx.request({
        url: 'http://localhost:8080/api/wxLogin',
        method: "POST",
        data: {
          code: e.detail.code,
          jsCode:app.globalData.jsCode
        },
        header: {
          'content-type': 'application/json' // 默认值
        },
        success (res) {
          if (res.statusCode == 200 ) {
            if (res.data.code == 0){
              app.globalData.phone = res.data.data.phone
              app.globalData.openId = res.data.data.openId
              that.setData({
                  phone: res.data.data.phone,
              })
              wx.showToast({
                title:"登陆成功",
                icon:"success",
                duration:500,
                success: function () {
                  setTimeout(function () {
                    wx.redirectTo({
                      url:"/pages/user/user"
                    })
                  }, 500);
                 }
              })
            }else{
              wx.showToast({
                title: res.data.msg,
                icon: 'error',
                duration: 500
              })
            }
          }else{
            wx.showToast({
              title: "网络繁忙",
              icon: 'error',
              duration: 500
            })
          }
          
        },
        fail (res){
          wx.showToast({
            title: "网络繁忙",
            icon: 'error',
            duration: 2000
          })
        }
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