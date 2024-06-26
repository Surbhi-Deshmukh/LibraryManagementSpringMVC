package com.nagarro.assgn5frontend.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.nagarro.assgn5frontend.entity.Book;


public interface BookService {
	
	public Book getBook(String bookCode);
	
	public List<Book> getAllBooks();
	
	public void addBook(Book book);
	
	public void deleteBook(String bookCode);
	
	public void updateBook(Book book);

}
