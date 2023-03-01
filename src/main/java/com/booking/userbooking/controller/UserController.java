package com.booking.userbooking.controller;

import com.booking.userbooking.pojo.BarberInfo;
import com.booking.userbooking.pojo.UserInfo;
import com.booking.userbooking.service.UserInfoService;
import com.booking.userbooking.util.Constant;
import com.booking.userbooking.util.ResultObject;
import com.booking.userbooking.vo.BarberVo;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * author : jiaze.chen
 * Date : 2023/2/23 11:09
 **/
@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/index.do")
    public ModelAndView barberIndex() {
        ModelAndView mav = new ModelAndView("/user/user-list");
        return mav;
    }

    @Autowired
    private UserInfoService userInfoService;

    @ResponseBody
    @RequestMapping("/getAllUser")
    public ResultObject<List<UserInfo>> getAllUser(@RequestParam("limit") int limit, @RequestParam("page") int page,@RequestParam(name = "param",required = false) String param){
        log.info("-------------------进入getAllUser方法" + "limit:" + limit + " page:" + page + " param:" + param);
        PageInfo<UserInfo> pageInfo = userInfoService.getAllUser(param,page,limit);
        ResultObject<List<UserInfo>> res = new ResultObject<>();
        res.setCode(Constant.SUCCESS_RETUEN_CODE);
        res.setData(pageInfo.getList());
        res.setMsg("查询成功");
        res.setCount(pageInfo.getTotal());
        log.info("----------------执行getAllUser,res:" + res);
        return res;
    }
}
