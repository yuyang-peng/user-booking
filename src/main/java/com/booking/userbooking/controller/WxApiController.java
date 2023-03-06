package com.booking.userbooking.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.booking.userbooking.pojo.*;
import com.booking.userbooking.service.*;
import com.booking.userbooking.util.Constant;
import com.booking.userbooking.util.ResultObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * author : jiaze.chen
 * Date : 2023/3/6 14:50
 **/
@Slf4j
@RestController
@RequestMapping("/api")
public class WxApiController {

    @Autowired
    private WxApiService wxApiService;

    @Autowired
    private BarberSkillService barberSkillService;

    @ResponseBody
    @PostMapping("/wxLogin")
    public ResultObject<Object> login(@RequestBody JSONObject param) {
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + Constant.APPID + "&secret=" + Constant.APPSECRET
                + "&js_code=" + param.getStr("code") + "&grant_type=authorization_code";
        RestTemplate restTemplate = new RestTemplate();
        ResultObject<Object> res = new ResultObject<>();
        JSONObject rtn = new JSONObject();
        String result = restTemplate.getForObject(url, String.class);
        JSONObject jsonObject = JSONUtil.parseObj(result);
        if (!"0".equals(jsonObject.getStr("errcode"))){
            res.setCode(Constant.FAILURE_RETUEN_CODE);
            res.setMsg(jsonObject.getStr("errmsg"));
            log.info(res.toString());
            return res;
        }
        WxInfo wxInfo = jsonObject.toBean(WxInfo.class);
        rtn.putOnce("openId", wxInfo.getOpenid());
        //插入user表
        UserInfo userInfo = new UserInfo();
        userInfo.setPhone(param.getStr("phone"));
        userInfo.setOpenId(wxInfo.getOpenid());
        wxApiService.insertUserInfo(userInfo);
        res.setCode(Constant.SUCCESS_RETUEN_CODE);
        res.setData(rtn);
        log.info(res.toString());
        return res;
    }

    /**
     * 获取推荐商品
     * @return
     */
    @ResponseBody
    @RequestMapping("/getRecommendedProducts")
    public ResultObject<Object> getRecommendedProducts(){
        ResultObject<Object> res = new ResultObject<>();
        log.info("---------------getRecommendedProducts");
        JSONObject rtn = new JSONObject();
        List<JSONObject> commodityList = new ArrayList<>();
        List<RecommendCommodity> recommendCommodities = wxApiService.getRecommendedProducts();
        for (RecommendCommodity recommendCommodity :recommendCommodities){
            String s = JSONUtil.toJsonStr(recommendCommodity);
            JSONObject jsonObject = JSONUtil.parseObj(s);
            commodityList.add(jsonObject);
        }
        rtn.putOnce("commodityList",commodityList);
        res.setCode(Constant.SUCCESS_RETUEN_CODE);
        res.setData(rtn);
        log.info("---------执行getRecommendedProducts，res：" + res);
        return res;
    }

    /**
     * 获取所有理发师
     * @return
     */
    @ResponseBody
    @RequestMapping("/getAllBarber")
    public ResultObject<Object> getAllBarber(){
        ResultObject<Object> res = new ResultObject<>();
        log.info("---------------getAllBarber");
        JSONObject rtn = new JSONObject();
        List<JSONObject> barberList = new ArrayList<>();
        List<BarberInfo> barberInfos = wxApiService.getAllBarber();
        for (BarberInfo barberInfo :barberInfos){
            String s = JSONUtil.toJsonStr(barberInfo);
            JSONObject jsonObject = JSONUtil.parseObj(s);
            barberList.add(jsonObject);
        }
        rtn.putOnce("barberList",barberList);
        res.setCode(Constant.SUCCESS_RETUEN_CODE);
        res.setData(rtn);
        log.info("---------执行getRecommendedProducts，res：" + res);
        return res;
    }


