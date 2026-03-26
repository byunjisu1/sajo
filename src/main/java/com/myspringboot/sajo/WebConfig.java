package com.myspringboot.sajo;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 브라우저 주소창에 /sajo/upload/ 가 들어오면 D:/Temp/upload2/ 로 연결
//        registry.addResourceHandler("/upload/**")
//                .addResourceLocations("file:///D:/Temp/upload2/");
        
        // 혹시 모르니 upload2 도 추가
        registry.addResourceHandler("/upload2/**")
                .addResourceLocations("file:///D:/Temp/upload2/");
    }
}
