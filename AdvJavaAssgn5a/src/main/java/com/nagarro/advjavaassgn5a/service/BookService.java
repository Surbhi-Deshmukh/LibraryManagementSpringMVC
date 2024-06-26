package com.nagarro.advjavaassgn5a.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nagarro.advjavaassgn5a.entity.Book;

public interface BookService {
	
	public List<Book> getAllBooks();
	
	public Book getBook(String id);
	
	public Book addBook(Book book);
	
	public Book updateBook(String bookCode, Book book);
	
	public void deleteBook(String id);
	

}
