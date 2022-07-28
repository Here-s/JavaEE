package com.example.springaop.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/sayhi")
    public String sayHi() {
        System.out.println("sayHi");
        return "hello word";
    }
    @RequestMapping("/sayhello")
    public String sayHello() {
        System.out.println("sayHello");
//        int num = 10/0; // 用来检测 抛出异常后通知
        return "word hello";
    }
}
