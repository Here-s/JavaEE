package com.example.unifiedfunction.config;


import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 自定义用户登录拦截器
 */
@Component
public class LoginIntercept implements HandlerInterceptor {

    /**
     * 返回 true 表示拦截判断通过，可以访问后面的接口
     * 返回 false 表示拦截判断未通过，直接返回后面结果给前端
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //得到 HttpSession 对象
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("userinfo") != null) {
            //表示已经登录
            return true;
        }
        //表示未登录，然后就跳转到登陆页面
//        response.sendRedirect("/login.html");
        return false;
    }
}
