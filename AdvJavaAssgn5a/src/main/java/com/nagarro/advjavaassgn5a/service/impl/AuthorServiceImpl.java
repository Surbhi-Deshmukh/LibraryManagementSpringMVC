package com.nagarro.advjavaassgn5a.service.impl;

import java.util.List;
import java.util.Optional;

import com.nagarro.advjavaassgn5a.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.advjavaassgn5a.repository.AuthorRepository;
import com.nagarro.advjavaassgn5a.entity.Author;

@Service
public class AuthorServiceImpl implements AuthorService {
	
	@Autowired
	private AuthorRepository authorRepository;
	
	@Override
	public List<Author> getAllAuthors(){
		return authorRepository.findAll();
	}
	
	@Override
	public Author getAuthor(Integer id) {
		Optional<Author> authorOptional = authorRepository.findById(id);
		return authorOptional.orElse(null);
	}
	
	@Override
	public Author addAuthor(Author author) {
		return authorRepository.save(author);
	}
	
	@Override
	public Author updateAuthor(Integer id, Author author) {
		Author existing = authorRepository.findById(id).orElseThrow(() -> new RuntimeException("Author does not exist"));
		existing.setBooks(author.getBooks());
		existing.setName(author.getName());
		return authorRepository.save(existing);
	}
	
	@Override
	public void deleteAuthor(Integer id) {
		if(!authorRepository.existsById(id)) throw new RuntimeException("Author does not exist");
		authorRepository.deleteById(id);
	}
}
