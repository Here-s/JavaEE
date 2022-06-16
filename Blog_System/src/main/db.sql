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

-- 给表中插入数据，方便测试
insert into blog values (null, '第一篇博客', '从今天开始，我要认真敲代码', 1, now());
insert into blog values (null, '第二篇博客', '从今天开始，我要认真敲代码', 1, now());
insert into blog values (null, '第三篇博客', '从今天开始，我要认真敲代码', 2, now());
insert into blog values (null, '第四篇博客', '从今天开始，我要认真敲代码', 2, now());
insert into blog values (null, '第五篇博客', '从今天开始，我要认真敲代码', 3, now());
insert into blog values (null, '第二篇博客', '从今天开始，我还要认真敲代码', 3, now());
insert into blog values (null, '不挂科保佑', '一定一定一定一定一定不要挂科', 3, now());
insert into blog values (null, '不挂科保佑2', '# 保佑\n ### 一定不挂\n >再次保佑期末不要挂科', 1, now());

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


