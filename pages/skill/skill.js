// pages/skill/skill.js
//获取应用实例
const app = getApp()  
Page({

  /**
   * 页面的初始数据
   */
  data: {
    serviceList: [
    //   {//推荐
    //   id: 0,
    //   barberName: "彭技师",
    //   barberAge:"22",
    //   barberPhone:"18062790665",
    //   image: "/pages/images/skilledt_img_01.png",
    //   score: 5,
    // }
  ]
  },
  toSkill: function (e) {
    const index = e.currentTarget.dataset.skillid;
    console.log("当前点击技师的id为："+index);
    app.globalData.barberNo= index
    wx.navigateTo({
      url:"skillDetail/skillDetail"
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this
    var compare = function (prop) {
      return function (obj1, obj2) {
          var val1 = obj1[prop];
          var val2 = obj2[prop];
          if (!isNaN(Number(val1)) && !isNaN(Number(val2))) {
              val1 = Number(val1);
              val2 = Number(val2);
          }
          if (val1 < val2) {
              return 1;
          } else if (val1 > val2) {
              return -1;
          } else {
              return 0;
          }            
      } 
    }
      wx.request({
        url: 'http://localhost:8080/api/getAllBarber',
        method: "GET",
        success (res) {
          if (res.statusCode == 200){
            res.data.data.barberList.sort(compare("score"))
            for (var i = 0; i < res.data.data.barberList.length; i++) {
              var str = res.data.data.barberList[i].image
              res.data.data.barberList[i].image = str.substring(str.indexOf("\\images")).replace(/\\/g,"/")
              res.data.data.barberList[i].barberName = res.data.data.barberList[i].barberName + "技师"
              res.data.data.barberList[i].desc = "　" + res.data.data.barberList[i].barberName + ",今年" + res.data.data.barberList[i].barberAge + "岁,擅长洗剪吹,烫头染发等。\n联系电话: " + res.data.data.barberList[i].barberPhone + ",店内客户评分为: " + res.data.data.barberList[i].score
            }
          console.log(res.data.data.barberList)
          app.globalData.barberList = res.data.data.barberList
            that.setData({
              serviceList: res.data.data.barberList,
            })
          }else{
            wx.showToast({
              title: "网络繁忙",
              icon: 'error',
              duration: 1000
            })
          }
        },
        fail (res){
          wx.showToast({
            title: "网络繁忙",
            icon: 'error',
            duration: 1000
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