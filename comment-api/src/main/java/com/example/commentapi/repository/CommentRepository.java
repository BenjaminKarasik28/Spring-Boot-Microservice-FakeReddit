package com.example.commentapi.repository;

import com.example.commentapi.model.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
    public Iterable<Comment> findAllByPostId(Long postId);
//    public Iterable<Comment> findCommentsByPostId(Long postId);
    public Long deleteByPostId(Long PostId);
}
