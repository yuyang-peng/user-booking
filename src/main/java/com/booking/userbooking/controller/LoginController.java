package com.booking.userbooking.controller;

import cn.hutool.core.util.StrUtil;
import com.booking.userbooking.http.request.UserLoginParam;
import com.booking.userbooking.pojo.BarberInfo;
import com.booking.userbooking.service.BarberInfoService;
import com.booking.userbooking.util.Constant;
import com.booking.userbooking.util.ResultObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * author : jiaze.chen
 * Date : 2023/2/21 16:51
 **/
@Slf4j
@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private BarberInfoService barberInfoService;

    @RequestMapping("/login.do")
    public String login() {
       return "login";
    }

    @RequestMapping("/index.do")
    public String index() {
        return "index";
    }

    @RequestMapping("/barberIndex.do")
    public ModelAndView barberIndex() {
        ModelAndView mav = new ModelAndView("/barber/barber-index");
        return mav;
    }

    @ResponseBody
    @RequestMapping("/barberLogin")
    public ResultObject<List<BarberInfo>> barberLogin(HttpServletRequest request, UserLoginParam param){
        log.info("-----------------进入了barberLogin方法,param:" + param);
        List<BarberInfo> barberInfoList = barberInfoService.barberLogin(param);
        ResultObject<List<BarberInfo>> response = new ResultObject<>();
        if (CollectionUtils.isEmpty(barberInfoList)){
            response.setCode(Constant.FAILURE_RETUEN_CODE);
            response.setMsg("登录失败");
        } else {
            response.setCode(Constant.SUCCESS_RETUEN_CODE);
            request.getSession().setAttribute("barber", barberInfoList.get(0));
            response.setMsg("登录成功");
        }
        response.setData(barberInfoList);
        log.info("-----------------执行barberLogin结果为 " + response);
        return response;
    }

    @ResponseBody
    @RequestMapping("/barberLoginOut")
    public ResultObject<Object> barberLoginOut(HttpServletRequest request){
        ResultObject<Object> res = new ResultObject<>();
        request.getSession().removeAttribute("barber");
        res.setCode(Constant.SUCCESS_RETUEN_CODE);
        res.setMsg("退出成功");
        log.info("---------------执行barberLoginOut,res:"  + res);
        return res;
    }


    /**/

}
