package com.nagarro.assgn5frontend.controller;

import com.nagarro.assgn5frontend.entity.Book;
import com.nagarro.assgn5frontend.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.Arrays;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class HomeControllerTest {
    @Mock
    private BookService bookService;
    @InjectMocks
    private HomeController homeController;
    private MockMvc mockMvc;
    private Book book1, book2, book3;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        mockMvc = MockMvcBuilders.standaloneSetup(homeController).setViewResolvers(viewResolver).build();
    }

    @Test
    void testShowHome() throws Exception {
        when(bookService.getAllBooks()).thenReturn(Arrays.asList(book1, book2));

        mockMvc.perform(MockMvcRequestBuilders.get("/home"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"))
                .andExpect(model().attributeExists("allbooks"));
    }
    private void initializeBooks() {
        book1 = new Book("1", "Java Fundamentals", null, new Date());
        book2 = new Book("2", "Java FullStack", null, new Date());
        book3 = new Book("3", "React Js", null, new Date());
    }
}