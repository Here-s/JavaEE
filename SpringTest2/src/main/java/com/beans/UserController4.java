package com.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * 通过 Setter 实现 Bean 对象的注入
 */
@Controller
public class UserController4 {

    private UserService userService;

    @Autowired
    //@Resource 注解和 Autowired 基本差不多，这里也可以换成 @Resource。
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void sayHi() {
        userService.sayHi();
    }
}
