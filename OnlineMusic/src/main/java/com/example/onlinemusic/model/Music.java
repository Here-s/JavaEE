package com.example.onlinemusic.model;

import lombok.Data;

/**
 * 音乐属性
 */
@Data
public class Music {
    private int id;
    private String title;
    private String singer;
    private String time;
    private String url;
    private int userId;
}
