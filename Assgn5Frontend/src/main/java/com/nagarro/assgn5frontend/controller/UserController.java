package com.nagarro.assgn5frontend.controller;


import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nagarro.assgn5frontend.entity.User;
import com.nagarro.assgn5frontend.service.UserService;


@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("/login")
	public ModelAndView showLoginForm() {
		ModelAndView mv = new ModelAndView("login");
		return mv;
	}
	
	@PostMapping("/authenticate")
	public ModelAndView submitLoginForm(@ModelAttribute User user, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		
		if(userService.authenticateUser(user)) {
			mv.setViewName("redirect:/home");
			session.setAttribute("user", user.getUsername());
		}
		else {
			mv.setViewName("redirect:/login");
		}
		return mv;
	}
	
	@RequestMapping("/logout")
	public ModelAndView logout(HttpSession session) {
		ModelAndView mv = new ModelAndView("redirect:/login");
		session.removeAttribute("user");
		session.invalidate();
		return mv;
	}
	

}
