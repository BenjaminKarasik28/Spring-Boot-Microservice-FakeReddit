package com.example.apigateway.service;

import com.example.apigateway.Bean.UserBean;
import com.example.apigateway.Repository.UserRepository;
import com.example.apigateway.Service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    private UserBean user;
    private UserDetails userDetails;

    @InjectMocks
    UserService userService;

    @Mock
    PasswordEncoder bCryptPasswordEncoder;

    @Mock
    UserRepository userRepository;

    @Before
    public void initialize() {
        user = new UserBean(1L, "testuser", "testpass");
    }

    @Test
    public void loadUserByUsername_UserDetails_SUCCESS() {
        when(userRepository.findByUsername(any())).thenReturn(user);
        when(bCryptPasswordEncoder.encode(any())).thenReturn("1d4f");
        userDetails = userService.loadUserByUsername(user.getUsername());
        assertEquals(user.getUsername(), userDetails.getUsername());
    }

    @Test(expected = UsernameNotFoundException.class)
    public void loadUserByUsernameNull_Error_FAIL() {
        when(userRepository.findByUsername(any())).thenReturn(null);
        userDetails = userService.loadUserByUsername(user.getUsername());
    }
}
