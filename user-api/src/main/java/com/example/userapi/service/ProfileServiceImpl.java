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

//    @Override
//    public Profile createProfile(String email, Profile newProfile) {
//        User user = userRepository.findByEmail(email);
//        user.setProfile(newProfile);
//
//        return profileRepository.save(newProfile);
//    }

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

    @Override
    public Profile updateProfile(String username, Profile updateProfile) {
        User user = userRepository.findByUsername(username);
        Profile profile = userRepository.findByUsername(username).getProfile();

        if(updateProfile.getAddress() != null) profile.setAddress(updateProfile.getAddress());
        if(updateProfile.getMobile() != null) profile.setMobile(updateProfile.getMobile());
        if(updateProfile.getEmail() != null) profile.setEmail(updateProfile.getEmail());

        profileRepository.save(profile);
        user.setProfile(profile);

        return updateProfile;
    }

//    @Override
//    public Profile getProfile(String email) {
//        return profileRepository.findProfileByEmail(email);
//    }

//    @Override
//    public Profile updateProfile(String email, Profile updateProfile) {
//        User user = userRepository.findByEmail(email);
//        Profile profile = userRepository.findByEmail(email).getProfile();
//
//        if(updateProfile.getAddress() != null) profile.setAddress(updateProfile.getAddress());
//        if(updateProfile.getMobile() != null) profile.setMobile(updateProfile.getMobile());
//        if(updateProfile.getEmail() != null) profile.setEmail(updateProfile.getEmail());
//
//        profileRepository.save(profile);
//        user.setProfile(profile);
//
//        return updateProfile;
//    }


}