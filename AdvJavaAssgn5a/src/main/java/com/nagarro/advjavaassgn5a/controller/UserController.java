package com.nagarro.advjavaassgn5a.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nagarro.advjavaassgn5a.entity.Users;
import com.nagarro.advjavaassgn5a.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/user")
	public ResponseEntity<Users> loginUser(@RequestBody Users user) {
		Users matchedUser = userService.authenticateUser(user.getUsername(), user.getPassword());
		return ResponseEntity.ok(matchedUser);
	}
	
	@PostMapping("/user/add")
	public ResponseEntity<Users> registerUser(@RequestBody Users user) {
		return ResponseEntity.ok(userService.addUser(user));
	}
	
	@DeleteMapping("/user/{username}")
	public ResponseEntity<String> removeUser(@PathVariable String username) {
		try{
			userService.deleteUser(username);
			return ResponseEntity.ok("User deleted successfully");
		}
		catch (RuntimeException ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
		}

	}

}
