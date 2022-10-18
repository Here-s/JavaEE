package com.example.onlinemusic.mapper;

import com.example.onlinemusic.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User login(User loginUser);

    User selectByName(String  username);

    Boolean registerUser(String username, String password);

    Boolean registerUserAdmin(String username, String password, int admin);
}
