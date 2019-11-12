package com.example.userapi.controller;

import com.example.userapi.model.User;
import com.example.userapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/hello")
    public String test(){
        return "Hello World!";
    }

    @PostMapping("/login")
    public User login(@RequestBody User user) {
        return userService.userLogin(user);
    }


    @PostMapping("/signup")
    public User signup(@RequestBody User user) {
        return userService.userSignup(user);
    }



}
