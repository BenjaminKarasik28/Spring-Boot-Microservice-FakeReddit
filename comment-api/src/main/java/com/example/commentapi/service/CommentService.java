package com.example.commentapi.service;

import com.example.commentapi.model.Comment;
import com.example.commentapi.model.PostComment;
import org.springframework.http.HttpStatus;

public interface CommentService {

    public Iterable<Comment> getAllComments();
    public PostComment getAllCommentsByPostId(Long postId);
    public String getEmailbyPostId(Long postId);
    public String createComment (Comment comment, String username, Long postId);
    public Comment updateComment (Comment comment, Long commentId);
    public void deleteByCommentId(Long commentId);
    public void deleteCommentByUsername(String username);
    public Long deletePostAndComments(Long postId);
}
