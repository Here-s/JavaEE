package com.example.springboot1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBoot1Application {

    //SpringBoot 优点：
    //1、快速集成框架，SpringBoot 提供了启动添加依赖的功能，用于秒级集成各种框架
    //2、内置运行容器，无需配置 Tomcat 等 Web 容器，直接运行和部署程序
    //3、快速部署项目于，无需外部容器即可启动并运行项目
    //4、可以完全抛弃繁琐的 XML，使用注释和配置的方式进行开发
    //5、支持更多的监控的指标，可以更好的了解项目的运行情况
    public static void main(String[] args) {
        SpringApplication.run(SpringBoot1Application.class, args);
    }

}
