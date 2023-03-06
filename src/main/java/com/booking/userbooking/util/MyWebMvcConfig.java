package com.booking.userbooking.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * author : jiaze.chen
 * Date : 2023/2/21 11:35
 **/
@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {
    @Bean
    public MyInterceptor myInterceptor(){
        return new MyInterceptor();
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //拦截处理操作的匹配路径
        //放开静态拦截
        registry.addInterceptor(myInterceptor())
                //拦截所有路径
                .addPathPatterns("/**")
                //排除路径
                .excludePathPatterns("/login.html","/login/**","/admin/**","/js/**","/api/**");
    }
}
