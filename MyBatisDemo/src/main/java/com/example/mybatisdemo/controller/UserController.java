package com.example.mybatisdemo.controller;

import com.example.mybatisdemo.model.UserInfo;
import com.example.mybatisdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/getuserbyid")
    public UserInfo getUserById(Integer id) {
        if (id == null) {
            return null;
        }
        return userService.getUserById(id);
    }

}
