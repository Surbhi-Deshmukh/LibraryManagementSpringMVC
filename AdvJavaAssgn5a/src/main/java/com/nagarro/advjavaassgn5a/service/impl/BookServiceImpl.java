package com.nagarro.advjavaassgn5a.service.impl;

import java.util.List;
import java.util.Optional;

import com.nagarro.advjavaassgn5a.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.advjavaassgn5a.repository.BookRepository;
import com.nagarro.advjavaassgn5a.entity.Book;

@Service
public class BookServiceImpl implements BookService {
	
	@Autowired
	private BookRepository bookRepository;

	@Override
	public List<Book> getAllBooks(){
		return bookRepository.findAll();
	}
	
	@Override
	public Book getBook(String id) {
        return bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book does not exist"));
	}
	
	@Override
	public Book addBook(Book book){
		return bookRepository.save(book);
	}
	
	@Override
	public Book updateBook(String bookCode, Book book) {
		Book existing = bookRepository.findById(bookCode)
				.orElseThrow(() -> new RuntimeException("Book does not exists"));
		existing.setBookName(book.getBookName());
		existing.setAuthor(book.getAuthor());
		return bookRepository.save(existing);
	}
	
	@Override
	public void deleteBook(String id) {
		if(!bookRepository.existsById(id)) throw new RuntimeException("Book does not exists");
		bookRepository.deleteById(id);
	}
	
	
}
