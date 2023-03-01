package com.booking.userbooking.mapper;

import com.booking.userbooking.pojo.ActivityInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.booking.userbooking.vo.ActivityInfoVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author jiaze.chen
* @description 针对表【activity_info(活动表)】的数据库操作Mapper
* @createDate 2023-02-21 16:01:14
* @Entity com.booking.userbooking.pojo.ActivityInfo
*/
@Mapper
public interface ActivityInfoMapper extends BaseMapper<ActivityInfo> {

    List<ActivityInfoVo> getAllActivityInfo(@Param("param") String param);

    int addActivity(ActivityInfo activityInfo);

    int editActivity(ActivityInfo activityInfo);

    Integer deleteActivityByActivityNo(@Param("activityNo") String activityNo);
}




