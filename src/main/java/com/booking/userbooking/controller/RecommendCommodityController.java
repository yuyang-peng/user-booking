package com.booking.userbooking.controller;

import com.booking.userbooking.http.request.AddBarberParam;
import com.booking.userbooking.http.request.AddRecommendCommodityParam;
import com.booking.userbooking.pojo.BarberInfo;
import com.booking.userbooking.pojo.RecommendCommodity;
import com.booking.userbooking.service.RecommendCommodityService;
import com.booking.userbooking.util.Constant;
import com.booking.userbooking.util.ResultObject;
import com.booking.userbooking.vo.RecommendCommodityVo;
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
 * Date : 2023/2/23 14:48
 **/
@Slf4j
@Controller
@RequestMapping("/recommendCommodity")
public class RecommendCommodityController {
    @RequestMapping("/index.do")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("/recommend/recommend-list");
        return mav;
    }

    @RequestMapping("/add.do")
    public ModelAndView add() {
        ModelAndView mav = new ModelAndView("/recommend/recommendAdd");
        return mav;
    }

    @RequestMapping("/edit.do")
    public ModelAndView edit() {
        ModelAndView mav = new ModelAndView("/recommend/recommendEdit");
        return mav;
    }

    @Autowired
    private RecommendCommodityService recommendCommodityService;

    @ResponseBody
    @RequestMapping("/getAllRecommend")
    public ResultObject<List<RecommendCommodityVo>> getAllRecommend(@RequestParam("limit") int limit, @RequestParam("page") int page, @RequestParam(name = "param",required = false) String param){
        log.info("-------------------进入getAllRecommend方法" + "limit:" + limit + " page:" + page + " param:" + param);
        PageInfo<RecommendCommodityVo> pageInfo = recommendCommodityService.getAllRecommend(param,page,limit);
        ResultObject<List<RecommendCommodityVo>> res = new ResultObject<>();
        res.setCode(Constant.SUCCESS_RETUEN_CODE);
        res.setData(pageInfo.getList());
        res.setMsg("查询成功");
        res.setCount(pageInfo.getTotal());
        log.info("----------------执行getAllBarberInfo,res:" + res);
        return res;
    }

    @ResponseBody
    @RequestMapping("/addRecommend")
    public ResultObject<Object> addRecommend(AddRecommendCommodityParam param, HttpServletRequest request){
        log.info("---------------进入addRecommend方法 param:" + param);
        List<RecommendCommodity> recommendCommodities = recommendCommodityService.selectRecommendByRecommendNo(param);
        ResultObject<Object> res = new ResultObject<>();
        if (CollectionUtils.isEmpty(recommendCommodities)){
            BarberInfo barberInfo = (BarberInfo) request.getSession().getAttribute("barber");
            int tmp = recommendCommodityService.addRecommend(param,barberInfo);
            if (tmp == 0){
                res.setCode(Constant.FAILURE_RETUEN_CODE);
                res.setMsg("添加商品信息失败");
            } else {
                res.setCode(Constant.SUCCESS_RETUEN_CODE);
                res.setMsg("增加商品信息成功");
            }
        } else{
            res.setCode(Constant.HASE_RETUEN_CODE);
            res.setMsg("商品编号已存在");
        }
        log.info("---------------执行addRecommend方法  res" + res);
        return res;
    }

    @ResponseBody
    @RequestMapping("/editRecommend")
    public ResultObject<Object> editRecommend(AddRecommendCommodityParam param,HttpServletRequest request){
        log.info("---------------进入editRecommend方法 param:" + param);
        BarberInfo barberInfo = (BarberInfo) request.getSession().getAttribute("barber");
        ResultObject<Object> res = new ResultObject<>();
        int tmp = recommendCommodityService.editRecommend(param,barberInfo);
        if (tmp == 0){
            res.setCode(Constant.FAILURE_RETUEN_CODE);
            res.setMsg("修改商品信息失败");
        } else {
            res.setCode(Constant.SUCCESS_RETUEN_CODE);
            res.setMsg("修改商品信息成功");
        }
        log.info("---------------执行editBarber方法  res" + res);
        return res;
    }

    @ResponseBody
    @RequestMapping("/deleteRecommend")
    public ResultObject<Object> deleteRecommend(@RequestParam("recommendCommodityNo") String recommendCommodityNo){
        log.info("---------------deleteRecommend recommendCommodityNo:" + recommendCommodityNo);
        Integer result = recommendCommodityService.deleteRecommendByRecommendCommodityNo(recommendCommodityNo);
        ResultObject<Object> res = new ResultObject<>();
        if (result == 0){
            res.setCode(Constant.FAILURE_RETUEN_CODE);
            res.setMsg("删除商品信息失败");
        }else{
            res.setCode(Constant.SUCCESS_RETUEN_CODE);
            res.setMsg("删除商品信息成功");
        }
        log.info("---------------执行deleteStudent方法  res:" + res);
        return res;
    }
}
