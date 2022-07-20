package com.example.springmvc.controller;

import com.example.springmvc.model.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

@Slf4j
@Controller
@RequestMapping("/user") //类上面的 RequestMapping 可以省略
@ResponseBody
public class UserController {

    @Value("${img.path}")
    private String imgPath;

    @RequestMapping("/cookie")
    public void getCookie(HttpServletRequest request) {
        //得到全部的 Cookie
        Cookie[] cookies = request.getCookies();
        for (Cookie item:cookies) {
            log.info("Cookie Name: " + item.getName() + " Cookie Value: " + item.getValue());
        }
    }

    @RequestMapping("/cookie2")
    public String getCookie2(@CookieValue("123") String cookie, @CookieValue("qwe") String qweCookie) {
        return "Cookie Value: " + cookie;
    }

    //获取 header
    @RequestMapping("/getheader")
    public String getHead(HttpServletRequest request) {
        return "header: " + request.getHeader(("User-agent"));
    }

    @RequestMapping("/getheader2")
    public String getHead2(@RequestHeader("User-Agent") String userAgent) {
        return "header: " + userAgent;
    }

    //存储 Session
    @RequestMapping("/setsess")
    public boolean setSession(HttpServletRequest request) {
        boolean result = false;
        //1、得到 HttpSession
        HttpSession session = request.getSession(true);
        //2、使用 setAtt 设置值
        session.setAttribute("userInfo", "userInfo");
        result = true;
        return result;
    }

    //获取session
    @RequestMapping("/getsession")
    public String getSession(HttpServletRequest request) {
        String result = null;
        //1、得到 HttpSession 对象
        HttpSession session = request.getSession(false);
            //false 就是如果有会话，就使用会话，如果没有的话，就不创建会话
        //2、getAtt 得到 session 信息
        if (session != null && session.getAttribute("userInfo") != null) {
            result = (String) session.getAttribute("userInfo");
        }
        return result;
    }
    @RequestMapping("/getsession2")
    public String getSession2(@SessionAttribute(value = "userInfo", required = false) String userInfo) {
        return "信息：" + userInfo;
    }

    @RequestMapping("/sayhi")//方法上一定要加 RequestMapping
    public String sayHi(HttpServletRequest request) {
        return "hello " + imgPath + request.getParameter("name");
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

    //模拟 json 提交
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

    @RequestMapping("/login3")
    public HashMap<String, Object> login3 (String username, String password) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        int state = 200;
        int data = -1;//data 等于 1，就表示登陆成功，否则登陆失败
        String msg = "未知错误1";
        if (StringUtils.hasLength(username) && StringUtils.hasLength(password)) {
            if (username.equals("admin") && password.equals("admin")) {
                data = 1;
                msg = "";
            } else {
                msg = "用户名或密码错误";
            }
        } else {//参数为空
            msg = "非法参数";
        }
        result.put("state", state);
        result.put("data", data);
        result.put("msg", msg);
        return result;
    }

    /**
     * 获取前端的 json 数据
     * @param userInfo
     * @return
     */
    @RequestMapping("/login4")
    public HashMap<String, Object> login4 (@RequestBody UserInfo userInfo) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        int state = 200;
        int data = -1;//data 等于 1，就表示登陆成功，否则登陆失败
        String msg = "未知错误1";
        if (StringUtils.hasLength(userInfo.getUsername()) && StringUtils.hasLength(userInfo.getPassword())) {
            if (userInfo.getUsername().equals("admin") && userInfo.getPassword().equals("admin")) {
                data = 1;
                msg = "";
            } else {
                msg = "用户名或密码错误";
            }
        } else {//参数为空
            msg = "非法参数";
        }
        result.put("state", state);
        result.put("data", data);
        result.put("msg", msg);
        return result;
    }

    //接收 JSON 格式的数据
    @RequestMapping("/req2")
    public String req2(@RequestBody UserInfo userInfo) {
        return "用户信息" + userInfo;
    }

    //从 URL 当中获取参数
    @RequestMapping("/Info/{id}/{name}")
    public String getInfo (String id, String name) {
        return "id: " + id +" name: " + name;
    }

    //上传文件
    @RequestMapping("/upImg")
    public boolean upImg(Integer uid, @RequestPart("img") MultipartFile file) {
        boolean result = false;
        //保存图片到本地目录（就是存到服务器），确定目录：imgpath
        //图片的名称（名称不能重复），UUID，时间戳可能会重复
        //获取原上传图片的格式
        String fileName = file.getOriginalFilename();//得到图片名称
        fileName = fileName.substring(fileName.lastIndexOf("."));//得到图片后缀
        fileName = UUID.randomUUID().toString() + fileName;
        try {
            file.transferTo(new File("D:/img/"));
            result = true;
        } catch (IOException e) {
            log.error("图片上传失败" + e.getMessage());
        }
        return result;
    }
}
