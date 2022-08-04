package com.example.springtransaction.service;

import com.example.springtransaction.mapper.LogMapper;
import com.example.springtransaction.mapper.UserMapper;
import com.example.springtransaction.model.LogInfo;
import com.example.springtransaction.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Resource
    private LogMapper logMapper;

    public int add(UserInfo userInfo) throws InterruptedException {
        int result = userMapper.add(userInfo);
        System.out.println("添加用户：" + result);
        LogInfo logInfo = new LogInfo();
        logInfo.setName("添加用户");
        logInfo.setDesc("添加用户结果：" + result);
        int logResult = logMapper.add(logInfo);
        return result;
    }
}
