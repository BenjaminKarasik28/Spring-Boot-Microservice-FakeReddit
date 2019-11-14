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

//    @GetMapping("/{username}")
//    public Profile getUserProfile(@PathVariable String username) {
//        return profileService.getProfile(username);
//    }
//
//    @PostMapping("/{username}")
//    public Profile createUserProfile(@PathVariable String username, @RequestBody Profile userProfile) {
//        return profileService.createProfile(username, userProfile);
//    }

    @GetMapping("/{email}")
    public Profile getUserProfile(@PathVariable String email) {
        return profileService.getProfile(email);
    }

    // To discuss - multiple profiles can be added for user
    @PostMapping("/{email}")
    public Profile createUserProfile(@PathVariable String email, @RequestBody Profile userProfile) {
        return profileService.createProfile(email, userProfile);
    }

    @PutMapping("/{email}")
    public Profile updateUserProfile(@PathVariable String email, @RequestBody Profile updateProfile) {
        return profileService.updateProfile(email, updateProfile);
    }


}