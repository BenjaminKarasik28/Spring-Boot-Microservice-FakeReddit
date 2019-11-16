package com.example.commentapi.controller;

import com.example.commentapi.model.Comment;
import com.example.commentapi.model.PostComment;
import com.example.commentapi.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {

    @Autowired
    CommentService commentService;

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @PostMapping("/comment/{postId}")
    public Comment createComment(@RequestBody Comment comment, @RequestHeader("username") String username, @PathVariable Long postId) {
        return commentService.createComment(comment, username, postId);
    }


    @GetMapping("/list/{postId}")
    public PostComment getAllCommentsByPostId(@PathVariable Long postId) {
        return commentService.getAllCommentsByPostId(postId);
    }


    @DeleteMapping("/{commentId}")
    public void deleteCommentById(@PathVariable Long commentId) {
        commentService.deleteByCommentId(commentId);
    }

    @DeleteMapping("/post/{postId}")
    public Long deletePostAndComments(@PathVariable Long postId){
        return commentService.deletePostAndComments(postId);
    }

}
