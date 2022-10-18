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
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 登录
     *
     * @param username
     * @param password
     */
    @RequestMapping("/login")
    public ResponseBodyMessage<User> login(@RequestParam String username, @RequestParam String password,
                                           HttpServletRequest request) {
        User user = userMapper.selectByName(username);
        if (user == null) {
            System.out.println("登陆失败");
            return new ResponseBodyMessage<>(-1, "用户名或密码错误", user);
        } else {
            boolean flag = bCryptPasswordEncoder.matches(password, user.getPassword());
            if (!flag) {
                return new ResponseBodyMessage<>(-1, "用户名或密码错误", user);
            }
            request.getSession().setAttribute(Constant.USERINFO_SESSION_KEY, user);
            return new ResponseBodyMessage<>(0, "登陆成功", user);
        }
    }

    @RequestMapping("/userinfo")
    public ResponseBodyMessage<User> userInfo(HttpServletRequest request) {
        HttpSession httpSession = request.getSession(false);
        if (httpSession == null || httpSession.getAttribute(Constant.USERINFO_SESSION_KEY) == null) {
            System.out.println("获取用户信息失败！");
            return null;
        }
        User user = (User) httpSession.getAttribute(Constant.USERINFO_SESSION_KEY);
        return new ResponseBodyMessage<>(0, "成功获取到用户信息", user);
    }

    @RequestMapping("/register")
    public ResponseBodyMessage<Boolean> register(@RequestParam String username, @RequestParam String password1,
                                                 @RequestParam String password2) {
        System.out.println("进行注册！");
        if (password1.equals(password2)) {
            //对注册密码进行加密
            String password = bCryptPasswordEncoder.encode(password1);
            boolean reg = userMapper.registerUser(username, password);
            if (!reg) {
                System.out.println("注册失败");
                return new ResponseBodyMessage<>(-1, "注册失败！", false);
            } else {
                return new ResponseBodyMessage<>(0, "注册成功！", true);
            }
        }
        return new ResponseBodyMessage<>(-1, "两次密码不一样，注册失败！", false);
    }

    //注册管理员
    @RequestMapping("/registeradmin")
    public ResponseBodyMessage<Boolean> registerAdmin(@RequestParam String username, @RequestParam String password1,
                                                      @RequestParam String password2) {
        System.out.println("管理员进行注册！");
        if (password1.equals(password2)) {
            //对注册密码进行加密
            String password = bCryptPasswordEncoder.encode(password1);
            boolean reg = userMapper.registerUserAdmin(username, password, 1);
            if (!reg) {
                System.out.println("注册失败");
                return new ResponseBodyMessage<>(-1, "注册失败！", false);
            } else {
                return new ResponseBodyMessage<>(0, "注册成功！", true);
            }
        }
        return new ResponseBodyMessage<>(-1, "两次密码不一样，注册失败！", false);
    }
}