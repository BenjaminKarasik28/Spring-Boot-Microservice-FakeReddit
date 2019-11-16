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

    @PostMapping
    public Profile createProfile(@RequestBody Profile profile, @RequestHeader("username") String username) {
        return profileService.createProfile(username, profile);
    }

    @GetMapping("/list")
    public Profile getProfile(@RequestBody Profile profile, @RequestHeader("username") String username) {
        return profileService.getProfile(username);
    }

    @PutMapping("/{email}")
    public Profile updateUserProfile(@PathVariable String email, @RequestBody Profile updateProfile) {
        return profileService.updateProfile(email, updateProfile);
    }

//    @GetMapping("/{email}")
//    public Profile getUserProfile(@PathVariable String email) {
//        return profileService.getProfile(email);
//    }
//
//
//    @PostMapping("/{email}")
//    public Profile createUserProfile(@PathVariable String email, @RequestBody Profile userProfile) {
//        return profileService.createProfile(email, userProfile);
//    }

    //    @GetMapping("/{username}")
//    public Profile getUserProfile(@PathVariable String username) {
//        return profileService.getProfile(username);
//    }
//
//    @PostMapping("/{username}")
//    public Profile createUserProfile(@PathVariable String username, @RequestBody Profile userProfile) {
//        return profileService.createProfile(username, userProfile);
//    }
}