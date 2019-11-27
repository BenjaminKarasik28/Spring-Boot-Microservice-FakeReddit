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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
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

    private PostComment samplePostComment;

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
        samplePostComment = new PostComment();
        samplePostComment.setPostComment(sampleCommentList);


    }

    //    getAllComments
    //     getAllCommentsByPostId
    //    getEmailByPostId
    //    createComment
    //    updateComment
    //   deleteCommentById
    //   deleteCommentByUsername
    //    deletePostAndComments

    @Test
    public void getAllComments() throws Exception {
        getAllComments_Comment_Success();
    }

    @Test
    public void getAllCommentsByPostId() throws Exception {
        getAllCommentsByPostId_Comment_Success();
    }

    @Test
    public void getEmailByPostId() throws Exception {
        getEmailByPostId_String_Success();
    }

    @Test
    public void createComment() throws Exception {
        createComment_Comment_Success();
    }

    @Test
    public void updateComment() throws Exception {
        updateComment_Comment_Success();
    }


    @Test
    public void deleteCommentById() throws Exception {
        deleteCommentById_Void_Success();
    }

    @Test
    public void deleteCommentByUsername() throws Exception {
        deleteCommentByUsername_Void_Success();
    }

        @Test
    public void deletePostAndComments() throws Exception {
        deletePostAndComments_Long_Success();
    }





    private void getAllComments_Comment_Success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/list");

        when(commentService.getAllComments()).thenReturn(sampleCommentList);

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

    private void getAllCommentsByPostId_Comment_Success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
            .get("/list/{postId}", 1);

        when(commentService.getAllCommentsByPostId(anyLong())).thenReturn(samplePostComment);

        commentController.getAllCommentsByPostId(1L);

        assertNotNull(samplePostComment);

    }


    private void getEmailByPostId_String_Success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/post/user/{postId}", 1);

        when(commentService.getEmailbyPostId(anyLong())).thenReturn("user1@mail.com");

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().string("user1@mail.com"))
                .andReturn();
    }

    private void createComment_Comment_Success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/comment/{postId}", 1)
                .header("username", "user1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\t\"text\" : \"some text\"\n" +
                        "}");

        when(commentService.createComment(any(), anyString(), anyLong())).thenReturn("user@mail.com");

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().string("user@mail.com"))
                .andReturn();
    }

    private void updateComment_Comment_Success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/{commentId}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\t\"text\" : \"some text\"\n" +
                        "}");

        when(commentService.updateComment(any(), anyLong())).thenReturn(sampleComment);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "{\n" +
                        "    \"id\":1,\n" +
                        "    \"postId\":1,\n" +
                        "    \"text\": \"some text\",\n" +
                        "    \"username\": \"user1\"\n" +
                        "}"))
                .andReturn();

    }

    private void deleteCommentById_Void_Success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/{commentId}", 1);

        commentService.deleteByCommentId(anyLong());

        verify(commentService, times(1)).deleteByCommentId(anyLong());

        commentController.deleteCommentById(anyLong());
    }


    private void deleteCommentByUsername_Void_Success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/post/name/{username}", "user1");

        commentService.deleteCommentByUsername(anyString());

        verify(commentService, times(1)).deleteCommentByUsername(anyString());

        commentController.deleteCommentByUsername(anyString());
    }


    private void deletePostAndComments_Long_Success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/post/{postId}", 1);

        commentService.deletePostAndComments(anyLong());

        verify(commentService, times(1)).deletePostAndComments(anyLong());

        commentController.deletePostAndComments(anyLong());

    }


}
