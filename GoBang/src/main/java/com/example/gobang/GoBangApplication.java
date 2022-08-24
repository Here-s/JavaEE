package com.example.gobang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})
public class GoBangApplication {

    public static void main(String[] args) {
        SpringApplication.run(GoBangApplication.class, args);
    }

}
