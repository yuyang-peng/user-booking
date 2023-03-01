package com.booking.userbooking.service;

import com.booking.userbooking.http.request.AddActivityParam;
import com.booking.userbooking.http.request.AddBarberParam;
import com.booking.userbooking.pojo.ActivityInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.booking.userbooking.pojo.BarberInfo;
import com.booking.userbooking.vo.ActivityInfoVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
* @author jiaze.chen
* @description 针对表【activity_info(活动表)】的数据库操作Service
* @createDate 2023-02-21 16:01:14
*/
public interface ActivityInfoService extends IService<ActivityInfo> {

    PageInfo<ActivityInfoVo> getAllActivity(String param, int page, int limit);


    List<ActivityInfo> selectActivityByActivityNo(AddActivityParam param);

    int addActivity(AddActivityParam param, BarberInfo barberInfo);

    int editActivity(AddBarberParam param, BarberInfo barberInfo);

    Integer deleteActivityByActivityNo(String activityNo);

    Boolean openActivityByActivityNo(String activityNo);

    Boolean closeActivityByActivityNo(String activityNo);
}
