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

import java.util.Date;

@RunWith(MockitoJUnitRunner.class)
public class JwtUtilTest {

    private String jwtToken;
    private Date date;
    private String username;

    @InjectMocks
    JwtUtil jwtUtil;

    @Mock
    UserDetails userDetails;

    @Before
    public void init() {

        jwtUtil.setSecret("pancakes");
        jwtToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJjaHJpcyIsImV4cCI6MTU3NTMxNzY1OSwiaWF0IjoxNTc1Mjk5NjU5fQ.PV9ZCc6KU8gSlUt4cBfEdTITPnWq-qAViv6M5KXgryWCsyfUC9CdH3xnbEtWFfzagn4-vvAphIQAvVinY8F3Yw";
        date = new Date();
        username = "username";
    }
    @Test
    public void getUserNameFromToken_String_Success(){
        String token = jwtUtil.getUsernameFromToken(jwtToken);
        assertNotNull(token);
    }
    @Test
    public void validateToken_Boolean_Success(){
        Boolean isTrue = jwtUtil.validateToken(jwtToken, userDetails);
        assertNotNull(isTrue);
    }
    @Test
    public void istokenExpired_Boolean_Success(){
        Date date1 = jwtUtil.getExpirationDateFromToken(jwtToken);
        Boolean isExpired = jwtUtil.isTokenExpired(jwtToken);
        assertNotNull(isExpired);
    }

}
