package com.example.userapi.config;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class JwtUtilTest {

    private int JTW_TOKEN_VALIDITY = 5 * 60 * 60;
    private String secret = "pancakes";
    private String username = "test";

    @InjectMocks
    JwtUtil jwtUtil;

    @Before
    public void init() {

        jwtUtil.setSecret("pancakes");
    }



    @Test
    public void generateToken_String_SUCCESS() {
        String token = jwtUtil.generateToken(username);
        assertNotNull(token);

    }
}

