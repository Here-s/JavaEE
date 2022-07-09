package com.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * 根据属性实现 Bean 对象的注入
 */
@Controller
public class UserController2 {
    //对象注入方式 1：属性注入 @Autowired 要注入那个对象，就在 private 后面写类名字就好
    @Autowired //@Resource 注解和 Autowired 基本差不多，这里也可以换成 @Resource。
    private UserService userService;

    public void sayHi() {
        userService.sayHi();
    }
}
