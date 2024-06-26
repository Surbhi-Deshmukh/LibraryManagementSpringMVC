package com.nagarro.assgn5frontend.entity;

import java.util.Date;


public class Book {
	
	String bookCode;
	String bookName;
	Author author;
	Date addedDate;

	public Book() {
	}

	public Book(String bookCode, String bookName, Author author, Date addedDate) {
		this.bookCode = bookCode;
		this.bookName = bookName;
		this.author = author;
		this.addedDate = addedDate;
	}

	public String getBookCode() {
		return bookCode;
	}
	public void setBookCode(String bookCode) {
		this.bookCode = bookCode;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}
	public Date getAddedDate() {
		return addedDate;
	}
	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}
	
	
	

}
