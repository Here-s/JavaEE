package com.example.gobang.game;

import com.example.gobang.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    //匹配队列的方法
    public void add(User user) {
        if (user.getScore() < 1500) {
            normalQueue.offer(user);
            System.out.println("玩家：" + user.getUsername() + " 加入到了 normalQueue 中！");
        } else if (user.getScore() >= 1500 && user.getScore() < 2000) {
            highQueue.offer(user);
            System.out.println("玩家：" + user.getUsername() + " 加入到了 highQueue 中！");
        } else {
            veryHighQueue.offer(user);
            System.out.println("玩家：" + user.getUsername() + " 加入到了 veryHighQueue 中！");
        }
    }

    //点击停止匹配的时候，就从匹配队列删除
    public void remove(User user) {
        if (user.getScore() < 1500) {
            normalQueue.remove(user);
            System.out.println("玩家：" + user.getUsername() + " 退出了 normalQueue 中！");
        } else if (user.getScore() >= 1500 && user.getScore() < 2000) {
            highQueue.remove(user);
            System.out.println("玩家：" + user.getUsername() + " 退出了 highQueue 中！");
        } else {
            veryHighQueue.remove(user);
            System.out.println("玩家：" + user.getUsername() + " 退出了 veryHighQueue 中！");
        }
    }

}
