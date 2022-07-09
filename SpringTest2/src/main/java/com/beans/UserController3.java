package com.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *根据 构造方法 实现 Bean 对象的注入，官方推荐
 */
@Controller
public class UserController3 {

    private UserService userService;

    @Autowired
    //类启动的时候，因为有了 Autowired，所以就从 Spring 当中读取 userService
    // 出来，然后把 userService 赋值给这里的属性.如果类中只有一个 构造方法，
    // 那么 @Autowired 注解可以省略。构造方法当中不能使用 @Resource
    public UserController3(UserService userService) {
        this.userService = userService;
    }

    public void sayHi() {
        userService.sayHi();
    }
}
