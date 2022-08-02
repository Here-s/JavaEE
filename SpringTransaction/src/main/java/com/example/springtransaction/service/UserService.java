package com.example.springtransaction.service;

import com.example.springtransaction.mapper.UserMapper;
import com.example.springtransaction.model.UserInfo;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public int add(UserInfo userInfo) {
        return userMapper.add(userInfo);
    }
}
