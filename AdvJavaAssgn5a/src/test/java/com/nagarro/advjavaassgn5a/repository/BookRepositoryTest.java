package com.nagarro.advjavaassgn5a.repository;

import com.nagarro.advjavaassgn5a.entity.Author;
import com.nagarro.advjavaassgn5a.entity.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//@ExtendWith(SpringExtension.class)
//@DataJpaTest
@SpringBootTest
@Transactional
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void testSaveBook() {
        Author author = new Author();
        author.setId(1);
        author.setName("Author Name");
        Book book = new Book("123", "Effective Java", author, new Date());

        Book savedBook = bookRepository.save(book);

        Optional<Book> foundBook = bookRepository.findById(savedBook.getBookCode());
        assertTrue(foundBook.isPresent());
        assertEquals(book.getBookName(), foundBook.get().getBookName());
    }

    @Test
    public void testFindById() {
        Author author = new Author();
        author.setId(2);
        author.setName("Author Name 2");
        Book book = new Book("456", "Clean Code", author, new Date());
        bookRepository.save(book);

        Optional<Book> foundBook = bookRepository.findById("456");

        assertTrue(foundBook.isPresent());
        assertEquals(book.getBookName(), foundBook.get().getBookName());
    }
    @Test
    public void testDeleteBook() {
        Author author = new Author();
        author.setId(3);
        author.setName("Author Name 3");
        Book book = new Book("789", "Design Patterns", author, new Date());
        bookRepository.save(book);

        bookRepository.deleteById("789");

        Optional<Book> foundBook = bookRepository.findById("789");
        assertTrue(foundBook.isEmpty());
    }

    @Test
    public void testUpdateBook() {
        Author author = new Author();
        author.setId(4);
        author.setName("Author Name 4");
        Book book = new Book("1011", "Refactoring", author, new Date());
        bookRepository.save(book);

        book.setBookName("Refactoring Updated");
        bookRepository.save(book);

        Optional<Book> foundBook = bookRepository.findById("1011");
        assertTrue(foundBook.isPresent());
        assertEquals("Refactoring Updated", foundBook.get().getBookName());
    }
}