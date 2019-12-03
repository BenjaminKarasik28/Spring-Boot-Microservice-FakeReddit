package com.example.postapi.model;

import com.example.postapi.exceptionhandling.ErrorResponse;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ErrorResponseTest {

    private ErrorResponse errorResponse;
    private ErrorResponse errorResponse2;

    @Before
    public void init(){
        errorResponse = new ErrorResponse();
        errorResponse.setMessage("message");
        errorResponse.setStatus(1);
        errorResponse2 = new ErrorResponse(1, "message");
    }
    @Test
    public void statusGetter_String_Success(){
        assertEquals(java.util.Optional.of(1), java.util.Optional.of(errorResponse.getStatus()));
        assertEquals(java.util.Optional.of(1), java.util.Optional.of(errorResponse2.getStatus()));
    }
    @Test
    public void messageGetter_String_Success(){
        assertEquals(java.util.Optional.of("message"), java.util.Optional.of(errorResponse.getMessage()));
        assertEquals(java.util.Optional.of("message"), java.util.Optional.of(errorResponse2.getMessage()));
    }


}