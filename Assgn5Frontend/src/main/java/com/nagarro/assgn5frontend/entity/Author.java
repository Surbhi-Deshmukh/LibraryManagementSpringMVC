package com.nagarro.assgn5frontend.entity;

import java.util.ArrayList;
import java.util.List;


public class Author {
	
	Integer id;
	String name;
	List<Book> books = new ArrayList<>();

	public Author() {
	}

	public Author(Integer id, String name, List<Book> books) {
		this.id = id;
		this.name = name;
		this.books = books;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}