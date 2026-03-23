package com.mayurTech.auth.service.service;

import com.mayurTech.auth.service.entity.User;

import jakarta.servlet.http.HttpServletResponse;

public interface UserService {

	public User saveUser(User user);
	
	public User getUser(String userId);

	public User getLogin(User user, HttpServletResponse response);
	
	
}
