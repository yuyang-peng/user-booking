// pages/tuijian/tuijian.js
var app = getApp();

Page({

  /**
   * 页面的初始数据
   */
  data: {
    autoplay:true,
    duration:1000,
    interval:2000,
    vertical:false,
    indicatorDots:true,
    activeList:[{
    },],
    currentData:0,
    navTop:[{
      id: 0,
      title:"推荐",
      imageUrl:"/pages/images/nav_icon_01.png"
    }, {
        id: 1,
        title: "技师",
        imageUrl: "/pages/images/nav_icon_02.png"
      }, {
        id: 2,
        title: "洗发",
        imageUrl: "/pages/images/nav_icon_03.png"
      }, {
        id: 3,
        title: "护发",
        imageUrl: "/pages/images/nav_icon_04.png"
      }, {
        id: 4,
        title: "其他",
        imageUrl: "/pages/images/nav_icon_05.png"
      }],
    contentList: ["recommend", "barber", "shampoo", "conditioner","other"],//获取所有选项卡类型
    contentInfo: "",//当前所选选项卡的数据
        recommend:[],
        barber: [], 
        shampoo: [], 
        conditioner: [], 
        other: []
  },
  //切换到详情界面 
  toAppointment:function(e){
    const barberNo = e.currentTarget.dataset.barberno;
    const type = e.currentTarget.dataset.type;
    console.log("当前点击类型及其no为："+type+barberNo);
    app.globalData.barberNo= barberNo
    if (type == 'barber'){
      wx.navigateTo({
        url:"/pages/skill/skillDetail/skillDetail"
      })
    }
  },
  checkCurrent:function(e){
    const that = this;
    if (that.data.currentData === e.currentTarget.dataset.current){
        return false
    }else{
      var array = this.data.contentList[e.currentTarget.dataset.current];

      that.setData({
        currentData: e.currentTarget.dataset.current,
        contentInfo: this.data[array] 
      })
    }
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    let shampoo = [];
    let conditioner = [];
    let other = [];
    var that = this;
    //获取活动
    wx.request({
          url: 'http://localhost:8080/api/getActivity',
          method: "GET",
          success (res) {
            console.log()
            if (res.statusCode == 200){
              for (var i = 0; i < res.data.data.activeList.length; i++) {
                var str = res.data.data.activeList[i].image
                res.data.data.activeList[i].image = str.substring(str.indexOf("\\images")).replace(/\\/g,"/")
              }
            that.setData({
              activeList:res.data.data.activeList
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
    //获取推荐技师
    wx.request({
      url: 'http://localhost:8080/api/getRecommendBarber',
      method: "GET",
      success (res) {
        if (res.statusCode == 200){
          for (var i = 0; i < res.data.data.barberList.length; i++) {
            var str = res.data.data.barberList[i].image
            res.data.data.barberList[i].image = str.substring(str.indexOf("\\images")).replace(/\\/g,"/")
            res.data.data.barberList[i].name = res.data.data.barberList[i].barberName
            res.data.data.barberList[i].no = res.data.data.barberList[i].barberNo 
            res.data.data.barberList[i].type = "barber"
            res.data.data.barberList[i].desc = res.data.data.barberList[i].name + "技师,今年: " + res.data.data.barberList[i].barberAge + "岁,擅长洗剪吹,烫头染发等,联系电话: " + res.data.data.barberList[i].barberPhone + ",店内客户评分为: " + res.data.data.barberList[i].score
          }
          app.globalData.barberList = res.data.data.barberList
          that.setData({
            recommend: that.data.recommend.concat(res.data.data.barberList),
            contentInfo: that.data.recommend.concat(res.data.data.barberList),
            barber: res.data.data.barberList
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
    //获取推荐商品
    wx.request({
      url: 'http://localhost:8080/api/getRecommendedProducts',
      method: "GET",
      success (res) {
        if (res.statusCode == 200){
          for (var i = 0; i < res.data.data.commodityList.length; i++) {
            var str = res.data.data.commodityList[i].image
            res.data.data.commodityList[i].image = str.substring(str.indexOf("\\images")).replace(/\\/g,"/")
            res.data.data.commodityList[i].name = res.data.data.commodityList[i].recommendCommodityName
            res.data.data.commodityList[i].no = res.data.data.commodityList[i].recommendCommodityNo 
            res.data.data.commodityList[i].desc = "推荐理发师编号为："+res.data.data.commodityList[i].createId
            if (res.data.data.commodityList[i].name.indexOf("洗发") > -1){
              res.data.data.commodityList[i].type = "shampoo"
              shampoo.push(res.data.data.commodityList[i])
            } else if(res.data.data.commodityList[i].name.indexOf("护发") > -1){
              res.data.data.commodityList[i].type = "conditioner"
              conditioner.push(res.data.data.commodityList[i])
            }else{
              res.data.data.commodityList[i].type = "other"
              other.push(res.data.data.commodityList[i])
            }
          }
          that.setData({
            recommend:that.data.recommend.concat(shampoo,conditioner,other),
            contentInfo: that.data.recommend.concat(shampoo,conditioner,other),
            shampoo: shampoo,
            conditioner: conditioner,
            other: other
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
    this.setData({
      contentInfo: this.data.recommend
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