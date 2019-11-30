package com.example.userapi.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class JwtUtilTest {

    private int JTW_TOKEN_VALIDITY = 5 * 60 * 60;
    private String secret = "pancakes";
    private String username = "test";

    @InjectMocks
    JwtUtil jwtUtil;

    @Test
    public void generateToken_String_SUCCESS() {
        Map<String, Object> claims = new HashMap<>();
        String expectedToken = "1234";
        String actualToken = jwtUtil.generateToken(username);
        assertEquals(expectedToken, actualToken);
    }
}

