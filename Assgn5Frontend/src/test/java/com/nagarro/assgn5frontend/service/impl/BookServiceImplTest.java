package com.nagarro.assgn5frontend.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nagarro.assgn5frontend.entity.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


class BookServiceImplTest {

    @Mock
    private RestTemplate restTemplate;
    @InjectMocks
    private BookServiceImpl bookService;
    private MockRestServiceServer mockServer;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private Book book1, book2, book3;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockServer = MockRestServiceServer.createServer(restTemplate);
        initializeBooks();
    }

    @Test
    void testGetBook() {
        String bookCode = "1";
        String url = "http://localhost:9191/api/book/{id}";

        when(restTemplate.getForObject(url, Book.class, bookCode)).thenReturn(book1);
        Book result = bookService.getBook(bookCode);
        assertNotNull(result);
        assertEquals("Java Fundamentals", result.getBookName());
    }

    @Test
    void testGetAllBooks() {
        String url = "http://localhost:9191/api/books";

        ResponseEntity<List<Book>> responseEntity = new ResponseEntity<>(List.of(book1, book2, book3), HttpStatus.OK);
        when(restTemplate.exchange(eq(url), eq(HttpMethod.GET), isNull(), any(ParameterizedTypeReference.class)))
                .thenReturn(responseEntity);

        List<Book> result = bookService.getAllBooks();

        assertNotNull(result);
        assertEquals(3, result.size());
        assertEquals("Java Fundamentals", result.get(0).getBookName());
        verify(restTemplate, times(1)).exchange(eq(url), eq(HttpMethod.GET), isNull(), any(ParameterizedTypeReference.class));
    }

    @Test
    void testAddBook() {
        String url = "http://localhost:9191/api/book";
        HttpEntity<Book> requestEntity = new HttpEntity<>(book1);

        when(restTemplate.postForObject(url, requestEntity, String.class)).thenReturn("Success");
        bookService.addBook(book1);
        verify(restTemplate, times(1)).postForObject(url, requestEntity, String.class);
    }

    @Test
    void testDeleteBook() {
        String bookCode = "1";
        String url = "http://localhost:9191/api/book/" + bookCode;

        doNothing().when(restTemplate).delete(url);
        bookService.deleteBook(bookCode);
        verify(restTemplate, times(1)).delete(url);
    }

    @Test
    void testUpdateBook() {
        String url = "http://localhost:9191/api/book/" + book1.getBookCode();

        doNothing().when(restTemplate).put(url,  book1);
        bookService.updateBook(book1);
        verify(restTemplate, times(1)).put(url, book1);
    }

    private void initializeBooks() {
        book1 = new Book("1", "Java Fundamentals", null, new Date());
        book2 = new Book("2", "Java FullStack", null, new Date());
        book3 = new Book("3", "React Js", null, new Date());
    }
}