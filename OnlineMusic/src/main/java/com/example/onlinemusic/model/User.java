package com.example.onlinemusic.model;

import lombok.Data;

/**
 * 用户类型
 */
@Data
public class User {
    private int id;
    private String username;
    private String password;
    private int admin;
}
