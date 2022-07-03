package com.beans;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    public void sayHi() {
        System.out.println("hello Service");
    }
}
