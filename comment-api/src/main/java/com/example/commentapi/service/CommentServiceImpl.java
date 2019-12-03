package com.example.commentapi.service;


import com.example.commentapi.exceptionhandling.BlankCommentException;
import com.example.commentapi.model.Comment;
import com.example.commentapi.model.DummyPost;
import com.example.commentapi.model.PostComment;
import com.example.commentapi.repository.CommentRepository;
import com.netflix.discovery.converters.Auto;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class CommentServiceImpl implements CommentService {

    private static Logger logger = LoggerFactory.getLogger(CommentServiceImpl.class.getName());

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    Queue queue;

    @Autowired
    RestTemplate restTemplate;


    @Override
    public Iterable<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    @Override
    public PostComment getAllCommentsByPostId(Long postId) {
        Iterable<Comment> commentList = commentRepository.findAllByPostId(postId);
        PostComment postComment =new PostComment();
        postComment.setPostComment(commentList);
        return postComment;
    }

    @Override
    public String getEmailbyPostId(Long postId) {
        String email = restTemplate.getForObject("http://localhost:8082/user/" + postId, String.class);
        return email;
    }

    @Override
    public String createComment(Comment comment, String username, Long postId) throws BlankCommentException {

        if(comment.getText().isEmpty()) {
            logger.error(username + " attempted to create an empty text");
            throw new BlankCommentException("Please enter text for your comment");
        } else {
           DummyPost dummyPost = restTemplate.getForObject("http://localhost:8082/post/" + postId, DummyPost.class);

            if(dummyPost.getId().equals(postId)) {
                comment.setPostId(postId);
                comment.setUsername(username);
                commentRepository.save(comment);
                String email = restTemplate.getForObject("http://localhost:8082/user/" + postId, String.class);
                rabbitTemplate.convertAndSend(queue.getName(), email);
                logger.info(username + " created a comment and email has been sent to post owner at " + email);
                return email;

            }
            return null;
        }
    }


    @Override
    public Comment updateComment(Comment comment, Long commentId) {
        Comment savedComment = commentRepository.findByCommentId(commentId);

        if(comment.getText() != null) savedComment.setText(comment.getText());
        return commentRepository.save(savedComment);
    }

    @Override
    public void deleteByCommentId(Long commentId) {
        commentRepository.deleteById(commentId);
    }


    @Override
    public void deleteCommentByUsername(String username){
        commentRepository.deleteByUsername(username);
    }


    @Override
    public Long deletePostAndComments(Long postId) {
        return commentRepository.deleteByPostId(postId);
    }

}
