package com.example.commentapi.Integration;

import com.example.commentapi.repository.CommentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.dao.DataIntegrityViolationException;

import com.example.commentapi.repository.CommentRepository;
import com.example.commentapi.model.Comment;
import com.example.commentapi.model.PostComment;
import com.example.commentapi.model.DummyPost;

import java.util.Arrays;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@ActiveProfiles("qa")
@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentIntegrationTest {

    @Autowired
    CommentRepository commentRepository;

    private Comment createComment(){

        Comment comment = new Comment();
        comment.setId((long) 1);
        comment.setPostId((long) 1);
        comment.setUsername("batman");
        comment.setText("Some text");
        return comment;
    }

    private Iterable<Comment> createComments(){
        Iterable<Comment> comments = Arrays.asList(
                new Comment(
                        1L,
                        1L,
                        "some text",
                        "user1"
                )
        );
                return comments;
    }
    


    @Test
    public void getAllComments_Comment_Success(){
    Iterable<Comment> comments = commentRepository.findAll();

    assertNotNull(comments);
    }


    @Test
    public void getAllCommentsByPostId_PostComment_Success(){
        Iterable<Comment> comments = commentRepository.findAllByPostId(1L);

        assertNotNull(comments);
    }


    @Test
    public void createComment_Comment_success(){
        Comment comment = commentRepository.findByCommentId((long) 1);
        if(comment != null){
            commentRepository.delete(comment);
        }
        comment = createComment();
        comment = commentRepository.save(comment);
        Comment foundComment = commentRepository.findByCommentId(comment.getId());

        assertNotNull(comment);
        assertNotNull(foundComment);
        assertEquals(comment.getId(), foundComment.getId());

        commentRepository.delete(comment);
    }

//    @Override
//    public Comment updateComment(Comment comment, Long commentId) {
//        Comment savedComment = commentRepository.findByCommentId(commentId);
//
//        if(comment.getText() != null) savedComment.setText(comment.getText());
//        return commentRepository.save(savedComment);
//    }

    @Test
    public void updateComment_Comment_Success() {
        Comment comment = commentRepository.findByCommentId((long) 1);
        if(comment == null) {
            comment = createComment();
            comment = commentRepository.save(comment);
        }

        Comment foundComment = commentRepository.findByCommentId(comment.getId());

        foundComment.setText("Some text");

        assertNotNull(comment);
        assertNotNull(foundComment);
        assertEquals(comment.getId(), foundComment.getId());

        commentRepository.delete(comment);

    }



    @Test
    public void deleteById_Comment_Success() {
        Comment comment = commentRepository.findByCommentId((long) 1);
        if(comment == null) {
            comment = createComment();
            comment = commentRepository.save(comment);
        }

        commentRepository.deleteById(comment.getId());
        Comment foundComment = commentRepository.findById(comment.getId()).orElse(null);

        assertNull(foundComment);
    }

    @Test
    public void deleteByUsername_Comment_Success() {
        Comment comment = commentRepository.findByCommentId(1L);
        if(comment == null) {
            comment = createComment();
            comment = commentRepository.save(comment);
        }

        commentRepository.deleteByUsername(comment.getUsername());
        Comment foundComment = commentRepository.findById(comment.getId()).orElse(null);

        assertNull(foundComment);
    }

    @Test
    public void deleteByPostId_Comment_Success() {
        Comment comment = commentRepository.findByCommentId(1L);
        if(comment == null) {
            comment = createComment();
            comment = commentRepository.save(comment);
        }

        commentRepository.deleteByPostId(comment.getPostId());
        Comment foundComment = commentRepository.findById(comment.getId()).orElse(null);

        assertNull(foundComment);
    }


}
