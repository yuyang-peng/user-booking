package com.booking.userbooking.controller;

import com.booking.userbooking.pojo.BarberInfo;
import com.booking.userbooking.pojo.UserInfo;
import com.booking.userbooking.service.BookInfoService;
import com.booking.userbooking.util.Constant;
import com.booking.userbooking.util.ResultObject;
import com.booking.userbooking.vo.BookInfoVo;
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
 * Date : 2023/2/23 13:32
 **/
@Slf4j
@Controller
@RequestMapping("/book")
public class BookController {

    @RequestMapping("/index.do")
    public ModelAndView barberIndex() {
        ModelAndView mav = new ModelAndView("/book/book-list");
        return mav;
    }

    @Autowired
    private BookInfoService bookInfoService;

    @ResponseBody
    @RequestMapping("/getBookingByBarberNo")
    public ResultObject<List<BookInfoVo>> getBookingByBarberNo(@RequestParam("limit") int limit, @RequestParam("page") int page, HttpServletRequest request, @RequestParam(name = "param",required = false) String param){
        log.info("-------------------进入getBookingByBarberNo方法" + "limit:" + limit + " page:" + page + " param:" + param);
        BarberInfo barberInfo = (BarberInfo) request.getSession().getAttribute("barber");
        PageInfo<BookInfoVo> pageInfo = bookInfoService.getBookingByBarberNo(barberInfo,param,page,limit);
        ResultObject<List<BookInfoVo>> res = new ResultObject<>();
        res.setCode(Constant.SUCCESS_RETUEN_CODE);
        res.setData(pageInfo.getList());
        res.setMsg("查询成功");
        res.setCount(pageInfo.getTotal());
        log.info("----------------执行getAllUser,res:" + res);
        return res;
    }


}
