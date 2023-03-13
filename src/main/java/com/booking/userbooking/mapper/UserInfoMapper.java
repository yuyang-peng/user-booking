package com.booking.userbooking.mapper;

import com.booking.userbooking.pojo.UserInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author jiaze.chen
* @description 针对表【user_info(用户表)】的数据库操作Mapper
* @createDate 2023-02-21 16:13:05
* @Entity com.booking.userbooking.pojo.UserInfo
*/
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {

    List<UserInfo> getAllUser(String param);

    void insertUserInfo(UserInfo userInfo);
}




