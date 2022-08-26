package com.example.gobang.game;

import com.example.gobang.model.User;

import java.util.UUID;

//游戏房间
public class Room {
    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public User getUser2() {
        return user2;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
    }

    //使用字符串类型表示，用来生成唯一值
    private String roomId;

    private User user1;
    private User user2;

    public Room() {
        //使用唯一字符串表示房间 id
        //使用 UUID 作为房间 ID
        roomId = UUID.randomUUID().toString();
    }
}
