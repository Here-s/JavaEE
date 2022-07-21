package com.example.mybatisdemo.mapper;

import com.example.mybatisdemo.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper //就表示当前不是普通的接口了，是 mybatis 的接口了
public interface UserMapper {
    //根据用户的 id 查询用户
    public UserInfo getUserById(@Param("id") Integer id);

    //更新
    public int update (@Param("id") Integer id, @Param("username") String username);

    //删除
    public int del(@Param("id") Integer id);

    //添加
    public int add(UserInfo userInfo);

    public int addGetId(UserInfo userInfo);
}
