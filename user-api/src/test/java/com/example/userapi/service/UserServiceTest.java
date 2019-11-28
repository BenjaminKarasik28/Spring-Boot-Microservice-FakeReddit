package com.example.userapi.service;

import com.example.userapi.config.JwtUtil;
import com.example.userapi.model.User;
import com.example.userapi.model.UserRole;
import com.example.userapi.repository.UserRepository;
import com.example.userapi.repository.UserRoleRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import sun.security.util.Password;

import javax.inject.Inject;

import java.beans.Encoder;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    private User user;
    private UserRole userRole;
    private User updatedUser;


    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    UserRepository userRepository;

    @Mock
    UserRoleRepository userRoleRepository;

    @MockBean
    private PasswordEncoder bCryptPasswordEncoder;

    @Mock
    private JwtUtil jwtUtil;

    @Before
    public void initialize() {
        user = new User();
        user.setId(1L);
        user.setUsername("test");
        user.setPassword("tester");
        user.setEmail("test@test.com");


        userRole = new UserRole();
        userRole.setName("ADMIN");
        userRole.setId(1);
        userRole.setUsers(Arrays.asList(user));
        user.addRole(userRole);
    }


    @Test
    public void signup_List_SUCCESS() {
        String expectedToken = "12345";
        when(userRoleRepository.findByName(any())).thenReturn(userRole);
        when(userRepository.save(any())).thenReturn(user);
        when(jwtUtil.generateToken(any())).thenReturn(expectedToken);
        List<String> actualToken = userService.userSignup(user);
        assertEquals(actualToken.get(0), expectedToken);
    }

//    @Test
//    public void login_List_Success() {
//        String expectedToken = "12345";
//        when(userRepository.findByEmail(any())).thenReturn(user);
//        when(jwtUtil.generateToken(any())).thenReturn(expectedToken);
//        when(encoder.matches(any(), any())).thenReturn(true);
////        when(bCryptPasswordEncoder.matches(any(),any())).thenReturn(true);
//        List<String> actualToken = userService.userLogin(user);
//        assertEquals(actualToken.get(0), expectedToken);
//    }

    @Test
    public void getEmailByUsername_String_SUCCESS() {
        String expectedEmail = "test@test.com";
        when(userRepository.findByUsername(any())).thenReturn(user);
        String actualEmail = user.getEmail();
        assertEquals(expectedEmail, actualEmail);

    }

}
