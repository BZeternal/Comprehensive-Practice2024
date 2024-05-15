package com.example.backend.controller;

import com.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/api/login")
    Map<String,String> login(@RequestParam Map<String,String> data)
    {
        return userService.login(data);
    }

    @PostMapping("/api/register")
    Map<String,String> register(@RequestParam Map<String,String> data){
        return userService.register(data);
    }

    @PostMapping("/api/user/info")
    Map<String,String> getUserInfo(@RequestParam Map<String,String> data)
    {
        return userService.getUserInfo(data);
    }
}
