package com.booking.userbooking.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * author : jiaze.chen
 * Date : 2023/2/21 14:55
 **/
@Configuration
public class FileUploadConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //获取文件的真实路径
        String path = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\images\\";
        //images/**是对应resource下工程目录
        registry.addResourceHandler("/images/**").addResourceLocations("file:"+path);
    }
}
