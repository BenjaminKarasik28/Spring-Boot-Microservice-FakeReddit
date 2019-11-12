package com.example.userapi.service;


import com.example.userapi.model.User;

public interface UserService {

    public User userLogin(User user);
    public User userSignup(User user);

}
