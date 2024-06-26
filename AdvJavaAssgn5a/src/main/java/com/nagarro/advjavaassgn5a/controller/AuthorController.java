package com.nagarro.advjavaassgn5a.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.advjavaassgn5a.entity.Author;
import com.nagarro.advjavaassgn5a.service.AuthorService;

@RestController
@RequestMapping("/api")
public class AuthorController {
	
	@Autowired
	private AuthorService authorService;
	
//	@GetMapping("/authors")
//	public ResponseEntity<Object[]> getAuthors(){
//		List<Author> authors = authorService.getAllAuthors();
//		return ResponseEntity.ok(authors.toArray());
//	}
//	
	@GetMapping("/authors")
	public ResponseEntity<List<Author>> getAuthors(){
		List<Author> authors = authorService.getAllAuthors();
		return ResponseEntity.ok(authors);
	}
	
	@GetMapping("/author/{id}")
	public ResponseEntity<Author> getSingleAuthor(@PathVariable Integer id) {
		Author author = authorService.getAuthor(id);
		return ResponseEntity.ok(author);
	}
	
	@PostMapping("/author")
	public ResponseEntity<Author> addAuthor(@RequestBody Author author){
		return ResponseEntity.ok(authorService.addAuthor(author));
	}
	
	@DeleteMapping("/authors/{id}")
	public ResponseEntity<String> deleteAuthor(@PathVariable Integer id) {
		try{
			authorService.deleteAuthor(id);
			return ResponseEntity.ok("Author's Record deleted successfully");
		}
		catch (RuntimeException ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
		}

	}
	
}
