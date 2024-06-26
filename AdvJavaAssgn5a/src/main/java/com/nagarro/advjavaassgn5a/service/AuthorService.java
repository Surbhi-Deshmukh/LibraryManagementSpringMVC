package com.nagarro.advjavaassgn5a.service;

import java.util.List;

import com.nagarro.advjavaassgn5a.entity.Author;


public interface AuthorService {
	
	public List<Author> getAllAuthors();
	
	public Author getAuthor(Integer id);
	
	public Author addAuthor(Author author);
	
	public Author updateAuthor(Integer id, Author author);
	
	public void deleteAuthor(Integer id);

}
