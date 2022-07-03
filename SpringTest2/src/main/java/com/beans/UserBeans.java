package com.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class UserBeans {

    @Bean //只使用一个 Bean 注解，是没办法把对象存储到容器中的，还需要一个 Component
    public User user1() {
        User user = new User();
        user.setId(1);
        user.setName("张三");
        return user;
    }
    public void sayHi() {
        System.out.println(" hello Bean");
    }
}
