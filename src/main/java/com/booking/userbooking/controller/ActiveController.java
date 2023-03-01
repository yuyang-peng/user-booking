package com.booking.userbooking.controller;

import com.booking.userbooking.http.request.AddActivityParam;
import com.booking.userbooking.http.request.AddBarberParam;
import com.booking.userbooking.pojo.ActivityInfo;
import com.booking.userbooking.pojo.BarberInfo;
import com.booking.userbooking.service.ActivityInfoService;
import com.booking.userbooking.util.Constant;
import com.booking.userbooking.util.ResultObject;
import com.booking.userbooking.vo.ActivityInfoVo;
import com.booking.userbooking.vo.BarberVo;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * author : jiaze.chen
 * Date : 2023/2/23 8:34
 **/
@Slf4j
@Controller
@RequestMapping("/active")
public class ActiveController {
    @RequestMapping("/index.do")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("/active/active-list");
        return mav;
    }

    @RequestMapping("/add.do")
    public ModelAndView add() {
        ModelAndView mav = new ModelAndView("/active/activeAdd");
        return mav;
    }

    @RequestMapping("/edit.do")
    public ModelAndView edit() {
        ModelAndView mav = new ModelAndView("/active/activeEdit");
        return mav;
    }

    @Autowired
    private ActivityInfoService activityInfoService;

    @ResponseBody
    @RequestMapping("/getAllActivity")
    public ResultObject<List<ActivityInfoVo>> getAllActivity(@RequestParam("limit") int limit, @RequestParam("page") int page, @RequestParam(name = "param",required = false) String param){
        log.info("-------------------进入getAllActivity方法" + "limit:" + limit + " page:" + page + " param:" + param);
        PageInfo<ActivityInfoVo> pageInfo = activityInfoService.getAllActivity(param,page,limit);
        ResultObject<List<ActivityInfoVo>> res = new ResultObject<>();
        res.setCode(Constant.SUCCESS_RETUEN_CODE);
        res.setData(pageInfo.getList());
        res.setMsg("查询成功");
        res.setCount(pageInfo.getTotal());
        log.info("----------------执行getAllActivity,res:" + res);
        return res;
    }

    @ResponseBody
    @RequestMapping("/addActivity")
    public ResultObject<Object> addActivity(AddActivityParam param, HttpServletRequest request){
        log.info("---------------进入addActivity方法 param:" + param);
        List<ActivityInfo> activityInfos = activityInfoService.selectActivityByActivityNo(param);
        ResultObject<Object> res = new ResultObject<>();
        if (CollectionUtils.isEmpty(activityInfos)){
            BarberInfo barberInfo = (BarberInfo) request.getSession().getAttribute("barber");
            int tmp = activityInfoService.addActivity(param,barberInfo);
            if (tmp == 0){
                res.setCode(Constant.FAILURE_RETUEN_CODE);
                res.setMsg("添加活动信息失败");
            } else {
                res.setCode(Constant.SUCCESS_RETUEN_CODE);
                res.setMsg("增加活动信息成功");
            }
        } else{
            res.setCode(Constant.HASE_RETUEN_CODE);
            res.setMsg("活动编号已存在");
        }
        log.info("---------------执行addActivity方法  res" + res);
        return res;
    }

    @ResponseBody
    @RequestMapping("/editActivity")
    public ResultObject<Object> editActivity(AddBarberParam param,HttpServletRequest request){
        log.info("---------------进入editActivity方法 param:" + param);
        BarberInfo barberInfo = (BarberInfo) request.getSession().getAttribute("barber");
        ResultObject<Object> res = new ResultObject<>();
        int tmp = activityInfoService.editActivity(param,barberInfo);
        if (tmp == 0){
            res.setCode(Constant.FAILURE_RETUEN_CODE);
            res.setMsg("修改活动信息失败");
        } else {
            res.setCode(Constant.SUCCESS_RETUEN_CODE);
            res.setMsg("修改活动信息成功");
        }
        log.info("---------------执行editActivity方法  res" + res);
        return res;
    }

    @ResponseBody
    @RequestMapping("/deleteActivity")
    public ResultObject<Object> deleteActivity(@RequestParam("activityNo") String activityNo){
        log.info("---------------进入deleteActivity方法 activityNo:" + activityNo);
        Integer result = activityInfoService.deleteActivityByActivityNo(activityNo);
        ResultObject<Object> res = new ResultObject<>();
        if (result == 0){
            res.setCode(Constant.FAILURE_RETUEN_CODE);
            res.setMsg("删除活动信息失败");
        }else{
            res.setCode(Constant.SUCCESS_RETUEN_CODE);
            res.setMsg("删除活动信息成功");
        }
        log.info("---------------执行deleteActivity方法  res:" + res);
        return res;
    }

    @ResponseBody
    @RequestMapping("/openActivity")
    public ResultObject<Object> openActivity(@RequestParam("activityNo") String activityNo){
        log.info("---------------进入openActivity方法 activityNo:" + activityNo);
        Boolean result = activityInfoService.openActivityByActivityNo(activityNo);
        ResultObject<Object> res = new ResultObject<>();
        if (!result){
            res.setCode(Constant.FAILURE_RETUEN_CODE);
            res.setMsg("启用活动失败");
        }else{
            res.setCode(Constant.SUCCESS_RETUEN_CODE);
            res.setMsg("启用活动成功");
        }
        log.info("---------------执行deleteActivity方法  res:" + res);
        return res;
    }

    @ResponseBody
    @RequestMapping("/closeActivity")
    public ResultObject<Object> closeActivity(@RequestParam("activityNo") String activityNo){
        log.info("---------------进入closeActivity方法 activityNo:" + activityNo);
        Boolean result = activityInfoService.closeActivityByActivityNo(activityNo);
        ResultObject<Object> res = new ResultObject<>();
        if (!result){
            res.setCode(Constant.FAILURE_RETUEN_CODE);
            res.setMsg("停用活动失败");
        }else{
            res.setCode(Constant.SUCCESS_RETUEN_CODE);
            res.setMsg("停用活动成功");
        }
        log.info("---------------执行deleteActivity方法  res:" + res);
        return res;
    }


}
