package com.example.postapi.controller;

import com.example.postapi.exceptionhandling.BlankPostException;
import com.example.postapi.exceptionhandling.ErrorResponse;
import com.example.postapi.model.Post;
import com.example.postapi.model.PostComment;
import com.example.postapi.service.PostService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
public class PostController {


    @Autowired
    PostService postService;

    @ApiOperation(value = "Create Post", notes = "Provide title and description; must be logged in", response = Post.class)
    @PostMapping("/post")
    public Post createPost(@RequestBody Post post, @RequestHeader("username") String username) {
        return postService.createPost(post, username);
    }
    @ApiOperation(value = "Fetch all posts", notes = "Returns a list of all posts", response = Iterable.class)
    @GetMapping("/list")
    public Iterable<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @ApiOperation(value = "Fetch all posts by username", notes = "Returns a list of all posts for a given user, must be logged in", response = Iterable.class)
    @GetMapping("/{username}")
    public Iterable<Post> getAllPostByUsername(@RequestHeader("username") String username) {
        return postService.getAllPostsByUsername(username);
    }
    @ApiOperation(value = "Fetch all comments by post id", notes = "Returns a list of all comments for a given post", response = PostComment.class)
    @GetMapping("/comment/{postId}")
    public PostComment getCommentsByPostId(@PathVariable Long postId){
        return postService.getAllCommentsByPostId(postId);

    }
    @ApiOperation(value = "Fetch post by post id", notes = "Returns a post by post id", response = Optional.class)
    @GetMapping("/post/{postId}")
    public Optional<Post> getPostById(@PathVariable Long postId){
        return postService.getPostById(postId);
    }

    @ApiOperation(value = "Delete post by post id", notes = "Deletes a post by its id", response = Long.class)
    @DeleteMapping("/{postId}")
    public Long deletePostbyId(@PathVariable Long postId) {
        return postService.deletePostbyId(postId);
    }

    @ApiOperation(value = "Delete post by username", notes = "Deletes a post by its author", response = String.class)
    @DeleteMapping("/post/{username}")
    public String deletePostByUsername(@PathVariable String username) {
        return postService.deletePostByUsername(username);}

    @ApiOperation(value = "Update post by id", notes = "Updated a post bt id, must be logged in, must update title AND description", response = Post.class)
    @PutMapping("/post/{postId}")
    public Post updatePostById(@PathVariable Long postId, @RequestBody Post post) {
        return postService.updatePost(post, postId);
    }

    @ApiOperation(value = "Send Post id to user API", notes = "Call used for communication between comment and user APIs, passes username to user API", response = String.class)
    @GetMapping("/user/{postId}")
    public String sendPostIdRestTemplate(@PathVariable Long postId){
        return postService.sendPostIdRestTemplate(postId);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(BlankPostException err){
        ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), err.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
