package com.booking.userbooking.service;

import com.booking.userbooking.http.request.UpdateSkillParam;
import com.booking.userbooking.pojo.BarberInfo;
import com.booking.userbooking.pojo.BarberSkill;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
* @author jiaze.chen
* @description 针对表【barber_skill(理发师技能表)】的数据库操作Service
* @createDate 2023-02-21 16:12:56
*/
public interface BarberSkillService extends IService<BarberSkill> {

    PageInfo<BarberSkill> getAllBarberSkill(String param,int page,int size);

    List<Map<String, String>> getAllSkill();

    Boolean editSkill(UpdateSkillParam param, BarberInfo barberInfo);
}
