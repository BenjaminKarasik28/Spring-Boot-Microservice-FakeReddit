package com.example.apigateway.config;

import com.example.apigateway.Service.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.Silent.class)
public class JwtRequestFilterTest {

    @Mock
    UserService userService;

    @Mock
    JwtUtil jwtUtil;

    @InjectMocks
    JwtRequestFilter jwtRequestFilter;

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Mock
    FilterChain chain;

    @Mock
    UserDetails userDetails;

    @Test
    public void doFilterInternal_SUCCESS() throws ServletException, IOException {
        when(request.getHeader(any())).thenReturn("Bearer 1234");
        when(jwtUtil.getUsernameFromToken(any())).thenReturn("test");
        when(userService.loadUserByUsername(any())).thenReturn(userDetails);
        when(jwtUtil.validateToken(any(), any())).thenReturn(true);

        jwtRequestFilter.doFilterInternal(request, response, chain);
    }

    @Test
    public void doFilterInternal_FAIL() throws ServletException, IOException {
        when(request.getHeader(any())).thenReturn("1234");
        when(jwtUtil.getUsernameFromToken(any())).thenReturn("test");
        when(userService.loadUserByUsername(any())).thenReturn(userDetails);
        when(jwtUtil.validateToken(any(), any())).thenReturn(true);

        jwtRequestFilter.doFilterInternal(request, response, chain);
    }

    @Test
    public void doFilterInternal_IllegalArgumentException_FAIL() throws ServletException, IOException {
        when(request.getHeader(any())).thenReturn("Bearer 1234");
        when(jwtUtil.getUsernameFromToken(any())).thenThrow(IllegalArgumentException.class);
        jwtRequestFilter.doFilterInternal(request, response, chain);
    }

    @Test
    public void doFilterInternal_ExpiredJWTException_FAIL() throws ServletException, IOException {
        when(request.getHeader(any())).thenReturn("Bearer 1234");
        when(jwtUtil.getUsernameFromToken(any())).thenThrow(ExpiredJwtException.class);
        jwtRequestFilter.doFilterInternal(request, response, chain);
    }
}
