package com.example.postapi.controller;

import com.example.postapi.model.DummyComment;
import com.example.postapi.model.Post;
import com.example.postapi.model.PostComment;
import com.example.postapi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@RestController
public class PostController {

    RestTemplate restTemplate = new RestTemplate();

    @Autowired
    PostService postService;

    @PostMapping("/post")
    public Post createPost(@RequestBody Post post, @RequestHeader("username") String username) {
        return postService.createPost(post, username);
    }

    @GetMapping("/list")
    public Iterable<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{username}")
    public Iterable<Post> getAllPostById(@RequestHeader("username") String username) {
        return postService.getAllPostsByUsername(username);
    }

    @GetMapping("/{postId}/comment")
    public PostComment getCommentsByPostId(@PathVariable Long postId){

        String hello = restTemplate.getForObject("http://localhost:8083/hello", String.class);
        System.out.println(hello);

        System.out.println(postId);
        PostComment postComment = restTemplate.getForObject("http://localhost:8083/" + postId +"/list", PostComment.class);
        System.out.println(postComment.getPostComment());
        return postComment;
    }


    @DeleteMapping("/{postId}")
    public void deletePostbyId(@PathVariable Long postId) {
        postService.deletePostbyId(postId);
    }

    //TODO: add method to delete all the comments once Post is deleted

}
