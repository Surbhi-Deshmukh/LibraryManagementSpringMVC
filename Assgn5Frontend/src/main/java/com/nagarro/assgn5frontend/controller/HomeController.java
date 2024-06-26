package com.nagarro.assgn5frontend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nagarro.assgn5frontend.entity.Book;
import com.nagarro.assgn5frontend.service.BookService;

@Controller
public class HomeController {
	
	@Autowired
	private BookService bookService; 
	
	@RequestMapping(value = {"/", "/home"})
	public ModelAndView showHome() {
		List<Book> books = bookService.getAllBooks();
		ModelAndView mv = new ModelAndView("home");
		mv.addObject("allbooks", books);
		return mv;
	}

}
