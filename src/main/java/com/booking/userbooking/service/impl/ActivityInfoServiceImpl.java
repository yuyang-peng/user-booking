package com.booking.userbooking.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.booking.userbooking.http.request.AddActivityParam;
import com.booking.userbooking.http.request.AddBarberParam;
import com.booking.userbooking.pojo.ActivityInfo;
import com.booking.userbooking.pojo.BarberInfo;
import com.booking.userbooking.pojo.BarberSkill;
import com.booking.userbooking.service.ActivityInfoService;
import com.booking.userbooking.mapper.ActivityInfoMapper;
import com.booking.userbooking.vo.ActivityInfoVo;
import com.booking.userbooking.vo.BarberVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
* @author jiaze.chen
* @description 针对表【activity_info(活动表)】的数据库操作Service实现
* @createDate 2023-02-21 16:01:14
*/
@Service
public class ActivityInfoServiceImpl extends ServiceImpl<ActivityInfoMapper, ActivityInfo>
    implements ActivityInfoService{

    @Override
    public PageInfo<ActivityInfoVo> getAllActivity(String param, int page, int limit) {
        PageHelper.startPage(page,limit);
        List<ActivityInfoVo> activityInfoVos =  getBaseMapper().getAllActivityInfo(param);
        //拼接后端展示图片
        for (ActivityInfoVo info : activityInfoVos){
            //设置页面展示的图片地址
            String[] imgList = info.getImage().split("\\\\");
            info.setImageTmp("../images/" +imgList[imgList.length-1]);
        }

        return new PageInfo<>(activityInfoVos);
    }

    @Override
    public List<ActivityInfo> selectActivityByActivityNo(AddActivityParam param) {
        return this.lambdaQuery()
                .eq(ActivityInfo::getActivityNo,param.getActivityNo())
                .list();
    }

    @Override
    public int addActivity(AddActivityParam param, BarberInfo barberInfo) {
        ActivityInfo activityInfo = BeanUtil.copyProperties(param, ActivityInfo.class);
        activityInfo.setCreateId(barberInfo.getBarberNo());
        activityInfo.setCreateTime(new Date());
        activityInfo.setUpdateId(barberInfo.getBarberNo());
        activityInfo.setUpdateTime(new Date());
        return getBaseMapper().addActivity(activityInfo);
    }

    @Override
    public int editActivity(AddBarberParam param, BarberInfo barberInfo) {
        ActivityInfo activityInfo = BeanUtil.copyProperties(param, ActivityInfo.class);
        activityInfo.setUpdateId(barberInfo.getBarberNo());
        activityInfo.setUpdateTime(new Date());
        return getBaseMapper().editActivity(activityInfo);
    }

    @Override
    public Integer deleteActivityByActivityNo(String activityNo) {
        return getBaseMapper().deleteActivityByActivityNo(activityNo);
    }

    @Override
    public Boolean openActivityByActivityNo(String activityNo) {
        return this.lambdaUpdate()
                .eq(ActivityInfo::getActivityNo,activityNo)
                .set(ActivityInfo::getStatus,1)
                .update();

    }

    @Override
    public Boolean closeActivityByActivityNo(String activityNo) {
        return this.lambdaUpdate()
                .eq(ActivityInfo::getActivityNo,activityNo)
                .set(ActivityInfo::getStatus,0)
                .update();
    }
}




