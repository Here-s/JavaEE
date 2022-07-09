package com.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

@Controller//注解不能省略，有注解就可以自动加入 Spring 容器
public class UserController {

    //注入 User 对象，并且打印。 使用 @Resource 注解就不用担心多个对象了。
    // 如果只让用 Autowired ，就通过使用 @Qualifier 就可以了，直接写在
    //  Autowired 下面就可以了
//    @Autowired
//    @Qualifier(value = "user")

//    @Resource(name = "user")//使用不同的 name 就是不同的对象
    private User user;

    public void sayHi() {
        System.out.println("hello UserController");
        System.out.println("User-> " + user);
    }

}
