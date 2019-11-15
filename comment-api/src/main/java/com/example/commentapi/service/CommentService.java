package com.example.commentapi.service;

import com.example.commentapi.model.Comment;

public interface CommentService {

    public Comment createComment (Comment comment, String username, Long postId);
    public Iterable<Comment> getAllCommentsByPostId(Long postId);
    public void deleteByCommentId(Long commentId);
}
