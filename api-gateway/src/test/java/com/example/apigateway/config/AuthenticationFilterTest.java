package com.example.apigateway.config;

import com.example.apigateway.Bean.UserBean;

import com.example.apigateway.Repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class AuthenticationFilterTest {
    @InjectMocks
    AuthenticationFilter authenticationFilter;
    @Mock
    UserRepository userRepository;
    @Mock
    SecurityContextHolder securityContextHolder;
    @Mock
    SecurityContext ctx;
    @Mock
    Authentication authentication;

    @InjectMocks
    UserBean user;

    @Before
    public void init(){
        SecurityContextHolder.setContext(ctx);
    }
    @Test
    public void getType_Success() {
        String returned = authenticationFilter.filterType();
        assertEquals("pre", returned);
    }
    @Test
    public void getOrder_Success() {
        int order = authenticationFilter.filterOrder();
        assertEquals(1, order);
    }
    @Test
    public void doesFilter_Success() {
        when(ctx.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("user");
        boolean value = authenticationFilter.shouldFilter();
        assertEquals(true, value);
    }
    @Test
    public void doRun_Success() {
        user.setUsername("user");
        user.setPassword("password");
        user.setId(1L);
        when(ctx.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("user");
        when(userRepository.findByUsername(anyString())).thenReturn(user);
        authenticationFilter.run();
    }
}