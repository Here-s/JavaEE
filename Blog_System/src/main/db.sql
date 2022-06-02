-- 编写建库建表的操作
create database if not exists blogsystem;

use blogsystem;

-- 创建博客表
drop table if exists blog;
create table blog (
    blogId int primary key auto_increment,
    title varchar(1024),
    content mediumtext,
    userId int,
    postTime datetime  -- 发布时间
);

-- 创建用户表
drop table if exists user;
create table user (
    userId int primary key auto_increment,
    username varchar(128) unique unique , -- 后期通过用户名登录，所以用户名不能相同
    password varchar(128)
);

insert into user values (null, 'zhangsan', '123');
insert into user values (null, 'lisi', '123');
insert into user values (null, 'wangwu', '123');


















-- 编写建库建表的 sql

create database if not exists java102_blog;

use java102_blog;

-- 创建一个博客表.
drop table if exists blog;
create table blog (
                      blogId int primary key auto_increment,
                      title varchar(1024),
                      content mediumtext,
                      userId int,         -- 文章作者的 id
                      postTime datetime   -- 发布时间
);

-- 创建一个用户表
drop table if exists user;
create table user (
                      userId int primary key auto_increment,
                      username varchar(128) unique,    -- 后续会使用用户名进行登录, 一般用于登录的用户名都是不能重复的.
                      password varchar(128)
);

insert into user values(null, 'zhangsan', '123');
insert into user values(null, 'lisi', '123');