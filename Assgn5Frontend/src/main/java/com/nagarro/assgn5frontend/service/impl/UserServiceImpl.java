package com.nagarro.assgn5frontend.service.impl;

import com.nagarro.assgn5frontend.service.UserService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.nagarro.assgn5frontend.entity.User;

@Service
public class UserServiceImpl implements UserService {
	
	private RestTemplate restTemplate = new RestTemplate();
	
	@Override
	public boolean authenticateUser(User user) {
		String url = "http://localhost:9191/api/user";
		
		HttpEntity<User> request = new HttpEntity<>(user);
		ResponseEntity<User> response = restTemplate.exchange(url, HttpMethod.POST, request, User.class);
		User matchedUser = response.getBody();
		if(matchedUser == null)	return false;
		return true;
	}
	
	@Override
	public User addUser(User user) {
		String url = "http://localhost:9191/api/user/add";
		User response = restTemplate.postForObject(url, user, User.class);
		return response;
	}
	
	@Override
	public User updateUser(User user) {
		String url = "http://localhost:9191/api/user/add";
		User response = restTemplate.postForObject(url, user, User.class);
		return response;
	}
}
