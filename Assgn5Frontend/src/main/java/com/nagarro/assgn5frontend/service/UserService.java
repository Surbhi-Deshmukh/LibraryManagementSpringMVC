package com.nagarro.assgn5frontend.service;

import com.nagarro.assgn5frontend.entity.User;

public interface UserService {

	public boolean authenticateUser(User user);
	
	public User addUser(User user);
	
	public User updateUser(User user);
	
}
