package com.example.userapi.service;

import com.example.userapi.model.Profile;

public interface ProfileService {


    public Profile createProfile(String username, Profile newProfile);

    public Profile getProfile(String username);

//    public Profile createProfile(String username, Profile newProfile);
//
//    public Profile getProfile(String username);



    public Profile updateProfile(String email, Profile updateProfile);


}