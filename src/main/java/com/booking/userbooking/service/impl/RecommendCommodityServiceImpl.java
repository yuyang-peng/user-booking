package com.booking.userbooking.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.booking.userbooking.http.request.AddRecommendCommodityParam;
import com.booking.userbooking.pojo.BarberInfo;
import com.booking.userbooking.pojo.RecommendCommodity;
import com.booking.userbooking.service.RecommendCommodityService;
import com.booking.userbooking.mapper.RecommendCommodityMapper;
import com.booking.userbooking.vo.RecommendCommodityVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
* @author jiaze.chen
* @description 针对表【recommend_commodity(推荐商品)】的数据库操作Service实现
* @createDate 2023-02-21 16:13:02
*/
@Service
public class RecommendCommodityServiceImpl extends ServiceImpl<RecommendCommodityMapper, RecommendCommodity>
    implements RecommendCommodityService{

    @Override
    public PageInfo<RecommendCommodityVo> getAllRecommend(String param, int page, int limit) {
        PageHelper.startPage(page,limit);
        List<RecommendCommodityVo> recommendCommodities =  getBaseMapper().getAllRecommend(param);
        for (RecommendCommodityVo recommendCommodity : recommendCommodities){
            //设置页面展示的图片地址
            String[] imgList = recommendCommodity.getImage().split("\\\\");
            recommendCommodity.setImageTmp("../images/" +imgList[imgList.length-1]);
        }
        return new PageInfo<>(recommendCommodities);
    }

    @Override
    public List<RecommendCommodity> selectRecommendByRecommendNo(AddRecommendCommodityParam param) {
        return this.lambdaQuery()
                .eq(RecommendCommodity::getRecommendCommodityNo,param.getRecommendCommodityNo())
                .list();
    }

    @Override
    public int addRecommend(AddRecommendCommodityParam param, BarberInfo barberInfo) {
        RecommendCommodity recommendCommodity = BeanUtil.copyProperties(param, RecommendCommodity.class);
        recommendCommodity.setCreateId(barberInfo.getBarberNo());
        recommendCommodity.setCreateTime(new Date());
        recommendCommodity.setUpdateId(barberInfo.getBarberNo());
        recommendCommodity.setUpdateTime(new Date());
        return getBaseMapper().addRecommend(recommendCommodity);
    }

    @Override
    public int editRecommend(AddRecommendCommodityParam param, BarberInfo barberInfo) {
        RecommendCommodity recommendCommodity = BeanUtil.copyProperties(param, RecommendCommodity.class);
        recommendCommodity.setUpdateId(barberInfo.getBarberNo());
        recommendCommodity.setUpdateTime(new Date());
        return getBaseMapper().editRecommend(recommendCommodity);
    }

    @Override
    public Integer deleteRecommendByRecommendCommodityNo(String recommendCommodityNo) {
        return getBaseMapper().deleteRecommendByRecommendCommodityNo(recommendCommodityNo);
    }
}




