package com.nagarro.advjavaassgn5a.controller;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.advjavaassgn5a.entity.Author;
import com.nagarro.advjavaassgn5a.entity.Book;
import com.nagarro.advjavaassgn5a.service.BookService;

@RestController
@RequestMapping("/api")
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@RequestMapping("/books")
	public ResponseEntity<List<Book>> allBooks(){
		List<Book> books = bookService.getAllBooks();
		return ResponseEntity.ok(books);
	}
	
	@PostMapping("/book")
	public ResponseEntity<Book> addNewBook(@RequestBody Book book) {
		book.setAddedDate(new Date());
		Book createdBook = bookService.addBook(book);
		return ResponseEntity.ok(createdBook);
		
	}
	
	@GetMapping("/book/{id}")
	public ResponseEntity<Book> getBookDetails(@PathVariable String id) {
		
		Book book = bookService.getBook(id);
		return ResponseEntity.ok(book);
	}
	
	@DeleteMapping("/book/{id}")
	public ResponseEntity<String> deleteBook(@PathVariable String id) {
		try{
			bookService.deleteBook(id);
			return ResponseEntity.ok("Book Record Deleted Successfully");
		}
		catch (RuntimeException ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/book/{bookCode}")
	public ResponseEntity<Book> updateBook(@PathVariable String bookCode, @RequestBody Book book) {

		return ResponseEntity.ok(bookService.updateBook(bookCode, book));
	}

}
