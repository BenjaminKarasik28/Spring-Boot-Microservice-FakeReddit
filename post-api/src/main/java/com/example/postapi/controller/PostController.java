package com.example.postapi.controller;

import com.example.postapi.model.Post;
import com.example.postapi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class PostController {

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

    @GetMapping("/{userId}")
    public Iterable<Post> getAllPostById(@RequestHeader("userId") Long userId) {
        return postService.getAllPostById(userId);
    }

    @DeleteMapping("/{postId}")
    public void deletePostbyId(@PathVariable Long postId) {
        postService.deletePostbyId(postId);
    }


}
