package com.nagarro.assgn5frontend.controller;

import com.nagarro.assgn5frontend.entity.User;
import com.nagarro.assgn5frontend.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class UserControllerTest {
    @Mock
    private UserService userService;
    @InjectMocks
    private UserController userController;
    private MockMvc mockMvc;
    private User user1;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        mockMvc = MockMvcBuilders.standaloneSetup(userController).setViewResolvers(viewResolver).build();
        user1 = new User("testuser", "password");
    }

    @Test
    void testShowLoginForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    void testSubmitLoginFormSuccess() throws Exception {
        when(userService.authenticateUser(any(User.class))).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.post("/authenticate")
                        .param("username", user1.getUsername())
                        .param("password", user1.getPassword()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/home"))
                .andExpect(redirectedUrl("/home"));
    }

    @Test
    void testSubmitLoginFormFailure() throws Exception {
        when(userService.authenticateUser(any(User.class))).thenReturn(false);

        mockMvc.perform(MockMvcRequestBuilders.post("/authenticate")
                        .param("username", user1.getUsername())
                        .param("password", "wrongpassword"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/login"))
                .andExpect(redirectedUrl("/login"));
    }

    @Test
    void testLogout() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/logout"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/login"))
                .andExpect(redirectedUrl("/login"));
    }
}