package com.example.gobang.api;

import com.example.gobang.mapper.UserMapper;
import com.example.gobang.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class UserAPI {

    @Resource
    private UserMapper userMapper;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/login")
    @ResponseBody
    public Object login(String username, String password, HttpServletRequest request) {
        //根据 username 去数据库中进行查询，如果能找到匹配的用户，并且密码也一致，就认为登陆成功
        User user = userMapper.selectByName(username);
        System.out.println("login");
        boolean flag = bCryptPasswordEncoder.matches(password, user.getPassword());
        if (user == null || flag == false) {
            //登录失败
            return new User();
        }
        HttpSession session = request.getSession(true);
        session.setAttribute("user", user);
        return user;
    }

    @PostMapping("/register")
    @ResponseBody
    public Object register(String username, String password1, String password2) {
        if (password1.equals(password2)) {
            try {
                User user = new User();
                //对注册密码加密
                String password =bCryptPasswordEncoder.encode(password1);
                user.setUsername(username);
                user.setPassword(password);
                userMapper.insert(user);
                return user;
            } catch (org.springframework.dao.DuplicateKeyException e) {
                User user = new User();
                return user;
            }
        } else {
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
