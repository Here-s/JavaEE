package com.beans;

import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    public void sayHi() {
        System.out.println("hello Repository");
    }
}
