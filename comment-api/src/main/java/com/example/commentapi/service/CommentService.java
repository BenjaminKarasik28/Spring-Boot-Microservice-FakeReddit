package com.example.commentapi.service;

import com.example.commentapi.model.Comment;
import com.example.commentapi.model.PostComment;
import org.springframework.http.HttpStatus;

public interface CommentService {

    public Comment createComment (Comment comment, String username, Long postId);
    public PostComment getAllCommentsByPostId(Long postId);
    public void deleteByCommentId(Long commentId);
    public Long deletePostAndComments(Long postId);
    public Iterable<Comment> getAllComments();
    public void deleteCommentByUsername(String username);
    public Comment updateComment (Comment comment, Long commentId);
}
