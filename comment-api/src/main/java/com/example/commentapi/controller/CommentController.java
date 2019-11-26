package com.example.commentapi.controller;

import com.example.commentapi.exceptionhandling.BlankCommentException;
import com.example.commentapi.exceptionhandling.ErrorResponse;
import com.example.commentapi.model.Comment;
import com.example.commentapi.model.PostComment;

import com.example.commentapi.service.CommentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class CommentController {

    @Autowired
    CommentService commentService;

    @ApiOperation(value = "Get All Comments", notes = "Returns all comment objects", response = Iterable.class)
    @GetMapping("/list")
    public Iterable<Comment> getAllComments() {
        return commentService.getAllComments();
    }

    @ApiOperation(value = "Get Comments by post Id", notes = "Returns all PostComment objects", response = PostComment.class)
    @GetMapping("/list/{postId}")
    public PostComment getAllCommentsByPostId(@PathVariable Long postId) {
        return commentService.getAllCommentsByPostId(postId);
    }
    @ApiOperation(value = "Get email by post Id", notes = "Returns email by postId", response = String.class)
    @GetMapping("/post/user/{postId}")
    public String getEmailByPostId(@PathVariable Long postId){
        return commentService.getEmailbyPostId(postId);
    }

    @PostMapping("/comment/{postId}")
    public String createComment(@RequestBody Comment comment, @RequestHeader("username") String username, @PathVariable Long postId) {
        return commentService.createComment(comment, username, postId);
    }

    @PutMapping("/{commentId}")
    public Comment updateComment(@PathVariable Long commentId, @RequestBody Comment comment) {
        return commentService.updateComment(comment, commentId);
    }

    @DeleteMapping("/{commentId}")
    public void deleteCommentById(@PathVariable Long commentId) {
        commentService.deleteByCommentId(commentId);
    }

    @DeleteMapping("/post/name/{username}")
    public void deleteCommentByUsername(@PathVariable String username){
        commentService.deleteCommentByUsername(username);
    }

    @DeleteMapping("/post/{postId}")
    public Long deletePostAndComments(@PathVariable Long postId){
        return commentService.deletePostAndComments(postId);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(BlankCommentException err){
        ErrorResponse error = new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), err.getMessage());
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

}
