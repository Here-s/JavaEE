package com.example.springboot1.model;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "student")
@Component
public class Student {
    private int id;
    private String name;
    private int age;
}
