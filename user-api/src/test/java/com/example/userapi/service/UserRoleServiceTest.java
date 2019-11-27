package com.example.userapi.service;

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


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserRoleServiceTest {

    private User user;
    private UserRole userRole;
    private List<UserRole> list;

    @InjectMocks
    UserRoleServiceImpl userRoleService;

    @Mock
    UserRoleRepository userRoleRepository;

    @Mock
    UserRepository userRepository;

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
    public void createRole_UserRole_SUCCESS(){
        when(userRoleRepository.save(any())).thenReturn(userRole);
        UserRole newRole = userRoleService.createRole(userRole);
        assertEquals(newRole.getName(), userRole.getName());
    }

    @Test
    public void getRole_UserRole_SUCCESS() {
        when(userRoleRepository.findByName(any())).thenReturn(userRole);
        UserRole savedRole = userRoleService.getRole(userRole.getName());
        assertEquals(savedRole.getName(), userRole.getName());
    }


    @Test
    public void getUser_User_SUCCESS() {
        when(userRepository.findByEmail(any())).thenReturn(user);
        User savedUser = userRoleService.getUser(user.getEmail());
        assertEquals(savedUser.getEmail(), user.getEmail());
    }


}
