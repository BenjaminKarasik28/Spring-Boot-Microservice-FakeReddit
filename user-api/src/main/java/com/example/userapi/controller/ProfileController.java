package com.example.userapi.controller;

import com.example.userapi.model.Profile;
import com.example.userapi.service.ProfileService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    ProfileService profileService;

    @ApiOperation(value = "Create profile", notes = "Provide username and profile body", response = Profile.class)
    @PostMapping
    public Profile createProfile(@RequestBody Profile profile, @RequestHeader("username") String username) {
        return profileService.createProfile(username, profile);
    }

    @ApiOperation(value = "Get profile", notes = "Provide username to retrieve profile", response = Profile.class)
    @GetMapping("/{username}")
    public Profile getProfile(@RequestHeader("username") String username) {
        return profileService.getProfile(username);
    }

    @ApiOperation(value = "Update profile", notes = "Provide username and profile body to update", response = Profile.class)
    @PutMapping("/{username}")
    public Profile updateProfile(@RequestBody Profile profile, @RequestHeader("username") String username) {
        return profileService.updateProfile(username, profile);
    }
}