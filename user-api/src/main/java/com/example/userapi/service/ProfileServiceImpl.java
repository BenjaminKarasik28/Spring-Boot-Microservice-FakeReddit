package com.example.userapi.service;

import com.example.userapi.model.Profile;
import com.example.userapi.model.User;
import com.example.userapi.repository.ProfileRepository;
import com.example.userapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Override
    public Profile createProfile(String username, Profile newProfile) {
        User user = userRepository.findByUsername(username);
        user.setProfile(newProfile);

        return profileRepository.save(newProfile);
    }

    @Override
    public Profile getProfile(String username) {
        return profileRepository.findProfileByUsername(username);
    }
}