package com.example.mybatisdemo.mapper;

import com.example.mybatisdemo.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper //就表示当前不是普通的接口了，是 mybatis 的接口了
public interface UserMapper {

    //模糊查询
    public List<UserInfo> getListByName(@Param("username") String name);

    //登录
    public UserInfo login(@Param("username") String username,
                            @Param("password") String password);

    //根据用户的 id 查询用户
    public UserInfo getUserById(@Param("id") Integer id);

    //根据全名进行查询
    public UserInfo getUserByFullName(@Param("username") String username);

    //获取列表，根据创建时间进行倒序或正序查询
    public List<UserInfo> getOrderList(@Param("order") String order);

    //更新
    public int update (@Param("id") Integer id, @Param("username") String username);

    //删除
    public int del(@Param("id") Integer id);

    //添加
    public int add(UserInfo userInfo);

    public int addGetId(UserInfo userInfo);
}
