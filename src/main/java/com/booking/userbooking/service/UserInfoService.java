package com.booking.userbooking.service;

import com.booking.userbooking.pojo.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;

/**
* @author jiaze.chen
* @description 针对表【user_info(用户表)】的数据库操作Service
* @createDate 2023-02-21 16:13:05
*/
public interface UserInfoService extends IService<UserInfo> {

    PageInfo<UserInfo> getAllUser(String param, int page, int limit);
}
