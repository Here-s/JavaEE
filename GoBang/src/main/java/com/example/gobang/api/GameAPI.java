package com.example.gobang.api;

import com.example.gobang.game.GameReadyResponse;
import com.example.gobang.game.OnlineUserManager;
import com.example.gobang.game.Room;
import com.example.gobang.game.RoomManager;
import com.example.gobang.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

@Component
public class GameAPI extends TextWebSocketHandler {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private RoomManager roomManager;

    @Autowired
    private OnlineUserManager onlineUserManager;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        GameReadyResponse response = new GameReadyResponse();

        //先获取到用户的身份信息，（从 HttpSession 拿到用户对象）
        User user = (User) session.getAttributes().get("user");
        if (user == null) {
            response.setOk(false);
            response.setReason("用户未登录");
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(response)));
            return;
        }

        //判断是否已经在房间
        Room room = roomManager.getRoomByUserId(user.getUserid());
        if (room == null) {
            //说明玩家还没进房间
            response.setOk(false);
            response.setReason("玩家没有进入房间");
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(response)));
            return;
        }

        //判断是否是多开
        if (onlineUserManager.getFromGameHall(user.getUserid()) != null
            || onlineUserManager.getFromGameRoom(user.getUserid()) != null) {
            //如果一个账号，一边在大厅，一边在房间，也当作多开
            response.setOk(false);
            response.setReason("不能多开账号！");
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(response)));
            return;
        }

        //没多开，设置为上线状态
        onlineUserManager.enterGameRoom(user.getUserid(), session);

        //把玩家加入到房间中。到这里的时候，说明已经跳转页面了
        if (room.getUser1() == null) {
            //第一个玩家还没进入房间，就把当前进入房间的玩家作为 user1
            room.setUser1(user);
            //先进入房间的玩家作为先手
            room.setWhiteUser(user.getUserid());
            System.out.println("玩家 " + user.getUsername() + " 已经准备就绪! 作为玩家1");
            return;
        }

        if (room.getUser2() == null) {
            //第二个玩家还没进入房间，就把当前进入房间的玩家作为 user2
            room.setUser2(user);
            System.out.println("玩家 " + user.getUsername() + " 已经准备就绪! 作为玩家2");
            //两个玩家都加入了，就给玩家都返回数据，说双方都准备好了
            // 通知玩家1
            noticeGameReady(room, room.getUser1(), room.getUser2());
            // 通知玩家2
            noticeGameReady(room, room.getUser2(), room.getUser1());
            return;
        }

        //如果还有玩家尝试连接同一个房间，就提示出错
        response.setOk(false);
        response.setReason("当前房间已满，您不能加入！");
        session.sendMessage(new TextMessage(objectMapper.writeValueAsString(response)));
    }

    private void noticeGameReady(Room room, User thisUser, User thatUser) throws IOException {
        GameReadyResponse response = new GameReadyResponse();
        response.setMessage("gameReady");
        response.setOk(true);
        response.setReason("");
        response.setRoomId(room.getRoomId());
        response.setThisUserId(thisUser.getUserid());
        response.setThatUserId(thatUser.getUserid());
        response.setWhiteUser(room.getWhiteUser());
        //把数据传给玩家
        WebSocketSession webSocketSession = onlineUserManager.getFromGameRoom(thisUser.getUserid());
        webSocketSession.sendMessage(new TextMessage(objectMapper.writeValueAsString(response)));
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        User user = (User) session.getAttributes().get("user");
        if (user == null) {
            //断开连接，不返回响应了
            return;
        }
        WebSocketSession exitSession = onlineUserManager.getFromGameRoom(user.getUserid());
        if (session == exitSession) {
            //再次判断，防止多开的时候，第二个用户退出，导致第一个用户也退出
            onlineUserManager.exitGameRoom(user.getUserid());
        }
        System.out.println("当前用户：" + user.getUsername() + " 游戏房间连接异常!");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        User user = (User) session.getAttributes().get("user");
        if (user == null) {
            //断开连接，不返回响应了
            return;
        }
        WebSocketSession exitSession = onlineUserManager.getFromGameRoom(user.getUserid());
        if (session == exitSession) {
            //再次判断，防止多开的时候，第二个用户退出，导致第一个用户也退出
            onlineUserManager.exitGameRoom(user.getUserid());
        }
        System.out.println("当前用户：" + user.getUsername() + " 离开游戏房间!");
    }
}
