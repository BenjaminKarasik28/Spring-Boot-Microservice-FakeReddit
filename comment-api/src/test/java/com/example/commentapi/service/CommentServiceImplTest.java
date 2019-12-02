package com.example.commentapi.service;

import com.example.commentapi.controller.CommentController;
import com.example.commentapi.exceptionhandling.BlankCommentException;
import com.example.commentapi.repository.CommentRepository;
import com.example.commentapi.service.CommentService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.commentapi.model.Comment;
import com.example.commentapi.model.DummyPost;
import com.example.commentapi.model.PostComment;
import org.springframework.web.client.RestTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;
//        import java.util.Queue;
import org.springframework.amqp.core.Queue;




import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class CommentServiceImplTest {

    private MockMvc mockMvc;

    private Comment sampleComment;

    private Iterable<Comment> sampleCommentList;

    private PostComment samplePostComment;

    private DummyPost sampleDummyPost;

    @InjectMocks
    CommentServiceImpl commentServiceImpl;

    @Mock
    private Queue sampleQue;

    @Mock
    RestTemplate restTemplate;

    @Mock
    RabbitTemplate rabbitTemplate;

    @Mock
    CommentRepository commentRepository;


    @Before
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(commentServiceImpl).build();
        sampleComment = new Comment(
                1L,
                1L,
                "some text",
                "user1"
        );


        sampleCommentList = Arrays.asList(
                new Comment(
                        1L,
                        1L,
                        "some text",
                        "user1"
                )
        );

        samplePostComment = new PostComment();
        samplePostComment.setPostComment(sampleCommentList);

        sampleDummyPost = new DummyPost(
                "Dummy Title",
                "Some Text",
                "user1",
                1L
        );

        sampleQue = new Queue("queue1", false, false, false);


    }

    @Test
    public void getAllComments() throws Exception {
        getAllComments_Comment_Success();
    }

    @Test
    public void getAllCommentsByPostId() throws Exception {
        getAllCommentsByPostId_Comment_Success();
    }


    @Test
    public void getEmailbyPostId() throws Exception {
        getEmailbyPostId_Comment_Success();
    }


        @Test
    public void createComment() throws Exception {
        createComment_String_Success();
    }


    @Test
    public void updateComment() throws Exception {
        updateComment_Comment_Success();
    }

    @Test
    public void deleteByCommentId() throws Exception {
        deleteByCommentId_Comment_Success();
    }

    @Test
    public void deleteCommentByUsername() throws Exception {
        deleteCommentByUsername_Comment_Success();
    }

    @Test
    public void deletePostAndComments() throws Exception {
        deletePostAndComments_Comment_Success();
    }

    private void getAllComments_Comment_Success() throws Exception {
        when(commentRepository.findAll()).thenReturn(sampleCommentList);

        Iterable<Comment> actual = commentServiceImpl.getAllComments();

        assertNotNull(actual);
        assertEquals(sampleCommentList, actual);
    }

    private void getAllCommentsByPostId_Comment_Success() throws Exception {
        when(commentRepository.findAllByPostId(anyLong())).thenReturn(sampleCommentList);
        PostComment actual = commentServiceImpl.getAllCommentsByPostId(1L);

        assertNotNull(actual);

    }

    private void getEmailbyPostId_Comment_Success() throws Exception {
        when(restTemplate.getForObject("http://localhost:8082/user/" + 1L, String.class)).thenReturn("userEmail");

        String email = commentServiceImpl.getEmailbyPostId(1L);

        assertNotNull(email);
        assertEquals("userEmail",email);

    }



    private void createComment_String_Success() throws Exception {

        when(restTemplate.getForObject(anyString(), any())).thenReturn(sampleDummyPost, "email");
        when(commentRepository.save(any())).thenReturn(sampleComment);
        String email = commentServiceImpl.createComment(sampleComment, "user1", sampleDummyPost.getId());
        assertNotNull(email);

    }

    private void updateComment_Comment_Success() throws Exception {
        when(commentRepository.findByCommentId(anyLong())).thenReturn(sampleComment);

        Comment savedComment = commentRepository.findByCommentId(anyLong());

       commentServiceImpl.updateComment(sampleComment, anyLong());

        commentRepository.save(savedComment);


        assertNotNull(savedComment);
        assertEquals(sampleComment,savedComment);

    }


    private void deleteByCommentId_Comment_Success() throws Exception {
        commentRepository.deleteById(anyLong());

        verify(commentRepository, times(1)).deleteById(anyLong());

        commentServiceImpl.deleteByCommentId(anyLong());

    }


    private void deleteCommentByUsername_Comment_Success() throws Exception {
        commentRepository.deleteByUsername(anyString());

        verify(commentRepository,times(1)).deleteByUsername(anyString());

        commentServiceImpl.deleteCommentByUsername(anyString());
    }



    private void deletePostAndComments_Comment_Success() throws Exception {
        commentRepository.deleteByPostId(anyLong());

        verify(commentRepository, times(1)).deleteByPostId(anyLong());

        commentServiceImpl.deletePostAndComments(anyLong());
    }



}
