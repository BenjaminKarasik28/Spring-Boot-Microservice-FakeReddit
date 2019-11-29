package com.example.postapi.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DummyCommentTest {
    private DummyComment dummyComment;

    @Before
    public void init(){
        dummyComment = new DummyComment();
        dummyComment.setId(1L);
        dummyComment.setPostId(2L);
        dummyComment.setText("text");
        dummyComment.setUsername("username");

    }

    @Test
    public void id_DummyComment_Success() {
        assertEquals(java.util.Optional.of(1L), java.util.Optional.of( dummyComment.getId()));
    }
    @Test
    public void postId_DummyComment_Success() {
        assertEquals(java.util.Optional.of(2L), java.util.Optional.of( dummyComment.getPostId()));
    }
    @Test
    public void text_DummyComment_Success() {
        assertEquals("text",  dummyComment.getText());
    }
    @Test
    public void username_DummyComment_Success() {
        assertEquals("username",  dummyComment.getUsername());
    }



}
