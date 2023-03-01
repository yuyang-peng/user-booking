package com.booking.userbooking.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.booking.userbooking.pojo.UserInfo;
import com.booking.userbooking.service.UserInfoService;
import com.booking.userbooking.mapper.UserInfoMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author jiaze.chen
* @description 针对表【user_info(用户表)】的数据库操作Service实现
* @createDate 2023-02-21 16:13:05
*/
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo>
    implements UserInfoService{

    @Override
    public PageInfo<UserInfo> getAllUser(String param, int page, int limit) {
        PageHelper.startPage(page,limit);
        List<UserInfo> allUser = getBaseMapper().getAllUser(param);
        return new PageInfo<>(allUser);
    }
}




