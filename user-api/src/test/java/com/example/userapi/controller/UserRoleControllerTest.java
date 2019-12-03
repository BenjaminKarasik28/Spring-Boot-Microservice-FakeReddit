package com.example.userapi.controller;

import com.example.userapi.config.JwtUtil;
import com.example.userapi.model.UserRole;
import com.example.userapi.repository.UserRoleRepository;
import com.example.userapi.service.UserRoleService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class UserRoleControllerTest {

    private MockMvc mockMvc;
    private UserRole role;

    @InjectMocks
    UserRoleController userRoleController;

    @Mock
    UserRoleService userRoleService;

    @Mock
    private JwtUtil jwtUtil;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(userRoleController).build();
    }

    @Before
    public void initialize(){
        role = new UserRole();
        role.setId(1);
        role.setName("ADMIN");
    }

    @Test
    public void getRole_Role_Success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/role/list")
                .accept(MediaType.APPLICATION_JSON);

//        when(userRoleService.getRole(any())).thenReturn(role);

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    public void createRole_Role_Success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/role")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createRoleInJson("ADMIN"));

        when(userRoleService.createRole(any())).thenReturn(role);

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().json("{\"name\":\"ADMIN\"}"))
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }


    //This converts to JSON object
    private static String createRoleInJson(String role) {
        return "{ \"name\": \"" + role + "\"}";
    }
}