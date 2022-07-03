package com.beans;

import org.springframework.stereotype.Component;

@Component
public class UserComponent {
    public void sayHi() {
        System.out.println("hello Component");
    }
}
