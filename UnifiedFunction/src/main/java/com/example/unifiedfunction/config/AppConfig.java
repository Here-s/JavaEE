package com.example.unifiedfunction.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig implements WebMvcConfigurer {

    @Autowired
    private LoginIntercept loginIntercept;

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(loginIntercept).addPathPatterns("/**").//拦截所有的 url
//                excludePathPatterns("/user/login").//不拦截登录接口
//                excludePathPatterns("/user/reg").//不拦截注册接口
//                excludePathPatterns("/login.html").
//                excludePathPatterns("/reg.html").
//                excludePathPatterns("/**/*.js").
//                excludePathPatterns("/**/*.css").
//                excludePathPatterns("/**/*.png");
//    }

//    @Override
//    public void configurePathMatch(PathMatchConfigurer configurer) {
//        configurer.addPathPrefix("api", c -> true);//对所有的地址都要加上 api 前缀才能访问
//    }
}
