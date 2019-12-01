package com.example.commentapi.model;

import com.example.commentapi.model.DummyPost;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class DummyPostTest {

    @InjectMocks
    DummyPost dummyPost;

    @Before
    public void initialize() {
        dummyPost.setId(1L);
        dummyPost.setTitle("Title");
        dummyPost.setDescription("Text");
        dummyPost.setUsername("user1");
    }

    @Test
    public void getId_Success() {
        assertEquals(1L, (long) dummyPost.getId());
    }

    @Test
    public void getTitle_Success() {
        assertEquals("Title", dummyPost.getTitle());
    }

    @Test
    public void getDescription_Success() {
        assertEquals("Text", dummyPost.getDescription());
    }

    @Test
    public void getUsername_Success() {
        assertEquals("user1", dummyPost.getUsername());
    }


}