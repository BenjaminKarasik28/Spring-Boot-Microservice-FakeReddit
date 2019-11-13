package com.example.userapi.service;


import com.example.userapi.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {

    public List<String> userLogin(User user);
    public List<String> userSignup(User user);
    public User addRole(String username, int roleId);
    public User getUser(String username);
}
