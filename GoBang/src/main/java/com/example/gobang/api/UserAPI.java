package com.example.gobang.api;

import org.springframework.web.bind.annotation.*;

@RestController
public class UserAPI {

    @PostMapping("/login")
    @ResponseBody
    public Object login(String username, String password) {

    }

    @PostMapping("/register")
    @ResponseBody
    public Object register(String username, String password) {

    }

    @GetMapping("/userInfo")
    @ResponseBody
    public Object getUserInfo() {

    }
}
