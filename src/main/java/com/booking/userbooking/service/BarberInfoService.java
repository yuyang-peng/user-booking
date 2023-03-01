package com.booking.userbooking.service;

import com.booking.userbooking.http.request.AddBarberParam;
import com.booking.userbooking.http.request.UserLoginParam;
import com.booking.userbooking.pojo.BarberInfo;
import com.booking.userbooking.vo.BarberVo;
import com.github.pagehelper.PageInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author jiaze.chen
* @description 针对表【barber_info(理发师信息表)】的数据库操作Service
* @createDate 2023-02-21 16:11:26
*/
public interface BarberInfoService extends IService<BarberInfo> {

    List<BarberInfo> barberLogin(UserLoginParam param);

    PageInfo<BarberVo> getAllBarberInfo(BarberInfo barberInfo, String param, int page, int limit);


    List<BarberInfo> selectBarberByBarberNo(AddBarberParam param);

    int addBarber(AddBarberParam param, BarberInfo barberInfo);

    int editBarber(AddBarberParam param, BarberInfo barberInfo);

    Integer deleteBarberByBarberNo(String barberNo);

    BarberInfo getBarberInfo(BarberInfo barberInfo);
}
