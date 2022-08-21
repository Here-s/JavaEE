package com.example.gobang.api;

import com.example.gobang.mapper.UserMapper;
import com.example.gobang.model.User;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class UserAPI {

    @Resource
    private UserMapper userMapper;

    @PostMapping("/login")
    @ResponseBody
    public Object login(String username, String password, HttpServletRequest request) {
        //根据 username 去数据库中进行查询，如果能找到匹配的用户，并且密码也一致，就认为登陆成功
        User user = userMapper.selectByName(username);
        System.out.println("login");
        if (user == null || !user.getPassword().equals(password)) {
            //登录失败
            return new User();
        }
        HttpSession session = request.getSession(true);
        session.setAttribute("user", user);
        return user;
    }

    @PostMapping("/register")
    @ResponseBody
    public Object register(String username, String password) {
        try {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            userMapper.insert(user);
            return user;
        } catch (org.springframework.dao.DuplicateKeyException e) {
            User user = new User();
            return user;
        }
    }

    @GetMapping("/userInfo")
    @ResponseBody
    public Object getUserInfo(HttpServletRequest request) {
        try {
            HttpSession session = request.getSession(false);
            User user = (User) session.getAttribute("user");
            return user;
        } catch (NullPointerException e) {
            return new User();
        }
    }
}
