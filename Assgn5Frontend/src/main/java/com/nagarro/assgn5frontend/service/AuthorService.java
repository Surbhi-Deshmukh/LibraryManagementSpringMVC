package com.nagarro.assgn5frontend.service;

import java.util.List;

import com.nagarro.assgn5frontend.entity.Author;

public interface AuthorService {
	
	public List<Author> getAllAuthors();
	
	public Author getAuthor(Integer id);
	
	public void addAuthor(Author author);
	
	public void deleteAuthor(Integer id);
	

}
