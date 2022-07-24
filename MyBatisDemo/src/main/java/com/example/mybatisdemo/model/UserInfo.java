package com.example.mybatisdemo.model;

import lombok.Data;

@Data
public class UserInfo {
    private int id;
    private String name;
    private String password;
    private String photo;
    private String createtime;
    private String updatetime;
    private int state;
}
