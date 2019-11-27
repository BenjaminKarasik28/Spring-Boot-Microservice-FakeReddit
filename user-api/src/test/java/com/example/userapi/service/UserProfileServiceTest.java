package com.example.userapi.service;

import com.example.userapi.model.Profile;
import com.example.userapi.model.User;
import com.example.userapi.repository.ProfileRepository;
import com.example.userapi.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserProfileServiceTest {

    private User user;
    private Profile profile;

    @InjectMocks
    ProfileServiceImpl profileService;

    @Mock
    ProfileRepository profileRepository;

    @Mock
    UserRepository userRepository;

    @Before
    public void initialize() {
        user = new User();
        user.setId(1L);
        user.setUsername("test");
        user.setPassword("tester");
        user.setEmail("test@test.com");

        profile = new Profile();
        profile.setId(1L);
        profile.setAddress("123");
        profile.setEmail("profile@profile.com");
        profile.setMobile("111-111-1111");
        user.setProfile(profile);
    }

    @Test
    public void createProfile_Profile_Success() {
        when(userRepository.findByUsername(any())).thenReturn(user);
        when(userRepository.findByEmail(any())).thenReturn(user);
        when(profileRepository.save(any())).thenReturn(profile);
        Profile fakeProfile = profileService.createProfile(user.getUsername(), profile);
        System.out.println(user.getUsername());
        assertEquals(fakeProfile.getId(), profile.getId());
    }

    @Test
    public void getProfile_Profile_SUCCESS(){
        when(userRepository.findByUsername(any())).thenReturn(user);
        when(profileRepository.findProfileByUsername(any())).thenReturn(profile);
        Profile fakeProfile = profileService.getProfile(user.getUsername());
        assertEquals(fakeProfile.getId(), profile.getId());
    }

    @Test
    public void updateProfile_Profile_SUCCESS(){
        when(userRepository.findByUsername(any())).thenReturn(user);
        when(profileRepository.save(any())).thenReturn(profile);
        Profile fakeProfile = profileService.updateProfile(user.getUsername(), profile);
        assertEquals(fakeProfile.getId(), profile.getId());
    }


}

