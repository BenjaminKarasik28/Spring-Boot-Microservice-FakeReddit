package com.example.commentapi.controller;

import com.example.commentapi.model.Comment;
import com.example.commentapi.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {

    @Autowired
    CommentService commentService;

    @PostMapping("/comment")
    public Comment createComment(@RequestBody Comment comment, @RequestHeader("username") String username) {
        return commentService.createComment(comment, username);
    }


    @GetMapping("/{postId}/list")
    public Iterable<Comment> getAllCommentsByPostId(@PathVariable Long postId) {
        return commentService.getAllCommentsByPostId(postId);
    }


    @DeleteMapping("/{commentId}")
    public void deleteCommentById(@PathVariable Long commentId) {
        commentService.deleteByCommentId(commentId);
    }

}
