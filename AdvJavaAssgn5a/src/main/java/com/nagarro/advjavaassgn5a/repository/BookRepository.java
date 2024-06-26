package com.nagarro.advjavaassgn5a.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.advjavaassgn5a.entity.Book;

public interface BookRepository extends JpaRepository<Book, String> {

}
