package com.example.mybatisdemo.model;

import lombok.Data;

@Data
public class ArticleInfo {
    private int id;
    private String title;
    private String content;
    private String createtime;
    private String updatetime;
    private int uid;
    private int rcount;
    private int state;
    private UserInfo userInfo;
}
