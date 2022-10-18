package com.example.onlinemusic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig implements WebMvcConfigurer {

    @Bean
    public BCryptPasswordEncoder getBCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 添加拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //登陆之后才能访问
        LoginInterceptor loginInterceptor = new LoginInterceptor();
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")
                //排除所有的JS
                .excludePathPatterns("/js/**.js")
                //排除images下所有的元素
                .excludePathPatterns("/images/**")
                .excludePathPatterns("/css/**.css")
                .excludePathPatterns("/fronts/**")
                .excludePathPatterns("/player/**")
                .excludePathPatterns("/login.html")
                .excludePathPatterns("/register.html")
                .excludePathPatterns("/registeradmin.html")
                //排除登录接口
                .excludePathPatterns("/user/login")
                .excludePathPatterns("/user/register")
                .excludePathPatterns("/user/registeradmin");
    }

}
