package com.nagarro.advjavaassgn5a.repository;

import com.nagarro.advjavaassgn5a.entity.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//@ExtendWith(SpringExtension.class)
//@DataJpaTest
@SpringBootTest
@Transactional
public class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    public void testSaveAuthor() {
        Author author = new Author(1, "Author Name", null);
        Author savedAuthor = authorRepository.save(author);
        Optional<Author> foundAuthor = authorRepository.findById(savedAuthor.getId());
        assertTrue(foundAuthor.isPresent());
        assertEquals(author.getName(), foundAuthor.get().getName());
    }

    @Test
    public void testFindById() {
        Author author = new Author(2, "Another Author", null);
        author = authorRepository.save(author);
        Optional<Author> foundAuthor = authorRepository.findById(author.getId());
        assertTrue(foundAuthor.isPresent());
        assertEquals(author.getName(), foundAuthor.get().getName());
    }

    @Test
    public void testDeleteAuthor() {
        Author author = new Author(3, "Author to Delete", null);
        author = authorRepository.save(author);
        Integer authorId = author.getId();
        authorRepository.deleteById(authorId);
        Optional<Author> foundAuthor = authorRepository.findById(authorId);
        assertTrue(foundAuthor.isEmpty());
    }

    @Test
    public void testUpdateAuthor() {
        Author author = new Author(4, "Original Name", null);
        author = authorRepository.save(author);
        Integer authorId = author.getId();
        author.setName("Updated Name");
        authorRepository.save(author);
        Optional<Author> foundAuthor = authorRepository.findById(authorId);
        assertTrue(foundAuthor.isPresent());
        assertEquals("Updated Name", foundAuthor.get().getName());
    }
}
