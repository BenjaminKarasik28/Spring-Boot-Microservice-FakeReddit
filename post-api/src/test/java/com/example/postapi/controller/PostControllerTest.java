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
    @Test
    public void getPostById_Post_Success() throws Exception{
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/post/" + post.getId())
                .header("username", "user1");
        ObjectMapper objectMapper = new ObjectMapper();
        String postMapper = objectMapper.writeValueAsString(post);
        when(postService.getPostById(anyLong())).thenReturn(java.util.Optional.ofNullable(post));
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().string((postMapper)));

    }
    @Test
    public void deletePostById_Post_Success() throws Exception{
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/" + post.getId())
                .header("username", "user1");
        when(postService.deletePostbyId(anyLong())).thenReturn(1L);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().string("1"));
    }

    @Test
    public void deletePostByUsername_Post_Success() throws Exception{
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/post/" + post.getUsername())
                .header("username", "user1");
        when(postService.deletePostByUsername(anyString())).thenReturn(post.getUsername());
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().string("Batman"));
    }

    @Test
    public void updatePost_Post_Success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/post/" + post.getId())
                .header("username", "user1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"title\"}");

        when(postService.updatePost(any(), anyLong())).thenReturn(post);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().json("{\"title\":\"title\"}"))
                .andReturn();

    }

    @Test
    public void sendPostIdRestTemplate_Post_Success() throws Exception{
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/user/" + post.getId());
        when(postService.sendPostIdRestTemplate(anyLong())).thenReturn("username");

         mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().string("username"))
                .andReturn();
    }




    private static String createPostInJson(String title, String desc) {
        return "{ \"title\": \"" + title + "\", " + "\"description\":\"" + desc  +"\" }";
    }
}
