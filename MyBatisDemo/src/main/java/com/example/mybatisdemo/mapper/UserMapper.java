package com.example.mybatisdemo.mapper;

import com.example.mybatisdemo.model.UserInfo;
import org.apache.catalina.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper //就表示当前不是普通的接口了，是 mybatis 的接口了
public interface UserMapper {

    //删除
    public int delIds(List<Integer> ids);

    //使用 set，set 也可以让 trim 替换
    public int update2(UserInfo userInfo);

    //使用 where，并且去除掉最前面的一个 and，这里的 where 标签也可以用 trim 标签来替代
    public UserInfo getUserById2(@Param("id") Integer id);

    //所有参数都是 非必传参数，通过 trim 解决
    public int add3(UserInfo userInfo);


    //通过 动态 sql 来添加用户，photo 是非必要参数
    public int add2(UserInfo userInfo);

    //动态 SQL 可以避免繁琐的拼接 SQL，可以避免空格之类的，避免 非必传参数“
    // if 标签，判断一个参数是否有值，如果没值，就会隐藏 if 中的 sql
    //    <if test="username!=null"> username=${username} </if>
    // trim 标签，配合 if 来使用，最主要的作用就是去除 SQL 语句中多余的某个字符的
    //   假设每个列都需要 if 那么每个列之后都会有一个 逗号，所以 trim 就是去除最后一个 逗号
    // where 标签，主要实现查询中的 where 的 sql 替换，如果没有查询条件，就隐藏查询的 where
    //    可以自动去除最前面一个 and 字符，就是 拼接 的时候，多拼接的 anf
    // set 标签，用来修改内容，配合 if 来处理非必传参数，因为修改的字段可能是非必传的。
    //    可以去除最后一个 逗号
    // foreach 标签，是对集合进行循环的，比如删除多个内容，主要的属性
    //    collection：绑定方法参数中的集合，如 List，Set，Map 或数组对象
    //    item：遍历时的每一个对象
    //    open：语句块开头的字符串
    //    close：语句块结束的字符串
    //    separator：每次遍历之间间隔的字符串

    //查询用户及用户发表的所有文章
    public UserInfo getUserAndArticleByUid(@Param("uid") Integer uid);

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
