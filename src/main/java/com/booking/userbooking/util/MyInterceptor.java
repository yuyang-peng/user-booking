package com.booking.userbooking.util;


import com.booking.userbooking.pojo.BarberInfo;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * author : jiaze.chen
 * Date : 2023/2/21 10:35
 **/
public class MyInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        BarberInfo barberInfo = (BarberInfo) request.getSession().getAttribute("barber");
        if (barberInfo == null) {
            //拦截未登录请求,跳转到登录页面
            request.getRequestDispatcher("/login/login.do").forward(request, response);
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
