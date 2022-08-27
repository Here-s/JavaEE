package com.example.gobang.api;

import com.example.gobang.game.MatchRequest;
import com.example.gobang.game.MatchResponse;
import com.example.gobang.game.Matcher;
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

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private OnlineUserManager onlineUserManager;

    @Autowired
    private Matcher matcher;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        //玩家上线，加入到 websocket 当中
        //获取当前用户身份，之前在拦截器当中加入了 httpsession
        // 这里直接拿就行了

        //登录中，已经有了 user 的数据，这里直接拿对象就行了
        // 但是用户可能为空
        try {
            User user = (User) session.getAttributes().get("user");

            //判断用户是否已登录，如果已登录，其他地方就不能登了
            if (onlineUserManager.getFromGameHall(user.getUserid()) != null ||
                onlineUserManager.getFromGameRoom(user.getUserid()) != null) {
                //已登录，通知客户端，不能登录
                MatchResponse response = new MatchResponse();
                response.setOk(false);
                response.setReason("当前账号已登录，不能再次登录！");
                session.sendMessage(new TextMessage(objectMapper.writeValueAsString(response)));
                return;
            }

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
        //实现匹配请求和停止匹配请求
        User user = (User) session.getAttributes().get("user");
        //获取到客户端给服务器发送的数据
        String payload = message.getPayload();
        //把 JSON 字符串转化成 Java 对象
        MatchRequest request = objectMapper.readValue(payload, MatchRequest.class);
        MatchResponse response = new MatchResponse();
        if (request.getMessage().equals("startMatch")) {
            //进入比配队列
            //把当前用户给加进匹配队列
            matcher.add(user);
            //玩家放进去之后，响应给前端
            response.setOk(true);
            response.setMessage("startMatch");
        } else if (request.getMessage().equals("stopMatch")) {
            //退出匹配队列
            //把当前用户给移除匹配队列
            matcher.remove(user);
            response.setOk(true);
            response.setMessage("stopMatch");
        } else {
            //非法情况
            response.setOk(false);
            response.setMessage("非法匹配请求");
        }
        String jsonString = objectMapper.writeValueAsString(response);
        session.sendMessage(new TextMessage(jsonString));
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        try {
            //玩家下线，从 websocket 当中删除
            User user = (User) session.getAttributes().get("user");
            WebSocketSession tempSession = onlineUserManager.getFromGameHall(user.getUserid());
            //当前玩家和之前成功上线的玩家一样，才能下线
            if (tempSession == session) {
                onlineUserManager.exitGameHall(user.getUserid());
            }
            //玩家匹配的时候，websocket 断开了，也移除匹配队列
            matcher.remove(user);
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
            WebSocketSession tmpSession = onlineUserManager.getFromGameHall(user.getUserid());
            if (tmpSession == session) {
                onlineUserManager.exitGameHall(user.getUserid());
            }
            //玩家匹配的时候，websocket 断开了，也移除匹配队列
            matcher.remove(user);
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
