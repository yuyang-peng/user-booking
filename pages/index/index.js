//index.js
//获取应用实例
const app = getApp()

Page({
  data: {
    motto: '欢迎来到美容专题',
    userInfo: {},
    phoneInfo:{},
    hasPhone:false,
    hasUserInfo: false,
    canIUseGetUserProfile: false,
  },
  //事件处理函数
  toHome:function(){
    if (JSON.stringify(this.data.phoneInfo) == "{}" ){
      wx.showToast({
        title: "请先登录",
        icon: 'error',
        duration: 1000
      })
    }else{
      wx.reLaunch({
        url: '/pages/home/home',
      })
    }

  },
  bindViewTap: function() {
    wx.navigateTo({
      url: '/pages/logs/logs'
    })
  },
  onLoad() {
    if (wx.getUserProfile) {
      this.setData({
        canIUseGetUserProfile: true
      })
    }
  },
  getUserProfile(e) {
    // 推荐使用 wx.getUserProfile 获取用户信息，开发者每次通过该接口获取用户个人信息均需用户确认
    // 开发者妥善保管用户快速填写的头像昵称，避免重复弹窗
    wx.getUserProfile({
      desc: '用于完善会员资料', // 声明获取用户个人信息后的用途，后续会展示在弹窗中，请谨慎填写
      success: (res) => {
        app.globalData.userInfo = res.userInfo
        this.setData({
          userInfo: res.userInfo,
          hasUserInfo: true
        })
      }
    })
  },

  //获取用户手机号
  getPhoneNumber (e) {
    var that = this
    console.log(e.detail.code)
    wx.request({
      url: 'http://localhost:8080/api/wxGetPhone',
      method: "POST",
      data: {
        code: e.detail.code,
        openId:app.globalData.openId
      },
      header: {
        'content-type': 'application/json' // 默认值
      },
      success (res) {
        if (res.statusCode == 200){
          app.globalData.phoneInfo = res.data.data
          that.setData({
              phoneInfo: res.data.data,
              hasPhone: true
          })
          wx.showToast({
            title: "登陆成功",
            icon: 'success',
            duration: 500
          })
        }else{
          wx.showToast({
            title: "网络繁忙",
            icon: 'error',
            duration: 2000
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

  }
})
