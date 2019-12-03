package com.example.userapi.controller;

import com.example.userapi.config.JwtUtil;
import com.example.userapi.exceptionhandling.EmailSignupException;
import com.example.userapi.exceptionhandling.ExistingUserSignupException;
import com.example.userapi.exceptionhandling.IncorrectLoginException;
import com.example.userapi.model.User;
import com.example.userapi.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

@EnableWebMvc
@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    private MockMvc mockMvc;
    private User user;

    @InjectMocks
    UserController userController;

    @Mock
    UserService userService;

    @Mock
    private JwtUtil jwtUtil;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Before
    public void intializeUser() {
        user = new User();
        user.setUsername("batman");
        user.setPassword("tester");
    }

    @Test
    public void signup_List_Success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createUserInJson("test", "tester"));

        when(userService.userSignup(any())).thenReturn(Arrays.asList("1234", "test"));

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().json("{\"username\":\"test\",\"token\":\"1234\"}"))
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }

    //This converts to JSON object
    private static String createUserInJson(String username, String password) {
        return "{ \"username\": \"" + username + "\", " + "\"password\":\"" + password + "\"}";
    }

    @Test
    public void login_List_Success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createUserInJson("test", "tester"));

        when(userService.userLogin(any())).thenReturn(Arrays.asList("1234", "test"));

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().json("{\"username\":\"test\",\"token\":\"1234\"}"))
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    public void IncorrectLoginException_401_Fail() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createUserInJson("test", "tester"));

        when(userService.userLogin(any())).thenThrow(new IncorrectLoginException("Wrong"));

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isBadRequest())
                .andReturn();

    }

    @Test
    public void deleteUser_Success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/{username}", "test");

        when(userService.deleteUserByUsername(any())).thenReturn((long) 1);

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().json("1"))
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    public void getEmailFromUsername_String_Success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/post/{username}", "test")
                .accept(MediaType.APPLICATION_JSON);

        when(userService.getEmailByUsername(any())).thenReturn("test@test.com");

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().string("test@test.com"))
                .andReturn();
    }

    @Test
    public void updateUser_User_Success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/{username}", "test")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"batman\"}");

        when(userService.updateUser(any(), any())).thenReturn(user);

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().json("{\"username\":\"batman\"}"))
                .andReturn();
    }
}
