package com.nagarro.advjavaassgn5a.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.nagarro.advjavaassgn5a.entity.Author;
import com.nagarro.advjavaassgn5a.service.AuthorService;
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

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = AuthorController.class)
class AuthorControllerTest {

    @MockBean
    private AuthorService authorService;
    @Autowired
    private MockMvc mockMvc;
    private Author author1, author2, author3;
    private final ObjectWriter objectWriter = new ObjectMapper().writer();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        initializeAuthors();
    }

    @Test
    void testGetAuthors() throws Exception {
        when(authorService.getAllAuthors()).thenReturn(List.of(author1, author2, author3));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/authors"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$.[1].name", is(author2.getName())));

    }

    @Test
    void testGetSingleAuthor() throws Exception {
        when(authorService.getAuthor(1)).thenReturn(author1);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/author/" + author1.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(author1.getId())))
                .andExpect(jsonPath("$.name", is(author1.getName())));
    }

    @Test
    void testAddAuthor() throws Exception {
        when(authorService.addAuthor(author1)).thenReturn(author1);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/author")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectWriter.writeValueAsString(author1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(author1.getId())))
                .andExpect(jsonPath("$.name", is(author1.getName())));

    }

    @Test
    void testDeleteAuthor() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/authors/" + author1.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string("Author's Record deleted successfully"));
    }

    private void initializeAuthors() {
        author1 = new Author(1, "Robert Scoble", null);
        author2 = new Author(2, "Samuel Glasstone", null);
        author3 = new Author(3, "David Blatner", null);

    }
}