package com.example.postapi.service;

import com.example.postapi.exceptionhandling.BlankPostException;
import com.example.postapi.model.DummyComment;
import com.example.postapi.model.Post;
import com.example.postapi.model.PostComment;
import com.example.postapi.repository.PostRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PostServiceTest {


    private Post post;
    private Iterable<Post> postList;
    private PostComment postComment;
    private DummyComment dummyComment;
    private Iterable<DummyComment> dummyComments;


    @Mock
    PostRepository postRepository;

    @InjectMocks
    PostServiceImpl postService;

    @Mock
    RestTemplate restTemplate;

    @Before
    public void initialize() {
        post = new Post();
        post.setId(1L);
        post.setTitle("Title");
        post.setDescription("Desc");
        post.setUsername("Batman");
        postComment = new PostComment();
        postComment.setPostComment(dummyComments);
        dummyComments = Arrays.asList(
                new DummyComment(
                        "text",
                        1L,
                        1L,
                        "username"
                )
        );


    }

    @Test
    public void createPost_Post_Success() {
        when(postRepository.save(any())).thenReturn(post);
        Post newPost = postService.createPost(post, post.getUsername());
        assertNotNull(newPost);
        assertEquals(post, newPost);
    }
    @Test
    public void getAllPosts_Post_Success(){
        when(postRepository.findAll()).thenReturn(postList);
        Iterable<Post> allPosts = postService.getAllPosts();
        assertEquals(postList, allPosts);
    }
    @Test
    public void getAllPostsByUsername_Post_Success(){
        when(postRepository.findAllByUsername(anyString())).thenReturn(postList);
        Iterable<Post> allPosts = postService.getAllPostsByUsername(post.getUsername());
        assertEquals(postList, allPosts);
    }

    @Test
    public void deletePostById_Post_Success() {
        restTemplate.delete(anyString());
        postRepository.deleteById(anyLong());
        verify(postRepository, times(1)).deleteById(anyLong());
        verify(restTemplate, times(1)).delete(anyString());
        postService.deletePostbyId(anyLong());
    }

    @Test
    public void deletePostByUsername_Post_Success(){

        postRepository.deleteByUsername(anyString());
        verify(postRepository,times(1)).deleteByUsername(anyString());
        postService.deletePostByUsername(anyString());
    }

    @Test
    public void getAllCommentsByPostId_Post_Success(){
        when(restTemplate.getForObject(anyString(), any())).thenReturn(postComment);
        PostComment postComment1 = postService.getAllCommentsByPostId(post.getId());
        assertEquals(postComment1, postComment);
        assertNotNull(postComment1);

    }
    @Test
    public void getPostId_Post_Success(){
//        when(postRepository.findByPostId(anyLong())).thenReturn(post);
        Optional<Post> post1 = postService.getPostById(anyLong());
        assertNotNull(post1);
    }

    @Test
    public void updatePost_Post_Success(){
        when(postRepository.findByPostId(anyLong())).thenReturn(post);
        when(postRepository.save(any())).thenReturn(post);
        Post post1 = postService.updatePost(post, post.getId());
        assertEquals(post1, post);
    }

    @Test
    public void sendPostIdRestTemplate_Post_Success(){
        when(postRepository.findByPostId(anyLong())).thenReturn(post);
        when(restTemplate.getForObject(anyString(), any())).thenReturn(post.getUsername());
        String username2 = postService.sendPostIdRestTemplate(post.getId());
        assertNotNull(username2);
    }

    @Test(expected = BlankPostException.class)
    public void createBlankPost_Status_ERROR() throws BlankPostException {

        post.setTitle("");
        Post newPost = postService.createPost(post, "batman");
        when(postRepository.save(newPost)).thenReturn(newPost);
    }
}
