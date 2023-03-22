// pages/appointment/appointment.js
const app = getApp()  
Page({

  /**
   * 页面的初始数据
   */
  data: {
    date:new Date(new Date().getTime()+24*60*60*1000).toJSON().substring(0, 10),
    time:0,
    all_range:["8:00-9:00","9:00-10:00","10:00-11:00","11:00-12:00","14:00-15:00","15:00-16:00","16:00-17:00","17:00-18:00"]
    // range:['上午','下午'],
    // morningBookingNum:5,
    // afternoonBookingNum:5,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function () {
    var barber =  app.globalData.barber;
    var index = app.globalData.skillNo;
    var range = [];
    var hour = new Date().toTimeString().substring(0,2);
    if (barber != null && index != null){
      if (barber.morningBookingNum){
        range.push.apply(range,this.data.all_range.slice(0,4))
      }
      if (barber.afternoonBookingNum > 0){
        range.push.apply(range,this.data.all_range.slice(4,8))
      }
      this.setData({
        skillNo:index,
        barber:barber,
        range:range
      })
    }else{
      wx.showToast({
        title: "网络繁忙",
        icon: 'error',
        duration: 1000
      })
    }

  },
  bindPickerChange:function(e){
    this.setData({
      time: e.detail.value
    })
  },
  formsubmit:function(e){
    var time = e.detail.value.time;
    var bookingType = this.data.all_range.indexOf(time) + 1;
    console.log(time,bookingType)
    if (app.globalData.openId == null){
      wx.showToast({
        title:"请先登录!",
        icon:"error",
        duration:500,
        success: function () {
          setTimeout(function () {
            wx.redirectTo({
              url:"/pages/index/index"
            })
          }, 500);
         }
      })
    }else{
      if (bookingType > -1 ){
          wx.request({
            url: 'http://localhost:8080/api/insertBook',
            method: "POST",
            data: {
              barberNo:app.globalData.barberNo,
              bookingType: bookingType,
              skillNo:app.globalData.skillNo,
              openId:app.globalData.openId,
            },
            header: {
              'content-type': 'application/json' // 默认值
            },
            success (res) {
              if (res.statusCode == 200){
                if(res.data.msg == "预约成功"){        
                  wx.showToast({
                    title:res.data.msg,
                    icon:"success",
                    duration:500,
                    success: function () {
                      setTimeout(function () {
                        wx.switchTab({
                          url:"/pages/user/user",
                          success: function(e) {
                            var page = getCurrentPages().pop();
                            return page.onLoad();
                          }
                        })
                      }, 500);
                     }
                  })
                }else{
                  wx.showToast({
                    title:res.data.msg,
                    icon:"none",
                    duration:1000
                  })
                }
              }else{
                wx.showToast({
                  title: "提交失败",
                  icon: 'error',
                  duration: 1000
                })
              }
            },
          })
      }else{
        wx.showToast({
          title:"提交失败",
          icon:"error",
          duration:1000
        })
      }
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