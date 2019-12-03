package com.example.userapi.service;

import com.example.userapi.model.Profile;

public interface ProfileService {

    /**
     * Creates and saves user profile
     *
     * @param newProfile the user profile to be created
     * @return the newly created user profile
     */
    public Profile createProfile(String username, Profile newProfile);

    /**
     * Gets user profile
     *
     * @param username name of user
     * @return the profile of user
     */
    public Profile getProfile(String username);

    /**
     * Updates profile
     *
     * @param username name of user
     * @param updateProfile updated profile information
     * @return the update profile
     */
    public Profile updateProfile(String username, Profile updateProfile);

}