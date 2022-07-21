package com.example.mybatisdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyBatisDemoApplication {

    public static void main(String[] args) {
        //MyBatis 是一个优秀的 ORM（对象关系映射）持久层框架，实现数据库和项目的交互
        SpringApplication.run(MyBatisDemoApplication.class, args);
    }

}
