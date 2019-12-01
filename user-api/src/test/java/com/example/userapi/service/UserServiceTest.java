package com.example.userapi.service;

import com.example.userapi.config.JwtUtil;
import com.example.userapi.exceptionhandling.ExistingUserSignupException;
import com.example.userapi.exceptionhandling.IncorrectLoginException;
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
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    private User user;
    private UserRole userRole;


    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    UserRepository userRepository;

    @Mock
    UserRoleRepository userRoleRepository;

    @Mock
    UserRoleServiceImpl userRoleService;

    @Mock
    private PasswordEncoder bCryptPasswordEncoder;

    @Mock
    private PasswordEncoder encoder;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    RestTemplate restTemplate;

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

    @Test
    public void AddRole_User_Success(){

        when(userRoleRepository.findById(anyInt())).thenReturn(java.util.Optional.ofNullable(userRole));
        when(userRepository.findByEmail(anyString())).thenReturn(user);
        when(userRepository.save(any())).thenReturn(user);
        User user1 = userService.addRole(user.getEmail(), 1);
        assertNotNull(user1);

    }
    @Test
    public void getUser_User_Success(){
        when(userRepository.findByEmail(anyString())).thenReturn(user);
        User user2 = userService.getUser(user.getEmail());
        assertNotNull(user2);

    }

    @Test
    public void deleteUserByUsername_User_Success(){
        restTemplate.delete(anyString());
        verify(restTemplate, times(1)).delete(anyString());
        when(userRepository.findByUsername(anyString())).thenReturn(user);
        userRepository.delete(any());
        verify(userRepository, times(1)).delete(any());
        Long id = userService.deleteUserByUsername(user.getUsername());
        assertNotNull(id);
    }

    @Test(expected = ExistingUserSignupException.class)
    public void userSignUp_Status_ERROR(){
        when(userRepository.findByEmail(any())).thenReturn(user);

        List<String> signup = userService.userSignup(user);
    }

//    @Test
//    public void login_List_Success() {
//        String expectedToken = "12345";
//        when(userRepository.findByEmail(any())).thenReturn(user);
//        when(jwtUtil.generateToken(any())).thenReturn(expectedToken);
//        //when(encoder.matches(any(), any())).thenReturn(true);
//        when(bCryptPasswordEncoder.matches(anyString(),anyString())).thenReturn(true);
//        List<String> actualToken = userService.userLogin(user);
//        System.out.println(actualToken.get(0) + " " + expectedToken);
//        assertEquals(actualToken.get(0), expectedToken);
//    }

    @Test(expected = IncorrectLoginException.class)
    public void login_Status_ERROR(){
        user.setUsername(null);
        when(userRepository.findByEmail(any())).thenReturn(user);
      //  when(bCryptPasswordEncoder.matches(any(),any())).thenReturn(false);
        List<String> actualToken = userService.userLogin(user);
    }

    @Test
    public void deleteUserByUsername_Long_SUCCESS() {
        restTemplate.delete(any());
        restTemplate.delete(any());
//        when(userRepository.findByUsername(any())).thenReturn(user);
        userRepository.delete(user);
    }

    @Test
    public void updateUser_User_Success() {
        when(userRepository.findByUsername(any())).thenReturn(user);
        when(userRepository.save(any())).thenReturn(user);
        User updatedUser = userService.updateUser(user.getUsername(), user);
        assertEquals(updatedUser, user);
    }

    @Test
    public void getEmailByUsername_String_SUCCESS() {
        when(userRepository.findByUsername(any())).thenReturn(user);
        String expectedEmail = "test@test.com";
        String actualEmail = userService.getEmailByUsername(user.getUsername());
        assertEquals(expectedEmail, actualEmail);
    }
}
