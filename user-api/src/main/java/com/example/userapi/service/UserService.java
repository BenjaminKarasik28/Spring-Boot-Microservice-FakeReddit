package com.example.userapi.service;


import com.example.userapi.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    public String userLogin(User user);
    public String userSignup(User user);

}
