package com.nagarro.advjavaassgn5a.service.impl;

import com.nagarro.advjavaassgn5a.entity.Book;
import com.nagarro.advjavaassgn5a.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;
    @InjectMocks
    private BookServiceImpl bookService;
    private Book book1, book2, book3;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        initializeBooks();
    }

    @Test
    void testGetAllBooks() {
        when(bookRepository.findAll()).thenReturn(List.of(book1, book2, book3));
        List<Book> allBooks = bookService.getAllBooks();

        assertEquals(3, allBooks.size());
        assertEquals("1", allBooks.get(0).getBookCode());
    }

    @Test
    void testGetBook() {
        when(bookRepository.findById("1")).thenReturn(Optional.of(book1));
        Book book = bookService.getBook("1");
        assertEquals(book1, book);
    }

    @Test
    void testAddBook() {
        when(bookRepository.save(book1)).thenReturn(book1);
        Book book = bookService.addBook(book1);
        assertEquals(book1, book);
    }

    @Test
    void testUpdateBook() {
        when(bookRepository.findById("1")).thenReturn(Optional.of(book1));
        when(bookRepository.save(any())).thenReturn(new Book(book1.getBookCode(), book2.getBookName(), null, book1.getAddedDate()));
        Book updatedBook = bookService.updateBook(book1.getBookCode(), book2);
        assertEquals(book1.getBookCode(), updatedBook.getBookCode());
        assertEquals(book2.getBookName(), updatedBook.getBookName());
    }

    @Test
    void testDeleteBook() {
        when(bookRepository.existsById(any())).thenReturn(true);
        bookService.deleteBook("1");
        verify(bookRepository, times(1)).deleteById("1");
    }

    private void initializeBooks() {
        book1 = new Book("1", "Java Fundamentals", null, new Date());
        book2 = new Book("2", "Java FullStack", null, new Date());
        book3 = new Book("3", "React Js", null, new Date());
    }
}