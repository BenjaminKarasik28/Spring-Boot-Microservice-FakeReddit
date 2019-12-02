package com.example.apigateway.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import springfox.documentation.swagger.web.SwaggerResource;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class DocumentationControllerTest {

    @InjectMocks
    DocumentationController documentationController;

    @Test
    public void get_List_Success() {
        List<SwaggerResource> getList = documentationController.get();
        assertEquals("user-api", getList.get(0).getName());
    }

}
