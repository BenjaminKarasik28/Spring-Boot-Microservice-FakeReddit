package com.example.postapi.controller;

import com.example.postapi.exceptionhandling.BlankPostException;
import com.example.postapi.exceptionhandling.ErrorResponse;
import com.example.postapi.model.Post;
import com.example.postapi.model.PostComment;
import com.example.postapi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


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
    @GetMapping("/comment/{postId}")
    public PostComment getCommentsByPostId(@PathVariable Long postId){
        return postService.getAllCommentsByPostId(postId);

    }

    @GetMapping("/post/{postId}")
    public Optional<Post> getPostById(@PathVariable Long postId){
        return postService.getPostById(postId);
    }


    @DeleteMapping("/{postId}")
    public void deletePostbyId(@PathVariable Long postId) {
        postService.deletePostbyId(postId);
    }

    @DeleteMapping("/post/{username}")
    public String deletePostByUsername(@PathVariable String username){return postService.deletePostByUsername(username);}

    @PutMapping("/post/{postId}")
    public Post updatePostById(@PathVariable Long postId, @RequestBody Post post) {
        return postService.updatePost(post, postId);
    }

    @GetMapping("/user/{postId}")
    public String sendPostIdRestTemplate(@PathVariable Long postId){
        return postService.sendPostIdRestTemplate(postId);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(BlankPostException err){
        ErrorResponse error = new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), err.getMessage());
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }
}
