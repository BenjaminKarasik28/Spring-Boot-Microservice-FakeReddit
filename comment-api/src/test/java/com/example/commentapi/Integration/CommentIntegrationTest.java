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


    @Test
    public void getAllComments(){
        Iterable<Comment> comments = commentRepository.findAll();

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

}
