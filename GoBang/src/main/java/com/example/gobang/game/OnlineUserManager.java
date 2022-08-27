package com.example.gobang.game;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class OnlineUserManager {
    //记录用户是否是在线状态
    private ConcurrentHashMap<Integer, WebSocketSession> gameHall = new ConcurrentHashMap<>();

    //用来记录用户在游戏房间是否在线
    private ConcurrentHashMap<Integer, WebSocketSession> gameRoom = new ConcurrentHashMap<>();

    public void enterGameHall(int userId, WebSocketSession webSocketSession) {
        gameHall.put(userId, webSocketSession);
    }

    public void exitGameHall(int userId) {
        gameHall.remove(userId);
    }

    public WebSocketSession getFromGameHall(int userId) {
        return gameHall.get(userId);
    }

    public void enterGameRoom(int userId, WebSocketSession session) {
        gameRoom.put(userId, session);
    }

    public void exitGameRoom(int userId) {
        gameRoom.remove(userId);
    }

    public WebSocketSession getFromGameRoom(int userId) {
        return gameRoom.get(userId);
    }
}
