package com.example.springtransaction.mapper;


import com.example.springtransaction.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int add(UserInfo userInfo);
}
