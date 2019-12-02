package com.example.apigateway.repository;

import com.example.apigateway.Bean.UserBean;
import com.example.apigateway.Repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;


import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@RunWith(MockitoJUnitRunner.class)
public class UserRepositoryTest {
    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    UserRepository userRepository;

    @InjectMocks
    private UserBean user;
    @Before
    public void init() {
        user.setId(1L);
        user.setEmail("osman@gmail.com");
        user.setPassword("qweqwe");
        user.setUsername("jooz");
    }
    @Test
    public void getUserByUsername_UserRepository_Success() {
        Mockito.when(jdbcTemplate.queryForObject(anyString(), any(), any(RowMapper.class))).thenReturn(user);
        assertEquals(userRepository.findByUsername("user"), user);
    }

}