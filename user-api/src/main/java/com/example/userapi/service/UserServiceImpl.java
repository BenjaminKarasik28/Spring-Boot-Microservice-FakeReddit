package com.example.userapi.service;

import com.example.userapi.model.User;
import com.example.userapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User userLogin(User user) {
        return userRepository.login(user.getUsername(), user.getPassword());
    }

    @Override
    public User userSignup(User user) {
        return userRepository.save(user);
    }
}
