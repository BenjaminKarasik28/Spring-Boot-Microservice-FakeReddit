package com.example.userapi.controller;

import com.example.userapi.model.Profile;
import com.example.userapi.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    ProfileService profileService;

    @GetMapping("/{username}")
    public Profile getUserProfile(@PathVariable String username) {
        return profileService.getProfile(username);
    }

    @PostMapping("/{username}")
    public Profile createUserProfile(@PathVariable String username, @RequestBody Profile userProfile) {
        return profileService.createProfile(username, userProfile);
    }


}