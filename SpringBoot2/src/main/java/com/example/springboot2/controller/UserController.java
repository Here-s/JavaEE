package com.example.springboot2.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class UserController {
    //1、先得到日志对象，一定要设置当前类的类型
    private final static Logger log = LoggerFactory.getLogger(UserController.class);

    @RequestMapping("/sayhi")
    public void sayHi() {
        //2、使用日志对象提供的打印方法进行日志打印
        log.trace("track 日志级别最小的一种，少许日志");
        log.debug("debug 调试阶段打印日志，调试日志");
        log.info("info 普通的打印信息，默认的日志级别");
        log.warn("warn 警告日志");
        log.error("error 错误日志");
    }
}
