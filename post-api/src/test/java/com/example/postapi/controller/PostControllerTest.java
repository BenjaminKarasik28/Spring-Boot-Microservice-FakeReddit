package com.example.postapi.controller;

import com.example.postapi.model.Post;
import com.example.postapi.model.PostComment;
import com.example.postapi.service.PostService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.Before;

import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@EnableWebMvc
@RunWith(MockitoJUnitRunner.class)
public class PostControllerTest {

    private MockMvc mockMvc;
    private Post post;

    @InjectMocks
    PostController postController;

    @Mock
    PostService postService;

    private List<Post> allPosts;
    private PostComment postComment;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(postController).build();
        post = new Post();
        post.setId(1L);
        post.setUsername("Batman");
        post.setTitle("title");
        post.setDescription("desc");
        allPosts = Arrays.asList(post);
        postComment = new PostComment();
    }


    @Test
    public void createPost_Post_Success() throws Exception{
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/post")
                .header("username", "user1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createPostInJson(post.getTitle(), post.getDescription()));

        ObjectMapper mapper = new ObjectMapper();
        String postMapper = mapper.writeValueAsString(post);
        when(postService.createPost(any(), anyString())).thenReturn(post);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().string(postMapper));

    }

    @Test
    public void getAllPosts_Post_Success() throws Exception{
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/list");

        ObjectMapper mapper = new ObjectMapper();
        String allPostsMapper = mapper.writeValueAsString(allPosts);
        when(postService.getAllPosts()).thenReturn(allPosts);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().string(allPostsMapper));
    }
    @Test
    public void getAllPostsByUsername_Post_Success() throws Exception{
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/" + post.getUsername())
                .header("username", "user1");
        ObjectMapper mapper = new ObjectMapper();
        String allPostsMapper = mapper.writeValueAsString(allPosts);
        when(postService.getAllPostsByUsername(anyString())).thenReturn(allPosts);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().string(allPostsMapper));
    }
    @Test
    public void getCommentsByPostId_Post_Success() throws Exception{
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/comment/" + post.getId())
                .header("username", "user1");
        ObjectMapper mapper = new ObjectMapper();
        String allPostsMapper = mapper.writeValueAsString(postComment);
        when(postService.getAllCommentsByPostId(anyLong())).thenReturn((postComment));
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().string(allPostsMapper));
    }

    private static String createPostInJson(String title, String desc) {
        return "{ \"title\": \"" + title + "\", " + "\"description\":\"" + desc  +"\" }";
    }
}
