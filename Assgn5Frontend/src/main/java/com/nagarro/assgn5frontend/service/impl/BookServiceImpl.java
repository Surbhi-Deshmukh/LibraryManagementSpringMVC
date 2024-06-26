package com.nagarro.assgn5frontend.service.impl;

import java.util.List;

import com.nagarro.assgn5frontend.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.nagarro.assgn5frontend.entity.Book;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private RestTemplate restTemplate;
	@Override
	public Book getBook(String bookCode) {
		System.out.println("Calling Rest template");
		String url = "http://localhost:9191/api/book/{id}";
		Book book = restTemplate.getForObject(url, Book.class, bookCode);
		System.out.println(restTemplate.getForObject(url, Book.class, bookCode));
		return book;
	}
	
	@Override
	public List<Book> getAllBooks(){
		String url = "http://localhost:9191/api/books";
		
		ResponseEntity<List<Book>> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Book>>() {});
		List<Book> books = response.getBody();
		return books;
		
	}
	
	@Override
	public void addBook(Book book) {
		String url = "http://localhost:9191/api/book";
		
		HttpEntity<Book> request = new HttpEntity<>(book);
		String response = restTemplate.postForObject(url, request, String.class);
		System.out.println(response);
	}
	
	@Override
	public void deleteBook(String bookCode) {
		String url = "http://localhost:9191/api/book/" + bookCode;
		restTemplate.delete(url);
	}
	
	@Override
	public void updateBook(Book book) {
		String url = "http://localhost:9191/api/book/" + book.getBookCode();
		restTemplate.put(url, book);
	}
	
}
