package com.example.commentapi.service;

        import com.example.commentapi.controller.CommentController;
        import com.example.commentapi.repository.CommentRepository;
        import com.example.commentapi.service.CommentService;
        import org.junit.Before;
        import org.junit.Test;
        import org.junit.runner.RunWith;
        import org.mockito.InjectMocks;
        import org.mockito.Mock;
        import org.mockito.junit.MockitoJUnitRunner;
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

        import java.util.Arrays;
        import java.util.Collections;
        import java.util.List;

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

    private List<Comment> samplePostComment;

    @InjectMocks
    CommentServiceImpl commentServiceImpl;

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

    }

    //    getAllComments
    // *    getAllCommentsByPostId
    //    getEmailByPostId
    //    createComment
    //*    updateComment
    //*    deleteCommentById
    //*   deleteCommentByUsername
    //*    deletePostAndComments

    @Test
    public void getAllComments() throws Exception {
        getAllComments_Comment_Success();
    }

    @Test
    public void getAllCommentsByPostId() throws Exception {
        getAllCommentsByPostId_Comment_Success();
    }




    //    @Test
//    public void createComment() throws Exception {
//        createComment_String_Success();
//    }





    private void getAllComments_Comment_Success() throws Exception {
        when(commentRepository.findAll()).thenReturn(sampleCommentList);

        Iterable<Comment> actual = commentServiceImpl.getAllComments();

        System.out.println(actual);
        System.out.println(sampleCommentList);

        assertNotNull(actual);
        assertEquals(sampleCommentList, actual);
    }

    private void getAllCommentsByPostId_Comment_Success() throws Exception {

    }

    //    private void createComment_String_Success() throws Exception {
//
//    }

}
