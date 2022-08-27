package com.example.gobang.game;

//客户端连接到游戏房间之后，服务器返回响应
public class GameReadyResponse {
    private String message;
    private boolean ok;
    private String reason;
    private String roomId;
    private int thisUserId;
    private int thatUserId;
    private int whiteUser;

    public int getThatUserId() {
        return thatUserId;
    }

    public void setThatUserId(int thatUserId) {
        this.thatUserId = thatUserId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public int getThisUserId() {
        return thisUserId;
    }

    public void setThisUserId(int thisUserId) {
        this.thisUserId = thisUserId;
    }

    public int getWhiteUser() {
        return whiteUser;
    }

    public void setWhiteUser(int whiteUser) {
        this.whiteUser = whiteUser;
    }
}
