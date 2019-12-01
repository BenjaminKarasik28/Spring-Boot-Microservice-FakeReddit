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
        import java.util.Queue;

        import static org.junit.Assert.*;
        import static org.mockito.ArgumentMatchers.*;
        import static org.mockito.Mockito.*;
        import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
        import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
        import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class CommentServiceImplTest {

    private MockMvc mockMvc;

    private Comment sampleComment;

    private Iterable<Comment> sampleCommentList;

    private PostComment samplePostComment;

    private DummyPost sampleDummyPost;

    private Queue sampleQue;

    @InjectMocks
    CommentServiceImpl commentServiceImpl;

    @Mock
    RestTemplate restTemplate;

    @Mock
    RabbitTemplate rabbitTemplate;

    @Mock
    CommentRepository commentRepository;

    @MockBean
    Queue queue;
//    @Mock
//    RabbitTemplate rabbitTemplate;

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

//        sampleQue = new Queue<>(
//
//        );


    }

    //    getAllComments
    //    getAllCommentsByPostId
    //    getEmailByPostId
    //*    createComment
    //*    updateComment
    //    deleteCommentById
    //   deleteCommentByUsername
    //   deletePostAndComments

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
//        when(commentServiceImpl.getAllCommentsByPostId(anyLong())).thenReturn(samplePostComment);

        PostComment actual = commentServiceImpl.getAllCommentsByPostId(1L);

        assertNotNull(actual);

    }

    private void getEmailbyPostId_Comment_Success() throws Exception {
        when(restTemplate.getForObject("http://localhost:8082/user/" + 1L, String.class)).thenReturn("userEmail");

        String email = commentServiceImpl.getEmailbyPostId(1L);

        assertNotNull(email);
        assertEquals("userEmail",email);

    }

//    @Override
//    public String createComment(Comment comment, String username, Long postId) throws BlankCommentException {
//
//        if(comment.getText().isEmpty()) {
//            throw new BlankCommentException("Please enter text for your comment");
//        } else {
//            DummyPost dummyPost = restTemplate.getForObject("http://localhost:8082/post/" + postId, DummyPost.class);
//
//
//            if(dummyPost.getId().equals(postId)) {
//                comment.setPostId(postId);
//                comment.setUsername(username);
//                commentRepository.save(comment);
//                String email = restTemplate.getForObject("http://localhost:8082/user/" + postId, String.class);
//                rabbitTemplate.convertAndSend(queue.getName(), email);
//                return email;
//
//            }
//            return null;
//        }
//    }



    private void createComment_String_Success() throws Exception {

//        when(restTemplate.getForObject(anyString(),any())).thenReturn("userEmail");
//
//        when(restTemplate.getForObject("http://localhost:8082/post/" + 1L, DummyPost.class)).thenReturn(sampleDummyPost);
//
//        when(commentServiceImpl.createComment(sampleComment, "user1", sampleDummyPost.getId())).thenReturn("userEmail");
//
////        when(rabbitTemplate.convertAndSend(any(),anyString())).thenReturn()
////        verify(rabbitTemplate.convertAndSend(any(),anyString()));
//
//        rabbitTemplate.convertAndSend(any(),anyString());
//
//        String userEmail = commentServiceImpl.createComment(sampleComment,"user1",sampleDummyPost.getId());
//
//        commentRepository.save(sampleComment);

        when(restTemplate.getForObject(anyString(), any())).thenReturn(sampleDummyPost, "email");
        when(commentRepository.save(any())).thenReturn(sampleComment);
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        RabbitTemplate spy = Mockito.spy(rabbitTemplate);
        Mockito.doNothing().when(spy).convertAndSend(any(), anyString());
        String email = commentServiceImpl.createComment(sampleComment, "username", 1L);
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
