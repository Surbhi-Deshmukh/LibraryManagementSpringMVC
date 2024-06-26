package com.nagarro.advjavaassgn5a.service.impl;

import com.nagarro.advjavaassgn5a.entity.Author;
import com.nagarro.advjavaassgn5a.repository.AuthorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AuthorServiceImplTest {

    @Mock
    private AuthorRepository authorRepository;
    @InjectMocks
    private AuthorServiceImpl authorService;
    private Author author1, author2, author3;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        initializeAuthors();
    }

    @Test
    void testGetAllAuthors() {
        when(authorRepository.findAll()).thenReturn(List.of(author1, author2, author3));
        List<Author> allAuthors = authorService.getAllAuthors();
        assertEquals(3, allAuthors.size());
        assertEquals(author1, allAuthors.get(0));
        assertEquals(author2, allAuthors.get(1));

    }

    @Test
    void testGetAuthor() {
        when(authorRepository.findById(author1.getId())).thenReturn(Optional.of(author1));
        Author author = authorService.getAuthor(author1.getId());
        assertEquals(author1, author);
    }

    @Test
    void testAddAuthor() {
        when(authorRepository.save(author1)).thenReturn(author1);
        Author author = authorService.addAuthor(author1);
        assertEquals(author1, author);
    }

    @Test
    void testUpdateAuthor() {
        when(authorRepository.findById(2)).thenReturn(Optional.of(author2));
        when(authorRepository.save(any())).thenReturn(new Author(author2.getId(), author3.getName(), null));
        Author updatedAuthor = authorService.updateAuthor(2, author3);
        assertEquals(author2.getId(), updatedAuthor.getId());
        assertEquals(author3.getName(), updatedAuthor.getName());
    }

    @Test
    void testDeleteAuthor() {
        when(authorRepository.existsById(any())).thenReturn(true);
        authorService.deleteAuthor(1);
        verify(authorRepository, times(1)).deleteById(1);
    }

    private void initializeAuthors() {
        author1 = new Author(1, "Robert Scoble", null);
        author2 = new Author(2, "Samuel Glasstone", null);
        author3 = new Author(3, "David Blatner", null);

    }
}