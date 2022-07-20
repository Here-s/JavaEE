package com.example.springmvc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalcController {

     @RequestMapping("/calc")
    public String calc(Integer num1, Integer num2) {
         if (num1 == null || num2 == null) {
             return "<h1>参数错误！</h1><a href='javascript:history.go(-1);'>返回</a>";
         }
         return "<h1>结果：" + (num1 + num2) + "</h1><a href='javascript:history.go(-1);'>返回</a>";
    }
}
