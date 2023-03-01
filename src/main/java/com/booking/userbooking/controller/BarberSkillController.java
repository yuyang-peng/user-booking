package com.booking.userbooking.controller;

import com.booking.userbooking.http.request.AddBarberParam;
import com.booking.userbooking.http.request.UpdateSkillParam;
import com.booking.userbooking.pojo.BarberInfo;
import com.booking.userbooking.pojo.BarberSkill;
import com.booking.userbooking.service.BarberSkillService;
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
import java.util.Map;

/**
 * author : jiaze.chen
 * Date : 2023/2/22 14:10
 **/
@Slf4j
@Controller
@RequestMapping("/skill")
public class BarberSkillController {

    @Autowired
    private BarberSkillService barberSkillService;

    @RequestMapping("/index.do")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("/skill/skill-list");
        return mav;
    }

    @RequestMapping("/edit.do")
    public ModelAndView edit() {
        ModelAndView mav = new ModelAndView("/skill/skillEdit");
        return mav;
    }

    @ResponseBody
    @RequestMapping("/getAllBarberSkill")
    public ResultObject<List<BarberSkill>> getAllBarberSkill(@RequestParam("limit") int limit, @RequestParam("page") int page, @RequestParam(name = "param",required = false) String param){
        log.info("-------------------进入getAllBarberSkill方法" + "limit:" + limit + " page:" + page + " param:" + param);
        PageInfo<BarberSkill> pageInfo = barberSkillService.getAllBarberSkill(param,page,limit);
        ResultObject<List<BarberSkill>> res = new ResultObject<>();
        res.setCode(Constant.SUCCESS_RETUEN_CODE);
        res.setData(pageInfo.getList());
        res.setMsg("查询成功");
        res.setCount(pageInfo.getTotal());
        log.info("----------------执行getAllBarberInfo,res:" + res);
        return res;
    }

    @ResponseBody
    @RequestMapping("/getAllSkill")
    public List<Map<String, String>> getAllSkill(){
        log.info("-------------------进入getAllSkill方法");
        List<Map<String, String>> res = barberSkillService.getAllSkill();
        log.info("----------------执行getAllSkill,res:" + res);
        return res;
    }

    @ResponseBody
    @RequestMapping("/editSkill")
    public ResultObject<Object> editSkill(UpdateSkillParam param, HttpServletRequest request){
        log.info("---------------进入editSkill方法 param:" + param);
        BarberInfo barberInfo = (BarberInfo) request.getSession().getAttribute("barber");
        ResultObject<Object> res = new ResultObject<>();
        Boolean tmp = barberSkillService.editSkill(param,barberInfo);
        if (!tmp){
            res.setCode(Constant.FAILURE_RETUEN_CODE);
            res.setMsg("修改理技能失败");
        } else {
            res.setCode(Constant.SUCCESS_RETUEN_CODE);
            res.setMsg("修改理技能成功");
        }
        log.info("---------------执行editBarber方法  res" + res);
        return res;
    }


}
