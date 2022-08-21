package com.example.gobang.mapper;


import com.example.gobang.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    //插入用户，用于注册
    void insert(User user);

    //根据用户名查询用户的信息，用来登陆
    User selectByName(String username);
}
