package com.example.springmvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringMvcApplication {

    public static void main(String[] args) {
        //MVC 就是 Model View Controller 的缩写，就是 模型，视图，控制器。用户拿到的数据，是服务器处理渲染之后的。
        //视图分为两种：
        // 1、服务器端的视图
        // 2、客户端的视图

        //MVC 是一种设计思想，SpringMVC 是一个具体的实现框架
        //Spring MVC 是一个基于 MVC 设计模式和 Servlet API 实现的 Web 项目，同时 Spring MVC 又是 Spring
        // 框架中的一个 web 模块，它是随着 Spring 的诞生而存在的一个框架

        //SpringMVC：
        //1、实现用户和程序的映射（在浏览器输入 URL 地址之后，能够在程序中找到相应的方法）
        // a）使用 @RequestMapping。@RequestMapping默认情况下，既支持 POST，也支持 GET
        //   @RequestMapping 参数扩展（只支持某种类型的的请求方式，比如 POST 之类的
        // b）可以直接使用 @POSTMapping
        //2、服务器端要得到用户的请求参数
        // a）获取单个参数
        // b）获取多个参数
        // c）获取对象
        // d）服务器端实现 JSON 数据的接收，需要使用 @RequestBody 注解
        // e）从 URL 地址当中获取参数 @PathVariable
        // f）上传文件 @RequestPart
        // g）获取 Cookie 和 Session/header
        //   1、servlet 获取 cookie 的方法
        //   2、使用 @CookieValue 注解
        // h）获取 header
        //   1、通过 servlet
        //   2、通过 @RequestHeader 注解
        // i）存储和获取 Session
        //   1、存储 Session，servlet 和 SpringMVC 是一样的
        //   2、获取 Session， servlet 方法。 SpringMVC 方法：@SessionAttribute 注解
        //3、服务器要将结果返回给用户（前端）
        // a）通过 @ResponseBody 注解
        // b）使用 @RestController 注解


        //热部署，就是自动重启 SpringBoot 项目，通过热部署，就可以“实时”开发了

        //请求转发 和 请求重定向
        // 1、请求转发：forward
        // 2、请求重定向：redirect。重定向的请求 发生在客户端。
        SpringApplication.run(SpringMvcApplication.class, args);
    }

}
