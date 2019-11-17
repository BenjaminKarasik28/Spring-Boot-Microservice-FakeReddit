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


    @GetMapping("/{username}")
    public Profile getProfile(@RequestHeader("username") String username) {
        return profileService.getProfile(username);
    }

    @PutMapping("/{username}")
    public Profile updateProfile(@RequestBody Profile profile, @RequestHeader("username") String username) {
        return profileService.updateProfile(username, profile);
    }
}