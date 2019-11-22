package com.example.commentapi.repository;

import com.example.commentapi.model.Comment;
import com.example.commentapi.model.PostComment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {

    public Iterable<Comment> findAllByPostId(Long postId);

    @Transactional
    public Long deleteByPostId(Long PostId);

    @Transactional
    public void deleteByUsername(String username);

    public Comment findByCommentId(Long commentId);

    PostComment findByPostId(Long postId);
}
