package com.booking.userbooking.mapper;

import com.booking.userbooking.pojo.BarberSkill;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author jiaze.chen
* @description 针对表【barber_skill(理发师技能表)】的数据库操作Mapper
* @createDate 2023-02-21 16:12:56
* @Entity com.booking.userbooking.pojo.BarberSkill
*/
@Mapper
public interface BarberSkillMapper extends BaseMapper<BarberSkill> {

    /**
     * 获取所有理发师技能
     * @param param
     * @return
     */
    List<BarberSkill> getAllBarberSkill(@Param("param")String param);
}




