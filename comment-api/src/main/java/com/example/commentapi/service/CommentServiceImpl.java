package com.example.commentapi.service;


import com.example.commentapi.model.Comment;
import com.example.commentapi.model.PostComment;
import com.example.commentapi.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Override
    public Comment createComment(Comment comment, String username, Long postId) {
        comment.setUsername(username);
        comment.setPostId(postId);
        return commentRepository.save(comment);
    }

    @Override
    public PostComment getAllCommentsByPostId(Long postId) {
       Iterable<Comment> commentList = commentRepository.findAllByPostId(postId);

       PostComment postComment =new PostComment();
       postComment.setPostComment(commentList);
       return postComment;

    }

    @Override
    public void deleteByCommentId(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
