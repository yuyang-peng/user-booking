package com.booking.userbooking.interceptor;

import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * author : jiaze.chen
 * Date : 2023/1/29 14:51
 **/
public class LoginConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        InterceptorRegistration registration = registry.addInterceptor(new UserLoinInterceptor());
        registration.addPathPatterns("/**");
        registration.excludePathPatterns(
                "/login.html",
                "/**/*.js",
                "/**/*.css"
        );
    }
}
