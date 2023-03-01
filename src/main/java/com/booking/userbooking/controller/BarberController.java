package com.booking.userbooking.controller;

import com.booking.userbooking.http.request.AddBarberParam;
import com.booking.userbooking.pojo.BarberInfo;
import com.booking.userbooking.service.BarberInfoService;
import com.booking.userbooking.util.Constant;
import com.booking.userbooking.util.ResultObject;
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
 * Date : 2023/2/21 16:23
 **/
@Slf4j
@Controller
@RequestMapping("/barber")
public class BarberController {

    @Autowired
    private BarberInfoService barberInfoService;

    @RequestMapping("/index.do")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("/barber/barber-list");
        return mav;
    }

    @RequestMapping("/add.do")
    public ModelAndView add() {
        ModelAndView mav = new ModelAndView("/barber/barberAdd");
        return mav;
    }

    @RequestMapping("/edit.do")
    public ModelAndView edit() {
        ModelAndView mav = new ModelAndView("/barber/barberEdit");
        return mav;
    }

    @RequestMapping("/barberInfo.do")
    public ModelAndView barberInfo() {
        ModelAndView mav = new ModelAndView("/barber/barber-Info");
        return mav;
    }

    @ResponseBody
    @RequestMapping("/getAllBarberInfo")
    public ResultObject<List<BarberVo>> getAllBarberInfo(@RequestParam("limit") int limit, @RequestParam("page") int page, HttpServletRequest request, @RequestParam(name = "param",required = false) String param){
        log.info("-------------------进入getAllBarberInfo方法" + "limit:" + limit + " page:" + page + " param:" + param);
        BarberInfo barberInfo = (BarberInfo) request.getSession().getAttribute("barber");
        PageInfo<BarberVo> pageInfo = barberInfoService.getAllBarberInfo(barberInfo,param,page,limit);
        ResultObject<List<BarberVo>> res = new ResultObject<>();
        res.setCode(Constant.SUCCESS_RETUEN_CODE);
        res.setData(pageInfo.getList());
        res.setMsg("查询成功");
        res.setCount(pageInfo.getTotal());
        log.info("----------------执行getAllBarberInfo,res:" + res);
        return res;
    }

    @ResponseBody
    @RequestMapping("/addBarber")
    public ResultObject<Object> addBarber(AddBarberParam param,HttpServletRequest request){
        log.info("---------------进入addBarber方法 param:" + param);
        List<BarberInfo> barberInfoList = barberInfoService.selectBarberByBarberNo(param);
        ResultObject<Object> res = new ResultObject<>();
        if (CollectionUtils.isEmpty(barberInfoList)){
            BarberInfo barberInfo = (BarberInfo) request.getSession().getAttribute("barber");
            int tmp = barberInfoService.addBarber(param,barberInfo);
            if (tmp == 0){
                res.setCode(Constant.FAILURE_RETUEN_CODE);
                res.setMsg("添加理发师信息失败");
            } else {
                res.setCode(Constant.SUCCESS_RETUEN_CODE);
                res.setMsg("增加理发师信息成功");
            }
        } else{
            res.setCode(Constant.HASE_RETUEN_CODE);
            res.setMsg("理发师编号已存在");
        }
        log.info("---------------执行addBarber方法  res" + res);
        return res;
    }

    @ResponseBody
    @RequestMapping("/editBarber")
    public ResultObject<Object> editBarber(AddBarberParam param,HttpServletRequest request){
        log.info("---------------进入editBarber方法 param:" + param);
        BarberInfo barberInfo = (BarberInfo) request.getSession().getAttribute("barber");
        ResultObject<Object> res = new ResultObject<>();
        int tmp = barberInfoService.editBarber(param,barberInfo);
        if (tmp == 0){
            res.setCode(Constant.FAILURE_RETUEN_CODE);
            res.setMsg("修改理发师信息失败");
        } else {
            res.setCode(Constant.SUCCESS_RETUEN_CODE);
            res.setMsg("修改理发师信息成功");
        }
        log.info("---------------执行editBarber方法  res" + res);
        return res;
    }

    @ResponseBody
    @RequestMapping("/deleteBarber")
    public ResultObject<Object> deleteBarber(@RequestParam("barberNo") String barberNo){
        log.info("---------------进入deleteBarber方法 barberNo:" + barberNo);
        Integer result = barberInfoService.deleteBarberByBarberNo(barberNo);
        ResultObject<Object> res = new ResultObject<>();
        if (result == 0){
            res.setCode(Constant.FAILURE_RETUEN_CODE);
            res.setMsg("删除理发师信息失败");
        }else{
            res.setCode(Constant.SUCCESS_RETUEN_CODE);
            res.setMsg("删除理发师信息成功");
        }
        log.info("---------------执行deleteStudent方法  res:" + res);
        return res;
    }

    @ResponseBody
    @RequestMapping("/getBarberInfo")
    public ResultObject<BarberInfo> getTeacherInfo(HttpServletRequest request){
        ResultObject<BarberInfo> res = new ResultObject<>();
        BarberInfo barberInfo = (BarberInfo) request.getSession().getAttribute("barber");
        log.info("---------------进入getBarberInfo方法 BarberInfo:" + barberInfo);
        BarberInfo barberInfo1 = barberInfoService.getBarberInfo(barberInfo);
        if (barberInfo1 == null){
            res.setCode(Constant.FAILURE_RETUEN_CODE);
            res.setMsg("获取教练信息失败");
        } else {
            res.setCode(Constant.SUCCESS_RETUEN_CODE);
            res.setMsg("获取教练信息成功");
            res.setData(barberInfo1);
        }
        log.info("---------------执行getInstructorInfo方法  res:" + res);
        return res;
    }


}
