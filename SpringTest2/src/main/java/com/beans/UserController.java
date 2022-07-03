package com.beans;

import org.springframework.stereotype.Controller;

@Controller//注解不能省略，有注解就可以自动加入 Spring 容器
public class UserController {

    public void sayHi() {
        System.out.println("hello UserController");
    }
}
