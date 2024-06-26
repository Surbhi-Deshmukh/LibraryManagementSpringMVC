package com.nagarro.assgn5frontend.controller;

import com.nagarro.assgn5frontend.entity.Author;
import com.nagarro.assgn5frontend.entity.Book;
import com.nagarro.assgn5frontend.service.AuthorService;
import com.nagarro.assgn5frontend.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


class BookControllerTest {
    private MockMvc mockMvc;
    @Mock
    private AuthorService authorService;

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    private Book book1, book2, book3;
    private Author author1, author2, author3;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
        initializeBooks();
        initializeAuthors();
    }

    @Test
    void testShowAddBook() throws Exception {

        when(authorService.getAllAuthors()).thenReturn(List.of(author1, author2));

        mockMvc.perform(MockMvcRequestBuilders.get("/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("addpage"))
                .andExpect(model().attributeExists("allauthors"))
                .andExpect(model().attribute("allauthors", List.of(author1, author2)));
    }

    @Test
    void testHandleEditBook() throws Exception {
        String bookCode = "1";


        when(bookService.getBook(bookCode)).thenReturn(book1);
        when(authorService.getAllAuthors()).thenReturn(List.of(author1, author2, author3));

        mockMvc.perform(MockMvcRequestBuilders.get("/edit")
                        .param("id", bookCode))
                .andExpect(status().isOk())
                .andExpect(view().name("editpage"))
                .andExpect(model().attributeExists("allauthors"))
                .andExpect(model().attribute("allauthors", List.of(author1, author2, author3)))
                .andExpect(model().attributeExists("book"))
                .andExpect(model().attribute("book", book1));
    }

    @Test
    void testAddBook() throws Exception {
        when(authorService.getAuthor(anyInt())).thenReturn(author1);

        mockMvc.perform(MockMvcRequestBuilders.post("/addbook")
                        .param("code", "1")
                        .param("name", "Java Fundamentals")
                        .param("authorId", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/home"));
    }

    @Test
    void testDeleteBook() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/home"));

         verify(bookService).deleteBook("1");
    }

    @Test
    void testEditBook() throws Exception {
        Author author = new Author(1, "Author1", null);
        when(authorService.getAuthor(anyInt())).thenReturn(author);

        mockMvc.perform(MockMvcRequestBuilders.post("/editbook")
                        .param("code", "1")
                        .param("name", "Java Fundamentals")
                        .param("authorId", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/home"));

         verify(bookService).updateBook(any(Book.class));
    }
    private void initializeBooks() {
        book1 = new Book("1", "Java Fundamentals", null, new Date());
        book2 = new Book("2", "Java FullStack", null, new Date());
        book3 = new Book("3", "React Js", null, new Date());
    }
    private void initializeAuthors() {
        author1 = new Author(1, "Robert Scoble", null);
        author2 = new Author(2, "Samuel Glasstone", null);
        author3 = new Author(3, "David Blatner", null);

    }
}