package com.example.apigateway.bean;

import com.example.apigateway.Bean.UserBean;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class UserBeanTest {
    @InjectMocks
    UserBean userBean;

    @Before
    public void initialize() {
        userBean.setId(1L);

        userBean.setUsername("test");
        userBean.setPassword("tester");
    }

    @Test
    public void getId_Success() {
        assertEquals(1L, (long) userBean.getId());
    }



    @Test
    public void getUsername_Success(){
        assertEquals("test", userBean.getUsername());
    }

    @Test
    public void getPassword_Success() {
        assertEquals("tester", userBean.getPassword());
    }
}
