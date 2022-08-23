package com.example.gobang.api;

import com.example.gobang.game.MatchResponse;
import com.example.gobang.game.OnlineUserManager;
import com.example.gobang.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

//处理匹配过程中的 websocket 请求
@Component
public class MatchAPI extends TextWebSocketHandler {
    private ObjectMapper objectMapper;

    @Autowired
    private OnlineUserManager onlineUserManager;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        //玩家上线，加入到 websocket 当中
        //获取当前用户身份，之前在拦截器当中加入了 httpsession
        // 这里直接拿就行了

        //登录中，已经有了 user 的数据，这里直接拿对象就行了
        // 但是用户可能为空
        try {
            User user = (User) session.getAttributes().get("user");
            //设置玩家为在线状态
            onlineUserManager.enterGameHall(user.getUserid(), session);
            System.out.println("玩家：" + user.getUsername() + " 进入游戏大厅！");
        } catch (NullPointerException e) {
            e.printStackTrace();
            //用户未登录，返回 websocket 信息
            MatchResponse response = new MatchResponse();
            response.setOk(false);
            response.setReason("用户未登录！不能进行匹配！");
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(response)));
        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        try {
            //玩家下线，从 websocket 当中删除
            User user = (User) session.getAttributes().get("user");
            onlineUserManager.exitGameHall(user.getUserid());
        } catch (NullPointerException e) {
            e.printStackTrace();
            //用户未登录，返回 websocket 信息
            MatchResponse response = new MatchResponse();
            response.setOk(false);
            response.setReason("用户未登录！不能进行匹配！");
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(response)));
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        try {
            //玩家下线，从 websocket 当中删除
            User user = (User) session.getAttributes().get("user");
            onlineUserManager.exitGameHall(user.getUserid());
        } catch (NullPointerException e) {
            e.printStackTrace();
            //用户未登录，返回 websocket 信息
            MatchResponse response = new MatchResponse();
            response.setOk(false);
            response.setReason("用户未登录！不能进行匹配！");
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(response)));
        }
    }
}
