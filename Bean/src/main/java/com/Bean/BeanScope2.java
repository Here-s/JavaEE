package com.Bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BeanScope2 {

    @Autowired
    private User user;

    public User getUser() {
        return user;
    }
}
