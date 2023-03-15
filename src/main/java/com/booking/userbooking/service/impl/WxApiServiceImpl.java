package com.booking.userbooking.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.booking.userbooking.mapper.BookInfoMapper;
import com.booking.userbooking.mapper.UserInfoMapper;
import com.booking.userbooking.pojo.*;
import com.booking.userbooking.service.*;
import com.booking.userbooking.util.Constant;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * author : jiaze.chen
 * Date : 2023/3/6 15:42
 **/
@Service
@Transactional(rollbackFor = {Throwable.class})
public class WxApiServiceImpl implements WxApiService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private RecommendCommodityService recommendCommodityService;

    @Autowired
    private BarberInfoService barberInfoService;

    @Autowired
    private BarberSkillService barberSkillService;

    @Autowired
    private BookInfoMapper bookInfoMapper;

    @Autowired
    private BookInfoService bookInfoService;

    @Autowired
    private ActivityInfoService activityInfoService;

    @Override
    public void insertUserInfo(UserInfo userInfo) {
        //插入前先判断是否已存在
        UserInfo one = userInfoService.lambdaQuery()
                .eq(UserInfo::getOpenId, userInfo.getOpenId())
                .one();
        //已存在执行修改
        if (!ObjectUtil.isEmpty(one)){
            userInfoService.lambdaUpdate()
                    .set(UserInfo::getPhone,userInfo.getPhone())
                    .set(UserInfo::getUpdateTime,new Date())
                    .eq(UserInfo::getOpenId,userInfo.getOpenId())
                    .update();
        }else {
            userInfo.setCreateId("sys");
            userInfo.setCreateTime(new Date());
            userInfo.setUpdateId("sys");
            userInfo.setUpdateTime(new Date());
            userInfoMapper.insertUserInfo(userInfo);
        }
    }

    @Override
    public List<RecommendCommodity> getRecommendedProducts() {
        return recommendCommodityService.lambdaQuery().list();
    }

    @Override
    public List<BarberInfo> getAllBarber() {
        return barberInfoService.lambdaQuery().ne(BarberInfo::getBarberNo, Constant.ADMIN_NAME).list();
    }

    @Override
    public List<BarberInfo> getAllBarberByScore() {
        return barberInfoService.lambdaQuery().ne(BarberInfo::getBarberNo, Constant.ADMIN_NAME).ge(BarberInfo::getScore,8).list();
    }

    @Override
    public List<BarberSkill> getBarberSkillByBarberNo(String barberNo) {
        BarberInfo barberInfo = barberInfoService.lambdaQuery()
                .eq(BarberInfo::getBarberNo,barberNo)
                .one();
        List<BarberSkill> barberSkillList = new ArrayList<>();
        if (!ObjectUtil.isEmpty(barberInfo)){
            String[] tmp = barberInfo.getBarberSkillNo().split(",");
            List<String> barberSkillNoList = Arrays.asList(tmp);
            barberSkillList = barberSkillService.lambdaQuery().in(BarberSkill::getSkillNo, barberSkillNoList).list();
        }
        return barberSkillList;
    }

    @Override
    public boolean appraise(String barberNo, BigDecimal score) {
        //计算评分，先将原来的评分取出
        BarberInfo barberInfo = barberInfoService.lambdaQuery().eq(BarberInfo::getBarberNo,barberNo).one();
        if (ObjectUtil.isEmpty(barberInfo)){
            return false;
        }
        BigDecimal oldScore = BigDecimal.valueOf(barberInfo.getScore());
        BigDecimal nowScore;
        if (score.compareTo(oldScore)>0){
            //表示频分比原分数大--需要加
            nowScore = oldScore.add(score.divide(new BigDecimal(10)));
        }else if (score.compareTo(oldScore)<0){
            BigDecimal minBig = new BigDecimal(1);
            //小则减
            nowScore = oldScore.subtract(minBig.subtract(score.divide(new BigDecimal(10))));
        }else {
            //相等，则无需修改返回成功
            return true;
        }
        return barberInfoService.lambdaUpdate()
                .set(BarberInfo::getScore, nowScore)
                .eq(BarberInfo::getBarberNo, barberNo)
                .update();

    }

    @Override
    @Transactional
    public Integer insertBook(JSONObject param) {
        BookInfo bookInfo = JSONUtil.toBean(param,BookInfo.class);
        bookInfo.setCreateId("sys");
        bookInfo.setCreateTime(new Date());
        bookInfo.setUpdateId("sys");
        bookInfo.setUpdateTime(new Date());
        Integer integer = bookInfoMapper.insertBook(bookInfo);
        if (integer>0){
            //更新理发师的可预约数量
            UpdateWrapper wrapper = new UpdateWrapper();
            wrapper.eq("barber_no",bookInfo.getBarberNo());
            if (1 == bookInfo.getBookingType()){
                wrapper.setSql("morning_booking_num = morning_booking_num-1 ");
            }else {
                wrapper.setSql("afternoon_booking_num = afternoon_booking_num-1");
            }
            boolean update = barberInfoService.update(wrapper);
            if (update){
                return 1;
            }else {
                return 0;
            }
        }else {
            return 0;
        }
    }


    @Override
    public BookInfo getBookByOpenId(String openId) {
        BookInfo bookInfo = bookInfoService.lambdaQuery()
                .eq(BookInfo::getOpenId, openId)
                .one();
        if (!ObjectUtil.isEmpty(bookInfo)){
            bookInfo.setSkillNo(barberSkillService.lambdaQuery().eq(BarberSkill::getSkillNo, bookInfo.getSkillNo()).one().getSkillName());
        }
        return bookInfo;
    }

    @Override
    public boolean verifyBarber(JSONObject param) {
        String barberNo = param.getStr("barberNo");
        BarberInfo barberInfo = barberInfoService.lambdaQuery()
                .eq(BarberInfo::getBarberNo, barberNo)
                .one();
        String bookingType = param.getStr("bookingType");
        if ("1".equals(bookingType)){
            return barberInfo.getMorningBookingNum() > 0 ;
        }else {
            return barberInfo.getAfternoonBookingNum() > 0;
        }
    }

    @Override
    public List<ActivityInfo> getActivity() {
        return activityInfoService.lambdaQuery().list();
    }

    @Override
    public boolean verifyUser(JSONObject param) {
        String today = DateUtil.today() + " 00:00:00";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date beginDate = new Date();
        try {
            beginDate = sdf.parse(today);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        BookInfo bookInfo = JSONUtil.toBean(param,BookInfo.class);
        List<BookInfo> list = bookInfoService.lambdaQuery()
                .eq(BookInfo::getOpenId, bookInfo.getOpenId())
                .eq(BookInfo::getBookingType, bookInfo.getBookingType())
                .between(BookInfo::getCreateTime, beginDate, new Date()).list();
        return list.size() <= 0;
    }
}
