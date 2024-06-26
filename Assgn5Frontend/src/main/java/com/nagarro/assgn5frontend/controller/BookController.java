package com.nagarro.assgn5frontend.controller;

import java.util.List;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nagarro.assgn5frontend.entity.Author;
import com.nagarro.assgn5frontend.entity.Book;
import com.nagarro.assgn5frontend.service.AuthorService;
import com.nagarro.assgn5frontend.service.BookService;


@Controller
public class BookController {
	
	@Autowired
	private AuthorService authorService;
	
	@Autowired
	private BookService bookService;
	
	
	@RequestMapping("/add")
	public ModelAndView showAddBook() {
		ModelAndView mv = new ModelAndView("addpage");
		List<Author> authors = authorService.getAllAuthors();
		mv.addObject("allauthors", authors);
		return mv;
	}
	
	
	@RequestMapping("/edit")
	public ModelAndView handleEditBook(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("editpage");
		String bookCode = request.getParameter("id");
		Book book = bookService.getBook(bookCode);
		List<Author> authors = authorService.getAllAuthors();
		mv.addObject("allauthors", authors);
		mv.addObject("book", book);
		return mv;
	}
	
	
	@PostMapping("/addbook")
	public ModelAndView addBook(@RequestParam("code") String code, @RequestParam("name") String name, @RequestParam("authorId") Integer authorId) {
		ModelAndView mv = new ModelAndView("redirect:/home");
		
		Book book = new Book();
		book.setBookCode(code);
		book.setBookName(name);
		Author author = authorService.getAuthor(authorId);
		book.setAuthor(author);
		bookService.addBook(book);
	
		return mv;
	}
	
	@RequestMapping("/delete/{id}")
	public ModelAndView deleteBook(@PathVariable("id") String id) {
		ModelAndView mv = new ModelAndView("redirect:/home");
		bookService.deleteBook(id);
		return mv;
	}
	
	@PostMapping("/editbook")
	public ModelAndView editBook(@RequestParam("code") String code, @RequestParam("name") String name, @RequestParam("authorId") Integer authorId) {
		ModelAndView mv = new ModelAndView("redirect:/home");
		Book updatedBook = new Book();
		updatedBook.setBookCode(code);
		updatedBook.setBookName(name);
		Author author = authorService.getAuthor(authorId);
		updatedBook.setAuthor(author);
		bookService.updateBook(updatedBook);
		return mv;
	}

}
