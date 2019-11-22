package com.example.commentapi.service;


import com.example.commentapi.model.Comment;
import com.example.commentapi.model.DummyPost;
import com.example.commentapi.model.PostComment;
import com.example.commentapi.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepository commentRepository;

    RestTemplate restTemplate = new RestTemplate();

    @Override
    public Comment createComment(Comment comment, String username, Long postId) {

        DummyPost dummyPost = restTemplate.getForObject("http://localhost:8082/post/" + postId, DummyPost.class);

        if(dummyPost.getId().equals(postId)) {
            comment.setPostId(postId);
            comment.setUsername(username);
            return commentRepository.save(comment);
        }
        return null;
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

    @Override
    public Long deletePostAndComments(Long postId) {
        return commentRepository.deleteByPostId(postId);

        }

    @Override
    public Iterable<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    @Override
    public void deleteCommentByUsername(String username){
        commentRepository.deleteByUsername(username);
    }

    @Override
    public Comment updateComment(Comment comment, Long commentId) {
        Comment savedComment = commentRepository.findByCommentId(commentId);

        if(comment.getText() != null) savedComment.setText(comment.getText());
        return commentRepository.save(savedComment);
    }

}
