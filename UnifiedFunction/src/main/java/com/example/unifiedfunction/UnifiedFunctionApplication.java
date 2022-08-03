package com.example.unifiedfunction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UnifiedFunctionApplication {

    public static void main(String[] args) {
        SpringApplication.run(UnifiedFunctionApplication.class, args);
        //统一功能处理，就像用户登录校验，统一异常处理

        //登录校验 ：通过 Spring 拦截器来实现用户的统一登陆验证
        // 1、实现自定义拦截器，实现 Spring 中的 HandlerInterceptor 接口的 preHandle 方法
        // 2、将自定义连接器加入到框架的配置中，并且设置拦截规则（一个项目当中可以有多个拦截器
        //  a）给当前类添加 @Configuration 注解
        //  b）实现 WebMvcConfigurer 接口
        //  c）重写 addInterceptors 方法

        //统一异常处理
        // 1、给当前的类上加 @ControllerAdvice 注解
        // 2、给方法加 @ExceptionHandler(xxx.class) 注解，添加异常返回的业务代码
    }

}