    /**
     * 获取推荐理发师
     * @return
     */
    @ResponseBody
    @RequestMapping("/getRecommendBarber")
    public ResultObject<Object> getRecommendBarber(){
        ResultObject<Object> res = new ResultObject<>();
        log.info("---------------getRecommendBarber");
        JSONObject rtn = new JSONObject();
        List<JSONObject> barberList = new ArrayList<>();
        List<BarberInfo> barberInfos = wxApiService.getAllBarberByScore();
        for (BarberInfo barberInfo :barberInfos){
            String s = JSONUtil.toJsonStr(barberInfo);
            JSONObject jsonObject = JSONUtil.parseObj(s);
            barberList.add(jsonObject);
        }
        rtn.putOnce("barberList",barberList);
        res.setCode(Constant.SUCCESS_RETUEN_CODE);
        res.setData(rtn);
        log.info("---------执行getRecommendBarber，res：" + res);
        return res;
    }


    /**
     * 根据理发师编号获取理发师技能
     * @return
     */
    @ResponseBody
    @RequestMapping("/getSkillByBarberNo")
    public ResultObject<Object> getSkillByBarberNo(@RequestBody JSONObject param){
        ResultObject<Object> res = new ResultObject<>();
        log.info("---------------getSkillByBarberNo ，param："+param);
        JSONObject rtn = new JSONObject();
        String barberNo = param.getStr("barberNo");
        List<JSONObject> barberSkillList = new ArrayList<>();
        List<BarberSkill> barberSkills = wxApiService.getBarberSkillByBarberNo(barberNo);
        for (BarberSkill barberSkill  :barberSkills){
            String s = JSONUtil.toJsonStr(barberSkill);
            JSONObject jsonObject = JSONUtil.parseObj(s);
            barberSkillList.add(jsonObject);
        }
        rtn.putOnce("barberSkillList",barberSkillList);
        res.setCode(Constant.SUCCESS_RETUEN_CODE);
        res.setData(rtn);
        log.info("---------执行getRecommendBarber，res：" + res);
        return res;
    }

    /**
     * 评价
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping("/appraise")
    public ResultObject<Object> appraise(@RequestBody JSONObject param){
        ResultObject<Object> res = new ResultObject<>();
        log.info("---------------getSkillByBarberNo,param:"+param);
        String barberNo = param.getStr("barberNo");
        String scoreTmp = param.getStr("score");
        BigDecimal score = new BigDecimal(scoreTmp);
        boolean appraise = wxApiService.appraise(barberNo, score);
        if (appraise){
            res.setCode(Constant.SUCCESS_RETUEN_CODE);
            res.setMsg("评价成功");
        }else {
            res.setCode(Constant.FAILURE_RETUEN_CODE);
            res.setMsg("评价失败");
        }
        log.info("---------执行getRecommendBarber，res：" + res);
        return res;
    }


    /**
     * 预约
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping("/insertBook")
    public ResultObject<Object> insertBook(@RequestBody JSONObject param){
        ResultObject<Object> res = new ResultObject<>();
        log.info("---------------insertBook,param:"+param);
        //先校验理发师是否可预约
        boolean verify =  wxApiService.verifyBarber(param);
        if (verify){
            Integer insertBook = wxApiService.insertBook(param);
            if (insertBook >0){
                res.setCode(Constant.SUCCESS_RETUEN_CODE);
                res.setMsg("预约成功");
            }else {
                res.setCode(Constant.FAILURE_RETUEN_CODE);
                res.setMsg("预约");
            }
        }else {
            res.setCode(Constant.HASE_RETUEN_CODE);
            res.setMsg("当前理发师，当前时间段预约人数已满");
        }
        log.info("---------执行getRecommendBarber，res：" + res);
        return res;
    }


    @ResponseBody
    @RequestMapping("/getActivity")
    public ResultObject<Object> getActivity(){
        ResultObject<Object> res = new ResultObject<>();
        log.info("---------------getActivity");
        JSONObject rtn = new JSONObject();
        List<JSONObject> activeList = new ArrayList<>();
        List<ActivityInfo> activityInfos = wxApiService.getActivity();
        for (ActivityInfo activityInfo  :activityInfos){
            String s = JSONUtil.toJsonStr(activityInfo);
            JSONObject jsonObject = JSONUtil.parseObj(s);
            activeList.add(jsonObject);
        }
        rtn.putOnce("activeList",activeList);
        res.setCode(Constant.SUCCESS_RETUEN_CODE);
        res.setData(rtn);
        log.info("---------执行getActivity，res：" + res);
        return res;
    }



}
