package com.example.apigateway.config;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import io.jsonwebtoken.Jwts;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;

@RunWith(MockitoJUnitRunner.class)
public class JwtUtilTest {

    @InjectMocks
    JwtUtil jwtUtil;

    @Mock
    UserDetails userDetails;

    @Before
    public void init() {

        jwtUtil.setSecret("pancakes");
    }
    @Test
    public void getUserNameFromToken_String_Success(){
        String token = jwtUtil.getUsernameFromToken("token");
        assertNotNull(token);


    }

}
