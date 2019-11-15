package com.example.commentapi.service;

import com.example.commentapi.model.Comment;
import com.example.commentapi.model.PostComment;

public interface CommentService {

    public Comment createComment (Comment comment, String username, Long postId);
    public PostComment getAllCommentsByPostId(Long postId);
    public void deleteByCommentId(Long commentId);
    public Long deletePostAndComments(Long postId);
}
