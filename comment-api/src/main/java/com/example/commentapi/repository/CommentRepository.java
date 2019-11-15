package com.example.commentapi.repository;

import com.example.commentapi.model.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
    public Iterable<Comment> findAllByPostId(Long postId);
//    public Iterable<Comment> findCommentsByPostId(Long postId);
    @Transactional
    public Long deleteByPostId(Long PostId);
}
