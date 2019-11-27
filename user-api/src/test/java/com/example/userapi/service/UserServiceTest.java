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
import org.springframework.security.crypto.password.PasswordEncoder;
import sun.security.util.Password;

import javax.inject.Inject;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


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
    public void sign_List_SUCCESS() {
        String expectedToken = "12345";
        when(userRoleRepository.findByName(any())).thenReturn(userRole);
        when(userRepository.save(any())).thenReturn(user);
        when(jwtUtil.generateToken(any())).thenReturn(expectedToken);
        List<String> actualToken = userService.userSignup(user);
        assertEquals(actualToken.get(0), expectedToken);
    }


}

//    public List<String> userSignup(User newUser) throws EmailSignupException, ExistingUserSignupException {
//        //validate email input
//        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
//        try {
//            if(!newUser.getEmail().matches(regex)) {
//                throw new EmailSignupException("Please enter a valid email");
//            }
//            else if(userRepository.findByEmail(newUser.getEmail()) != null) {
//                throw new ExistingUserSignupException("User already exists - please login");
//            }
//            UserRole userRole = userRoleRepository.findByName("ROLE_USER");
//            if (userRole == null) {
//                userRole = new UserRole();
//                userRole.setName("ROLE_USER");
//                userRoleService.createRole(userRole);
//            }
//            newUser.addRole(userRole);
//
//            newUser.setPassword(encoder().encode(newUser.getPassword()));
//            if (userRepository.save(newUser) != null) {
//                return Arrays.asList(jwtUtil.generateToken(newUser.getUsername()), newUser.getUsername());
//            }
//        } finally {
//            System.out.println("test");
//        }
//        return null;
//    }
