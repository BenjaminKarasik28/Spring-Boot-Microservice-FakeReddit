package com.example.commentapi.repository;

import com.example.commentapi.model.Comment;
import com.example.commentapi.model.PostComment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {

    //    getAllComments - CR.findAll()
    // *    getAllCommentsByPostId - CR.findAllByPostId
    //    getEmailByPostId - RT.getForObject
    //    createComment - RT.getForObject
    //*    updateComment - CR.findByCommentId
    //*    deleteCommentById - CR.deleteByPostId
    //*   deleteCommentByUsername - CR.deleteByUsername
    //*    deletePostAndComments - CR.deleteByPostId


    //    getAllComments - CR.findAll()

    public Iterable<Comment> findAllByPostId(Long postId);

    //    getEmailByPostId - RT.getForObject

//    PostComment findByPostId(Long postId)

    //    createComment - RT.getForObject

    public Comment findByCommentId(Long commentId);

    //*    deleteCommentById - CR.deleteByPostId

    @Transactional
    public void deleteByUsername(String username);

    @Transactional
    public Long deleteByPostId(Long PostId);

}
