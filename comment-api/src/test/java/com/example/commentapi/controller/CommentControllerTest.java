package com.example.commentapi.controller;

import com.example.commentapi.model.Comment;
import com.example.commentapi.model.DummyPost;
import com.example.commentapi.model.PostComment;

import com.example.commentapi.service.CommentService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class CommentControllerTest {

    private MockMvc mockMvc;


    private Comment sampleComment;

    private List<Comment> sampleCommentList;

    @InjectMocks
    CommentController commentController;

    @Mock
    CommentService commentService;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(commentController).build();
        sampleComment = new Comment(
                1L,
                1L,
                "some text",
                "user1"
        );


        sampleCommentList = Arrays.asList(
                new Comment(
                        1L,
                        1L,
                        "some text",
                        "user1"
                )
        );

    }

    @Test
    public void getAllComments() throws Exception {
        getAllComments_Comment_Success();
    }


    private void getAllComments_Comment_Success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/list");

        when(commentService.getAllComments()).thenReturn(sampleCommentList);
        System.out.println(sampleCommentList);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().json("[" +
                        "{\n" +
                        "    \"id\":1,\n" +
                        "    \"postId\":1,\n" +
                        "    \"text\": \"some text\",\n" +
                        "    \"username\": \"user1\"\n" +
                        "}]"))
                .andReturn();
    }


}
