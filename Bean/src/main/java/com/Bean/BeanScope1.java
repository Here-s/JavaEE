package com.Bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BeanScope1 {

    @Autowired
    private User user;

    public User getUser() {
        User user1 = user;
        user1.setName("李四");
        return user1;
    }
}
