package com.example.gobang.game;

import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

//房间管理类，也要有唯一身份
@Component
public class RoomManager {
    private ConcurrentHashMap<String, Room> rooms = new ConcurrentHashMap<>();

    //分别是用户 ID，房间 ID
    private ConcurrentHashMap<Integer, String> userIdToRoomId = new ConcurrentHashMap<>();

    public void add(Room room, int userId1, int userId2) {
        rooms.put(room.getRoomId(), room);
        userIdToRoomId.remove(userId1);
        userIdToRoomId.remove(userId2);
    }

    public void remove(String roomId, int userId1, int userId2) {
        rooms.remove(roomId);
        userIdToRoomId.remove(userId1);
        userIdToRoomId.remove(userId2);
    }

    public Room getRoomByRoomId(String roomId) {
        return rooms.get(roomId);
    }

    public Room getRoomByUserId(int userId) {
        String roomId = userIdToRoomId.get(userId);
        if (roomId == null) {
            return null;
        }
        return rooms.get(roomId);
    }
}
