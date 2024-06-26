package com.nagarro.assgn5frontend.service.impl;

import java.util.Arrays;
import java.util.List;

import com.nagarro.assgn5frontend.service.AuthorService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.nagarro.assgn5frontend.entity.Author;

@Service
public class AuthorServiceImpl implements AuthorService {
	
	private RestTemplate restTemplate = new RestTemplate();
	
	@Override
	public List<Author> getAllAuthors(){
		String url = "http://localhost:9191/api/authors";
	
		Author[] authors = (Author[]) restTemplate.getForObject(url, Author[].class);
		return Arrays.asList(authors);
	}
	
	@Override
	public Author getAuthor(Integer id) {
		String url = "http://localhost:9191/api/author/{id}";
		Author author = restTemplate.getForObject(url, Author.class, id);
		return author;
	}
	
	@Override
	public void addAuthor(Author author) {
		String url = "http://localhost:9191/api/author";
		HttpEntity<Author> request = new HttpEntity<Author>(author);
		String response = restTemplate.postForObject(url, request, String.class);
		System.out.print(response);
	}
	
	@Override
	public void deleteAuthor(Integer id) {
		String url = "http://localhost:9191/api/author/" + id;
		restTemplate.delete(url);
	}

}
