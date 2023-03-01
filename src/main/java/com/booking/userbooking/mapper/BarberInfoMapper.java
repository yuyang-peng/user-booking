package com.booking.userbooking.mapper;

import com.booking.userbooking.pojo.BarberInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.booking.userbooking.vo.BarberVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author jiaze.chen
* @description 针对表【barber_info(理发师信息表)】的数据库操作Mapper
* @createDate 2023-02-21 16:11:26
* @Entity com.booking.userbooking.pojo.BarberInfo
*/
@Mapper
public interface BarberInfoMapper extends BaseMapper<BarberInfo> {

    /**
     * 获取除登录理发师外全部的理发师信息
     * @param param
     * @param barberNo
     * @return List<BarberInfo>
     */
    List<BarberVo> getAllBarberInfo(@Param("param") String param, @Param("barberNo") String barberNo);

    Integer addBarber(BarberInfo barberInfo1);

    int editBarber(BarberInfo barberInfo1);

    Integer deleteBarberByBarberNo(@Param("barberNo") String barberNo);
}




