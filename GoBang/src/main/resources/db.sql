create database if not exists gobang;

use gobang;

drop table if exists user;

create table user (
    userid     int primary key auto_increment,
    username   varchar(50) unique,
    password   varchar(50),
    score      int, -- 比赛积分
    totalcount int, -- 比赛总次数
    wincount int -- 获胜场数
);

insert into user values (null, 'zhangsan', '123', 1000, 0, 0);
insert into user values (null, 'lisi', '123', 1000, 0, 0);
insert into user values (null, 'wangwu', '123', 1000, 0, 0);