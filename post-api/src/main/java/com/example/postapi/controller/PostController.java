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

    @GetMapping("/{username}")
    public Iterable<Post> getAllPostById(@RequestHeader("username") String username) {
        return postService.getAllPostsByUsername(username);
    }

    @DeleteMapping("/{postId}")
    public void deletePostbyId(@PathVariable Long postId) {
        postService.deletePostbyId(postId);
    }

    //TODO: add method to delete all the comments once Post is deleted

}
