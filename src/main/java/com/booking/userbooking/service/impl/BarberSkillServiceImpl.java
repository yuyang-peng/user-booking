package com.booking.userbooking.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.booking.userbooking.http.request.UpdateSkillParam;
import com.booking.userbooking.pojo.BarberInfo;
import com.booking.userbooking.pojo.BarberSkill;
import com.booking.userbooking.service.BarberSkillService;
import com.booking.userbooking.mapper.BarberSkillMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.*;

/**
* @author jiaze.chen
* @description 针对表【barber_skill(理发师技能表)】的数据库操作Service实现
* @createDate 2023-02-21 16:12:56
*/
@Service
public class BarberSkillServiceImpl extends ServiceImpl<BarberSkillMapper, BarberSkill>
    implements BarberSkillService{

    @Override
    public PageInfo<BarberSkill> getAllBarberSkill(String param, int page, int size) {
        PageHelper.startPage(page,size);
        List<BarberSkill> barberSkillList =  getBaseMapper().getAllBarberSkill(param);
        return new PageInfo<>(barberSkillList);
    }

    @Override
    public List<Map<String, String>> getAllSkill() {
        List<BarberSkill> barberSkillList = this.lambdaQuery().list();
        List<Map<String, String>> rtnList = new ArrayList<>();
        for (BarberSkill barberSkill : barberSkillList){
            Map<String, String> map = new HashMap<>();
            map.put("skillNo",barberSkill.getSkillNo());
            map.put("skillName",barberSkill.getSkillName());
            map.put("selected","selected");
            map.put("disabled","disabled");
            rtnList.add(map);
        }
        return rtnList;
    }

    @Override
    public Boolean editSkill(UpdateSkillParam param, BarberInfo barberInfo) {
        return this.lambdaUpdate()
                .eq(BarberSkill::getSkillNo,param.getSkillNo())
                .set(BarberSkill::getSkillAmount,param.getSkillAmount())
                .set(BarberSkill::getUpdateId,barberInfo.getBarberNo())
                .set(BarberSkill::getUpdateTime,new Date()).update();
    }
}




