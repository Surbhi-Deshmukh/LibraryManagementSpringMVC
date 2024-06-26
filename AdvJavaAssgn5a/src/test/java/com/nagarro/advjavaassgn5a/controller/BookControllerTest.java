package com.nagarro.advjavaassgn5a.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.nagarro.advjavaassgn5a.entity.Author;
import com.nagarro.advjavaassgn5a.entity.Book;
import com.nagarro.advjavaassgn5a.service.BookService;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = BookController.class)
class BookControllerTest {

    @MockBean
    private BookService bookService;

    @Autowired
    private MockMvc mockMvc;
    private final ObjectWriter objectWriter = new ObjectMapper().writer();

    private Book book1, book2, book3;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        initializeBooks();

    }

    @Test
    void testAllBooks() throws Exception {
        when(bookService.getAllBooks()).thenReturn(List.of(book1, book2, book3));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/books")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$.[0].bookCode", is(book1.getBookCode())));
    }

    @Test
    void testAddNewBook() throws Exception {
        when(bookService.addBook(any())).thenReturn(book1);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectWriter.writeValueAsString(book1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bookCode", is(book1.getBookCode())))
                .andExpect(jsonPath("$.bookName", is(book1.getBookName())))
                .andExpect(jsonPath("$.author.id", is(book1.getAuthor().getId())));

    }

    @Test
    void testGetBookDetails() throws Exception {
        when(bookService.getBook("1")).thenReturn(book1);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/book/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bookCode", is(book1.getBookCode())))
                .andExpect(jsonPath("$.bookName", is(book1.getBookName())))
                .andExpect(jsonPath("$.author.id", is(book1.getAuthor().getId())));

    }

    @Test
    void testDeleteBook() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/book/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Book Record Deleted Successfully"));
    }

    @Test
    void testUpdateBook() throws Exception {
        when(bookService.updateBook(book1.getBookCode(), book2))
                .thenReturn(new Book(book1.getBookCode(), book2.getBookName(), book2.getAuthor(), book2.getAddedDate()));

        mockMvc.perform(MockMvcRequestBuilders.put("/api/book/" + book1.getBookCode())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectWriter.writeValueAsString(book2)))
                .andExpect(jsonPath("$.bookCode", is(book1.getBookCode())))
                .andExpect(jsonPath("$.bookName", is(book2.getBookName())));

    }

    private void initializeBooks() {
        book1 = new Book("1", "Java Fundamentals", new Author(1, "Author1", new ArrayList<>()), new Date());
        book2 = new Book("2", "Java FullStack", null, new Date());
        book3 = new Book("3", "React Js", null, new Date());
    }
}