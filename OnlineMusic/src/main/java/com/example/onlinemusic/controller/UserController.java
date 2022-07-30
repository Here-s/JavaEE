package com.example.onlinemusic.controller;

import com.example.onlinemusic.mapper.UserMapper;
import com.example.onlinemusic.model.User;
import com.example.onlinemusic.tools.Constant;
import com.example.onlinemusic.tools.ResponseBodyMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 登录
     * @param username
     * @param password
     */
    @RequestMapping("/login")
    public ResponseBodyMessage<User> login(@RequestParam String username, @RequestParam String password,
                                           HttpServletRequest request) {
        User user = userMapper.selectByName(username);
        if (user == null) {
            System.out.println("登陆失败");
            return new ResponseBodyMessage<>(-1,"用户名或密码错误",user);
        } else {
            boolean flag = bCryptPasswordEncoder.matches(password, user.getPassword());
            if (!flag) {
                return new ResponseBodyMessage<>(-1,"用户名或密码错误",user);
            }
            request.getSession().setAttribute(Constant.USERINFO_SESSION_KEY, user);
            return new ResponseBodyMessage<>(0,"登陆成功",user);
        }
    }
}
