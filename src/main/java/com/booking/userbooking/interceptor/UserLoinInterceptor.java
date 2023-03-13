package com.booking.userbooking.interceptor;


import com.booking.userbooking.pojo.BarberInfo;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * author : jiaze.chen
 * Date : 2023/1/29 15:30
 **/
public class UserLoinInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try{
            BarberInfo barberInfo = (BarberInfo) request.getSession().getAttribute("barber");
            if (barberInfo!=null){
                return true;
            } else {
                response.sendRedirect(request.getContextPath()+"/login/login.do");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
