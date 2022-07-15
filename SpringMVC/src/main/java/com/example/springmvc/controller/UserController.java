package com.example.springmvc.controller;

import com.example.springmvc.model.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.jws.soap.SOAPBinding;

@Controller
@RequestMapping("/user") //类上面的 RequestMapping 可以省略
@ResponseBody
public class UserController {

    @RequestMapping("/sayhi")//方法上一定要加 RequestMapping
    public String sayHi() {
        return "hello";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/sayhi2")
    public String sayHi2() {
        return "hello sayHi2";
    }

    @PostMapping("/sayhi3")
    public String sayHi3() {
        return "hello sayHi3";
    }

    @GetMapping("/sayhi4")
    public String sayHi4() {
        return "hello sayHi4";
    }

    @RequestMapping("/getuserbyid")
    public UserInfo getUserById (Integer id) {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(id == null ? 0 : id);
        userInfo.setUsername("zhangsan");
        userInfo.setAge(18);
        return userInfo;
    }

    @RequestMapping("/login")
    public String login(String username, String password) {
        return "用户名 " + username + " 密码 " + password;
    }

    @RequestMapping("req")
    public String req(UserInfo userInfo) {
        return "用户信息" + userInfo;
    }

    //参数重命名，通过 @RequestParam 注解，就是重命名前端传过来的参数
    @RequestMapping("/login2")
    public String login2(@RequestParam(value = "name", required = false) String username, String password) {
        //这样就把 前端传来的 name，换成 username 了，但是前端必须传 name，不然就报错，
        // 所以就设置 required 为 false 就可以了
        return "用户名 " + username + " 密码 " + password;
    }
}
