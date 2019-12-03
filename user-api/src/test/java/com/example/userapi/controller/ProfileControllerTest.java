package com.example.userapi.controller;

import com.example.userapi.config.JwtUtil;
import com.example.userapi.model.Profile;
import com.example.userapi.service.ProfileService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;


@RunWith(MockitoJUnitRunner.class)
public class ProfileControllerTest {

    private MockMvc mockMvc;
    private Profile profile;

    @InjectMocks
    ProfileController profileController;

    @Mock
    ProfileService profileService;

    @Mock
    private JwtUtil jwtUtil;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(profileController).build();
    }

    @Before
    public void initializeProfile() {
        profile = new Profile();
        profile.setId(1L);
        profile.setEmail("test@test.com");
        profile.setAddress("Gotham");
        profile.setMobile("111-111-1111");
    }

    @Test
    public void createProfile_Profile_Success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/profile")
                .header("username", "test")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createUserProfileInJson("test@test.com", "111-111-1111", "test"));

        when(profileService.createProfile(any(), any())).thenReturn(profile);

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().json("{\"mobile\":\"111-111-1111\"}"))
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }

    //This converts to JSON object
    private static String createUserProfileInJson(String email, String mobile, String address) {
        return "{ \"email\": \"" + email + "\", " + "\"mobile\":\"" + mobile + "\", " + "\"address\":\"" + address + "\"}";
    }

    @Test
    public void updateProfile_Profile_Success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/profile/{username}", "test")
                .header("username", "test")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"test@test.com\"}");

        when(profileService.updateProfile(any(), any())).thenReturn(profile);

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().json("{\"email\":\"test@test.com\"}"))
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    public void getProfile_Profile_Success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/profile/{username}", "test")
                .header("username", "test")
                .accept(MediaType.APPLICATION_JSON);

        when(profileService.getProfile(any())).thenReturn(profile);

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }
}
