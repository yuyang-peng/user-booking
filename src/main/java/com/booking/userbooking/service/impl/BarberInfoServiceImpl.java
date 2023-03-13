package com.booking.userbooking.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.booking.userbooking.http.request.AddBarberParam;
import com.booking.userbooking.http.request.UserLoginParam;
import com.booking.userbooking.pojo.ActivityInfo;
import com.booking.userbooking.pojo.BarberInfo;
import com.booking.userbooking.pojo.BarberSkill;
import com.booking.userbooking.service.ActivityInfoService;
import com.booking.userbooking.service.BarberInfoService;
import com.booking.userbooking.mapper.BarberInfoMapper;
import com.booking.userbooking.service.BarberSkillService;
import com.booking.userbooking.util.Constant;
import com.booking.userbooking.vo.BarberVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.booking.userbooking.util.Constant.ADMIN_NAME;
import static com.booking.userbooking.util.Constant.ADMIN_TYPE;

/**
* @author jiaze.chen
* @description 针对表【barber_info(理发师信息表)】的数据库操作Service实现
* @createDate 2023-02-21 16:11:26
*/
@Service
public class BarberInfoServiceImpl extends ServiceImpl<BarberInfoMapper, BarberInfo>
    implements BarberInfoService{

    @Autowired
    private BarberSkillService barberSkillService;

    @Override
    public List<BarberInfo> barberLogin(UserLoginParam param) {
        List<BarberInfo> barberInfoList = this.lambdaQuery()
                .eq(BarberInfo::getBarberNo, param.getUserName())
                .eq(BarberInfo::getBarberPassword, param.getPassword())
                .list();
        if (barberInfoList.size()>0){
            if (ADMIN_TYPE.equals(param.getType())){
                if (ADMIN_NAME.equals(barberInfoList.get(0).getBarberNo())){
                    return barberInfoList;
                }else {
                    return new ArrayList<>();
                }
            }
        }
        return barberInfoList;
    }

    @Override
    public PageInfo<BarberVo> getAllBarberInfo(BarberInfo barberInfo, String param, int page, int limit) {
        PageHelper.startPage(page,limit);
        String barberNo = barberInfo.getBarberNo();
        List<BarberVo> barberInfoList =  getBaseMapper().getAllBarberInfo(param,barberNo);
        //获取所有的技能名称
        List<BarberSkill> barberSkillList = barberSkillService.lambdaQuery().list();
        Map<String, String> activeNoToActiveNameMap = barberSkillList.stream().collect(Collectors.toMap(BarberSkill::getSkillNo, BarberSkill::getSkillName, (s1, s2) -> s2));
        //拼接技能名称
        for (BarberVo info : barberInfoList){
            //设置页面展示的图片地址
            String[] imgList = info.getImage().split("\\\\");
            info.setImageTmp("../images/" +imgList[imgList.length-1]);
            String[] list = info.getBarberSkillNo().split(",");
            String activeNameTmp = "";
            for (int i = 0;i<list.length;i++){
                if (activeNoToActiveNameMap.containsKey(list[i])){
                    activeNameTmp += activeNoToActiveNameMap.get(list[i]) + ",";
                }
            }
            info.setBarberSkillNames(activeNameTmp.substring(0,activeNameTmp.length()-1));
        }
        //拼接后端展示图片
        return new PageInfo<>(barberInfoList);
    }

    @Override
    public List<BarberInfo> selectBarberByBarberNo(AddBarberParam param) {
        return this.lambdaQuery()
                .eq(BarberInfo::getBarberNo, param.getBarberNo())
                .list();
    }

    @Override
    public int addBarber(AddBarberParam param, BarberInfo barberInfo) {
        BarberInfo barberInfo1 = BeanUtil.copyProperties(param, BarberInfo.class);
        barberInfo1.setCreateId(barberInfo.getBarberNo());
        barberInfo1.setCreateTime(new Date());
        barberInfo1.setUpdateId(barberInfo.getBarberNo());
        barberInfo1.setUpdateTime(new Date());
        return getBaseMapper().addBarber(barberInfo1);
    }

    @Override
    public int editBarber(AddBarberParam param, BarberInfo barberInfo) {
        BarberInfo barberInfo1 = BeanUtil.copyProperties(param, BarberInfo.class);
        barberInfo1.setUpdateId(barberInfo.getBarberNo());
        barberInfo1.setUpdateTime(new Date());
        return getBaseMapper().editBarber(barberInfo1);
    }

    @Override
    public Integer deleteBarberByBarberNo(String barberNo) {
        return getBaseMapper().deleteBarberByBarberNo(barberNo);
    }

    @Override
    public BarberInfo getBarberInfo(BarberInfo barberInfo) {
        return this.lambdaQuery()
                .eq(BarberInfo::getBarberNo,barberInfo.getBarberNo())
                .one();
    }

    @Override
    public boolean updateAllBarberNum() {
        List<BarberInfo> barberInfos = this.lambdaQuery()
                .ne(BarberInfo::getBarberNo, ADMIN_NAME).list();
        List<String> barberNos = barberInfos.stream().map(BarberInfo::getBarberNo).collect(Collectors.toList());
        return this.lambdaUpdate()
                .set(BarberInfo::getAfternoonBookingNum, 4)
                .set(BarberInfo::getMorningBookingNum, 4)
                .in(BarberInfo::getBarberNo, barberNos)
                .update();
    }
}




