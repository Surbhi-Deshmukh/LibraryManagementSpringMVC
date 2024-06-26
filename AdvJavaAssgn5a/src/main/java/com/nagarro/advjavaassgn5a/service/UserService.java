package com.nagarro.advjavaassgn5a.service;

import com.nagarro.advjavaassgn5a.entity.Users;

public interface UserService {
	
	public Users authenticateUser(String userId, String password);
	
	public Users addUser(Users user);
	
	public void deleteUser(String username);
	
}
