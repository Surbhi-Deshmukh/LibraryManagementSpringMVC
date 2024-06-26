package com.nagarro.advjavaassgn5a.service.impl;

import com.nagarro.advjavaassgn5a.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.advjavaassgn5a.repository.UserRepository;
import com.nagarro.advjavaassgn5a.entity.Users;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Users authenticateUser(String username, String password) {
		Users user = userRepository.findById(username).orElseThrow(() -> new RuntimeException("User does not exist"));
		if(user.getPassword().equals(password))
			return  user;
		else throw new RuntimeException("Invalid username or password");
	}
	
	@Override
	public Users addUser(Users user) {
		return userRepository.save(user);
	}
	
	@Override
	public void deleteUser(String username) {
		if(!userRepository.existsById(username)) throw new RuntimeException("User not found");

		userRepository.deleteById(username);
	}
	
}
