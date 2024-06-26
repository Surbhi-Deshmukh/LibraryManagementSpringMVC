package com.nagarro.advjavaassgn5a.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.nagarro.advjavaassgn5a.entity.Users;
import com.nagarro.advjavaassgn5a.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = UserController.class)
class UserControllerTest {

    @MockBean
    private UserService userService;
    @Autowired
    private MockMvc mockMvc;
    private final ObjectWriter objectWriter = new ObjectMapper().writer();
    private Users user1;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user1 = new Users("username", "password");
    }

    @Test
    void testLoginUser() throws Exception {
        when(userService.authenticateUser(user1.getUsername(), user1.getPassword())).thenReturn(user1);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectWriter.writeValueAsString(user1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", is(user1.getUsername())));
    }

    @Test
    void testRegisterUser() throws Exception{
        when(userService.addUser(any())).thenReturn(user1);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/user/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectWriter.writeValueAsString(user1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", is(user1.getUsername())));
    }

    @Test
    void testRemoveUser() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/user/" + user1.getUsername()))
                .andExpect(status().isOk())
                .andExpect(content().string("User deleted successfully"));
    }
}