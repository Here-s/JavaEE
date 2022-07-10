package com.example.springboot1;

import com.example.springboot1.model.ReadList;
import com.example.springboot1.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class UserController {

    @Value("${server.port}")//读取配置文件，只能读取多个。要读多个的话，就写多个 server
    private String port;

    @Autowired
    private Student student;

    @Resource
    private ReadList readList;

    @ResponseBody //返回一个非静态页面的数据
    @RequestMapping("/sayhi") //设置路由地址，一定要注意，路由地址全是小写
    public String sayHi() {
        return "hello world 端口号：" + port;
    }
}
