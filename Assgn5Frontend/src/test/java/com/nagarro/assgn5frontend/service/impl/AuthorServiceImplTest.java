package com.nagarro.assgn5frontend.service.impl;

import com.nagarro.assgn5frontend.entity.Author;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthorServiceImplTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private AuthorServiceImpl authorService;

    private Author author1, author2, author3;
    private Author[] authorArray;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        initializeAuthors();
        authorArray = new Author[]{author1, author2, author3};
    }

    @Test
    void testGetAllAuthors() {
        String url = "http://localhost:9191/api/authors";

        when(restTemplate.getForObject(url, Author[].class)).thenReturn(authorArray);
        List<Author> result = authorService.getAllAuthors();
        assertNotNull(result);
        assertEquals(3, result.size());
        assertEquals("Robert Scoble", result.get(0).getName());
        verify(restTemplate, times(1)).getForObject(url, Author[].class);
    }

    @Test
    void testGetAuthor() {
        Integer authorId = 1;
        String url = "http://localhost:9191/api/author/{id}";

        when(restTemplate.getForObject(url, Author.class, authorId)).thenReturn(author1);
        Author result = authorService.getAuthor(authorId);
        assertNotNull(result);
        assertEquals("Robert Scoble", result.getName());
        verify(restTemplate, times(1)).getForObject(url, Author.class, authorId);
    }

    @Test
    void testAddAuthor() {
        String url = "http://localhost:9191/api/author";
        HttpEntity<Author> requestEntity = new HttpEntity<>(author1);

        when(restTemplate.postForObject(url, requestEntity, String.class)).thenReturn("Success");
        authorService.addAuthor(author1);
        verify(restTemplate, times(1)).postForObject(url, requestEntity, String.class);
    }

    @Test
    void testDeleteAuthor() {
        Integer authorId = 1;
        String url = "http://localhost:9191/api/author/" + authorId;

        doNothing().when(restTemplate).delete(url);
        authorService.deleteAuthor(authorId);
        verify(restTemplate, times(1)).delete(url);
    }

    private void initializeAuthors() {
        author1 = new Author(1, "Robert Scoble", null);
        author2 = new Author(2, "Samuel Glasstone", null);
        author3 = new Author(3, "David Blatner", null);

    }
}