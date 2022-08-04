package com.example.unifiedfunction.controller;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/login")
    public boolean login(HttpServletRequest request, String username, String password) {
        boolean result = false;
        if (StringUtils.hasLength(username) && StringUtils.hasLength(password)) {
            if (username.equals("admin") && password.equals("admin")) {
                HttpSession session = request.getSession();
                session.setAttribute("userinfo", "userinfo");
                return true;
            }
        }
        return result;
    }

    @RequestMapping( "/reg")
    public int reg() {
        return 1;
    }

    @RequestMapping("/index")
    public String index(HttpServletRequest request, String username, String password) {
        return "hello index";
    }

    @RequestMapping("/index2")
    public String index2() {
        int num = 10/0;
        return "hello index2";
    }

    @RequestMapping("/index3")
    public String index3() {
        Object obj = null;
        System.out.println(obj.hashCode());
        return "hello index2";
    }

    @RequestMapping("/index4")
    public String index4() {
        String obj = "aaa";
        System.out.println(Integer.valueOf(obj));
        return "hello index2";
    }
}
