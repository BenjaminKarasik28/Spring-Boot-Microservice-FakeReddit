package com.example.userapi.service;


import com.example.userapi.exceptionhandling.EmailSignupException;
import com.example.userapi.exceptionhandling.ExistingUserSignupException;
import com.example.userapi.model.User;
//import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {

    public List<String> userLogin(User user);
    public List<String> userSignup(User user) throws EmailSignupException, ExistingUserSignupException;
    public User addRole(String username, int roleId);
    public User getUser(String username);
    public Long deleteUserByUsername(String username);
    public User updateUser(String username, User user);
    public String getEmailByUsername(String username);
}