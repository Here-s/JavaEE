package com.example.mybatisdemo.mapper;

import com.example.mybatisdemo.model.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest
@Slf4j
class UserMapperTest {

    @Resource
    private UserMapper userMapper;

    @Test
    void getUserByFullName() {
        UserInfo userInfo = userMapper.getUserByFullName("张三");
        System.out.println(userInfo);
    }

    @Test
    void getOrderList() {
        List<UserInfo> list = userMapper.getOrderList("desc");
        log.info("列表" + list);
    }

    @Test
    void login() {
        String username = "admin";
        String password = "' or 1='1";
        UserInfo userInfo = userMapper.login(username, password);
        log.info("用户信息" + userInfo);
    }

    @Test
    void getListByName() {
        String username = "a";
        List<UserInfo> list = userMapper.getListByName(username);
        log.info("模糊查询列表" + list);
    }

    @Test
    void getUserById() {
        UserInfo userInfo = userMapper.getUserById(1);
        log.info("用户信息：" + userInfo);
    }

    @Test
    void getUserAndArticleByUid() {
        UserInfo userInfo = userMapper.getUserAndArticleByUid(2);
        log.info("用户详情 " + userInfo);
    }

    @Test
    void add2() {
        UserInfo userInfo = new UserInfo();
        userInfo.setName("张三");
        userInfo.setPassword("123");
        userInfo.setPhoto("123.png");
        int result = userMapper.add2(userInfo);
        log.info("添加用户的结果：" + result);
    }

    @Test
    void add3() {
        UserInfo userInfo = new UserInfo();
        userInfo.setName("张三");
        userInfo.setPassword("123");
        int result = userMapper.add3(userInfo);
        log.info("添加用户的结果：" + result);
    }

    @Test
    void getUserById2() {
        UserInfo userInfo = userMapper.getUserById2(1);
        log.info("用户信息：" + userInfo);
    }

    @Test
    void update2() {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(1);
        userInfo.setName("张三");
        int result = userMapper.update2(userInfo);
        log.info("修改结果：" + result);
    }

    @Test
    void delIds() {
        List<Integer> list = new ArrayList<>();
        list.add(8);
        list.add(9);
        int result = userMapper.delIds(list);
        log.info("批量删除：" + result);
    }
}