package com.example.unifiedfunction.config;


import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice //当前是针对 controller 的通知类
public class MyExceptionAdvice {

    @ExceptionHandler(ArithmeticException.class)
    public HashMap<String, Object> arithmeticExceptionAdvice(ArithmeticException e) {
        HashMap<String, Object> result = new HashMap<>();
        result.put("state", -1);
        result.put("data", null);
        result.put("msg", "算术异常：" + e.getMessage());
        return result;
    }

    @ExceptionHandler(NullPointerException.class)
    public HashMap<String, Object> nullPointerExceptionAdvice(NullPointerException e) {
        HashMap<String, Object> result = new HashMap<>();
        result.put("state", -1);
        result.put("data", null);
        result.put("msg", "空指针异常：" + e.getMessage());
        return result;
    }
}
