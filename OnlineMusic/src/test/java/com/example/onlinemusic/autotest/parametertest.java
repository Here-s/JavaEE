package com.example.onlinemusic.autotest;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class parametertest {

    @ParameterizedTest
    @MethodSource("test2")
    void test1(String x) {
        System.out.println(x);
    }
    //定义提供数据的方法，可以是 Stream 流
    static Stream<String> test2() {
        return Stream.of("火锅","辣条","螺蛳粉");
    }
}
