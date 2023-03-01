package com.booking.userbooking.service;

import com.booking.userbooking.http.request.AddBarberParam;
import com.booking.userbooking.http.request.AddRecommendCommodityParam;
import com.booking.userbooking.pojo.BarberInfo;
import com.booking.userbooking.pojo.RecommendCommodity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.booking.userbooking.vo.RecommendCommodityVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
* @author jiaze.chen
* @description 针对表【recommend_commodity(推荐商品)】的数据库操作Service
* @createDate 2023-02-21 16:13:02
*/
public interface RecommendCommodityService extends IService<RecommendCommodity> {

    PageInfo<RecommendCommodityVo> getAllRecommend(String param, int page, int limit);

    List<RecommendCommodity> selectRecommendByRecommendNo(AddRecommendCommodityParam param);

    int addRecommend(AddRecommendCommodityParam param, BarberInfo barberInfo);

    int editRecommend(AddRecommendCommodityParam param, BarberInfo barberInfo);

    Integer deleteRecommendByRecommendCommodityNo(String recommendCommodityNo);
}
