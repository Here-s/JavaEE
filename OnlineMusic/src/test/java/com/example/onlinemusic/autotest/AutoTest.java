package com.example.onlinemusic.autotest;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AutoTest {
    @Test
    @Order(1)
    void aaa() {
        System.out.println("aaa");
    }
    @Test
    @Order(2)
    void bbbbb() {
        System.out.println("bbb");
    }
    @Test
    @Order(3)
    void cccc() {
        System.out.println("ccc");
    }
    @Test
    @Order(4)
    void dddddd() {
        System.out.println("ddd");
    }
}
