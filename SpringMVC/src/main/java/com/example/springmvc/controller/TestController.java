package com.example.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping("/sayhi")
    public String sayHi() {
        //默认返回的是 hello 的页面，如果是 html 的话，就是返回 html 页面
        // 加上 ResponseBody 就可以返回数据了。
        //或者直接使用 @RestController
        return "hello";
    }
}
