package com.example.gobang.mapper;


import com.example.gobang.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    //插入用户，用于注册
    void insert(User user);

    //根据用户名查询用户的信息，用来登陆
    User selectByName(String username);

    //比赛常数 + 1，或胜场数 + 1，分数 + 50
    void userWin(int userId);
    //比赛场数 + 1，获胜场数不变，分数 - 50
    void userLose(int userId);
}
