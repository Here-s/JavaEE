package com.example.onlinemusic.controller;

import com.example.onlinemusic.mapper.UserMapper;
import com.example.onlinemusic.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    /**
     * 登录
     * @param username
     * @param password
     */
    @RequestMapping("/login")
    public void login(@RequestParam String username, @RequestParam String password) {
        User userLogin = new User();
        userLogin.setUsername(username);
        userLogin.setPassword(password);
        User user = userMapper.login(userLogin);
        if (user != null) {
            System.out.println("登陆成功");
        } else {
            System.out.println("登陆失败");
        }
    }
}
