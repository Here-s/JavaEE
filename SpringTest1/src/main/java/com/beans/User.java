package com.beans;

public class User {

    public User () {
        System.out.println("加载了 User");
    }
    public void sayHi(String name) {
        System.out.println("你好："+ name);
    }
}
