package com.booking.userbooking.service;

import cn.hutool.json.JSONObject;
import com.booking.userbooking.pojo.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * author : jiaze.chen
 * Date : 2023/3/6 15:45
 **/
public interface WxApiService{

    void insertUserInfo(UserInfo userInfo);

    List<RecommendCommodity> getRecommendedProducts();


    List<BarberInfo> getAllBarber();

    List<BarberInfo> getAllBarberByScore();

    List<BarberSkill> getBarberSkillByBarberNo(String barberNo);

    boolean appraise(String barberNo, BigDecimal score);

    Integer insertBook(JSONObject param);

    boolean verifyBarber(JSONObject param);

    List<ActivityInfo> getActivity();

    boolean verifyUser(JSONObject param);
}
