package com.example.userapi.service;

import com.example.userapi.model.Profile;

public interface ProfileService {

<<<<<<< HEAD
    public Profile createProfile(String username, Profile newProfile);

    public Profile getProfile(String username);
=======
//    public Profile createProfile(String username, Profile newProfile);
//
//    public Profile getProfile(String username);

    public Profile createProfile(String email, Profile newProfile);

    public Profile getProfile(String email);

    public Profile updateProfile(String email, Profile updateProfile);
>>>>>>> 26b1edacf451336fb397c0cc24cebc2aca4980d8

}