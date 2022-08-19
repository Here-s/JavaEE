package com.example.gobang.model;

import lombok.Data;

@Data
public class User {
    private int userid;
    private String username;
    private String password;
    private int score;
    private int totalcount;
    private int wincount;

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTotalcount() {
        return totalcount;
    }

    public void setTotalcount(int totalcount) {
        this.totalcount = totalcount;
    }

    public int getWincount() {
        return wincount;
    }

    public void setWincount(int wincount) {
        this.wincount = wincount;
    }
}
