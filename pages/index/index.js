//index.js
//获取应用实例
const app = getApp()

Page({
  data: {
    motto: '欢迎来到美容专题',
    userInfo: {},
    phone:null,
    hasPhone:false,
    hasUserInfo: false,
    canIUseGetUserProfile: false,
  },
  //事件处理函数
  toHome:function(){
    if (this.data.phone == null ){
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
            console.log(res)
            app.globalData.phone = res.data.data.phone
            app.globalData.openId = res.data.data.openId
            that.setData({
                phone: res.data.data.phone,
                hasPhone: true
            })
            wx.showToast({
              title: "登陆成功",
              icon: 'success',
              duration: 500
            })
          }else{
            wx.showToast({
              title: res.data.msg,
              icon: 'success',
              duration: 500
            })
          }

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
