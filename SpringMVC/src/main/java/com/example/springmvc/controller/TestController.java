package com.example.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
//@RestController
public class TestController {

    @RequestMapping("/sayhi")
    public String sayHi() {
        //默认返回的是 hello 的页面，如果是 html 的话，就是返回 html 页面
        // 加上 ResponseBody 就可以返回数据了。
        //或者直接使用 @RestController
        return "hello";
    }

    /**
     * 请求转发实现方式1
     * @return
     */
    @RequestMapping("/forwa")
    public String myForward() {
        return "forward:/login.html";
    }

    @RequestMapping("/forwa1")
    public String myForward1() {
        return "/login.html";
    }

    /**
     * 请求转发实现方式2
     * @return
     */
    @RequestMapping("/forwa2")
    public void myForward2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/login.html").forward(request, response);
    }

    /**
     * 请求重定向1
     * @return
     */
    @RequestMapping("/rd")
    public String myRedirect() {
        return "redirect:/login.html";
    }

    @RequestMapping("/rd2")
    public void myRedirect2(HttpServletResponse response) throws IOException {
        response.sendRedirect("/login.html");
    }
}
