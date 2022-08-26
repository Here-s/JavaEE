package com.example.gobang.game;

import com.example.gobang.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

//匹配器
@Component
public class Matcher {
    //创建匹配队列

    //普通等级
    private Queue<User> normalQueue = new LinkedList<>();
    //中级等级
    private Queue<User> highQueue = new LinkedList<>();
    //高级等级
    private Queue<User> veryHighQueue = new LinkedList<>();

    @Autowired
    private OnlineUserManager onlineUserManager;

    private ObjectMapper objectMapper;

    //匹配队列的方法
    public void add(User user) {
        if (user.getScore() < 1500) {
            synchronized (normalQueue) {
                normalQueue.offer(user);
                normalQueue.notify();
            }
            System.out.println("玩家：" + user.getUsername() + " 加入到了 normalQueue 中！");
        } else if (user.getScore() >= 1500 && user.getScore() < 2000) {
            synchronized (highQueue) {
                highQueue.offer(user);
                highQueue.notify();
            }
            System.out.println("玩家：" + user.getUsername() + " 加入到了 highQueue 中！");
        } else {
            synchronized (veryHighQueue) {
                veryHighQueue.offer(user);
                veryHighQueue.notify();
            }
            System.out.println("玩家：" + user.getUsername() + " 加入到了 veryHighQueue 中！");
        }
    }

    //点击停止匹配的时候，就从匹配队列删除
    public void remove(User user) {
        if (user.getScore() < 1500) {
            synchronized (normalQueue) {
                normalQueue.remove(user);
            }
            System.out.println("玩家：" + user.getUsername() + " 退出了 normalQueue 中！");
        } else if (user.getScore() >= 1500 && user.getScore() < 2000) {
            synchronized (highQueue) {
                highQueue.remove(user);
            }
            System.out.println("玩家：" + user.getUsername() + " 退出了 highQueue 中！");
        } else {
            synchronized (veryHighQueue) {
                veryHighQueue.remove(user);
            }
            System.out.println("玩家：" + user.getUsername() + " 退出了 veryHighQueue 中！");
        }
    }

    public Matcher() {
        //创建三个线程，针对三个匹配队列进行操作
        Thread t1 = new Thread() {
            @Override
            public void run() {
                //扫描 normalQueue
                while (true) {
                    handlerMatch(normalQueue);
                }
            }
        };
        t1.start();

        Thread t2 = new Thread() {
            @Override
            public void run() {
                //扫描 highQueue
                while (true) {
                    handlerMatch(highQueue);
                }
            }
        };
        t2.start();

        Thread t3 = new Thread() {
            @Override
            public void run() {
                //扫描 veryHighQueue
                while (true) {
                    handlerMatch(veryHighQueue);
                }
            }
        };
        t3.start();
    }

    private void handlerMatch(Queue<User> matchQueue) {
        synchronized (matchQueue) {
            try {
                //检测对了中的元素个数是否达到2，如果增加一个元素，任然还不能匹配，所以就使用 while
                while (matchQueue.size() < 2) {
                    matchQueue.wait();
                }
                //取出两个玩家
                User player1 = matchQueue.poll();
                User player2 = matchQueue.poll();
                System.out.println("匹配出的两个玩家：" + player1.getUsername() + " " + player2.getUsername());
                //获取到玩家的 websocket 会话，然后告诉玩家匹配到了
                WebSocketSession session1 = onlineUserManager.getFromGameHall(player1.getUserid());
                WebSocketSession session2 = onlineUserManager.getFromGameHall(player2.getUserid());
                //正常是在线的，因为之前判断过，这里再次判定
                if (session1 == null) {
                    //如果一个玩家不在线，就把另外一个玩家再放回去
                    matchQueue.offer(player2);
                    return;
                }
                if (session2 == null) {
                    //如果一个玩家不在线，就把另外一个玩家再放回去
                    matchQueue.offer(player1);
                    return;
                }
                //之前判断过，但还是再次判断是否多开
                if (session1 == session2) {
                    matchQueue.offer(player1);
                    return;
                }

                //把玩家放到一个匹配房间里面


                //告诉玩家匹配到对手了
                MatchResponse response1 = new MatchResponse();
                response1.setOk(true);
                response1.setMessage("matchSuccess");
                String json1 = objectMapper.writeValueAsString(response1);
                session1.sendMessage(new TextMessage(json1));

                MatchResponse response2 = new MatchResponse();
                response2.setOk(true);
                response2.setMessage("matchSuccess");
                String json2 = objectMapper.writeValueAsString(response2);
                session1.sendMessage(new TextMessage(json2));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
