package com.example.mybatisdemo.service;

import com.example.mybatisdemo.mapper.UserMapper;
import com.example.mybatisdemo.model.UserInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;
    public UserInfo getUserById(Integer id) {
        return userMapper.getUserById(id);
    }
}
