package com.booking.userbooking.mapper;

import com.booking.userbooking.pojo.RecommendCommodity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.booking.userbooking.vo.RecommendCommodityVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author jiaze.chen
* @description 针对表【recommend_commodity(推荐商品)】的数据库操作Mapper
* @createDate 2023-02-21 16:13:02
* @Entity com.booking.userbooking.pojo.RecommendCommodity
*/
@Mapper
public interface RecommendCommodityMapper extends BaseMapper<RecommendCommodity> {

    List<RecommendCommodityVo> getAllRecommend(@Param("param") String param);

    int addRecommend(RecommendCommodity recommendCommodity);

    int editRecommend(RecommendCommodity recommendCommodity);

    Integer deleteRecommendByRecommendCommodityNo(@Param("recommendCommodityNo") String recommendCommodityNo);
}




