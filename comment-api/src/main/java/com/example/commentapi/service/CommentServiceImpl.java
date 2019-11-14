package com.example.commentapi.service;


import com.example.commentapi.model.Comment;
import com.example.commentapi.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Override
    public Comment createComment(Comment comment, String username) {
        comment.setUsername(username);
        return commentRepository.save(comment);
    }

    @Override
    public Iterable<Comment> getAllCommentsByPostId(Long postId) {
        return commentRepository.findAllByPostId(postId);
    }

    @Override
    public void deleteByCommentId(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
