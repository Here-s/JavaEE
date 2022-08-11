-- 设计表：user 表，  music 表，  lovemusic 表
-- user表应该有：id（不可重复） username   password
-- music表：id（不可重复），名字，作者，上传时间，存储位置，上传者
-- lovemusic表：id（不可重复），userid，musicid

create databases `onlinemusic` character set utf8;

-- user 表
create table `user` (
    `id` int primary key auto_increment ,
    `username` varchar(20) not null ,
    `password` varchar(500) not null
);
insert into user(username,password)
VALUES("张三","123");


-- music 表
create table `music` (
     `id` int primary key auto_increment ,
     `title` varchar(50) not null ,
     `singer` varchar(30) not null ,
     `time` varchar(20) not null ,
     `url` varchar(1000) not null ,
     `userid` int(15) not null
);

-- lovemusic 表
create table `lovemusic` (
     `id` int primary key auto_increment ,
     `user_id` int(15) not null ,
     `music_id` int(15) not null
);