package com.mayurTech.auth.service.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mayurTech.auth.service.entity.User;
import com.mayurTech.auth.service.repository.UserRepository;
import com.mayurTech.auth.service.util.JwtUtil;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class UserService implements com.mayurTech.auth.service.service.UserService{

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	JwtUtil jwtUtil;
	
	@Override
	public User saveUser(User user) {
		// TODO Auto-generated method stub
		
//		8897704332 trysient Gobal
//		7986209670 
		
		
		userRepository.save(user);
		return user;
	}

	@Override
	public User getUser(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getLogin(User user, HttpServletResponse response) {
		// TODO Auto-generated method stub
		User byUsrName = userRepository.findByUsrName(user.getUsrName());
		
		if(byUsrName != null && byUsrName.getPassword().equals(user.getPassword())) {
			
			String token = jwtUtil.generateToken(user.getUsrName());
			
			Cookie cookie  = new Cookie("jwt", token);
	        cookie.setHttpOnly(true);     // Prevent JS access
	        cookie.setSecure(true);       // Only HTTPS
	        cookie.setPath("/");          // Accessible everywhere
	        cookie.setMaxAge(60 * 60);    // 1 hour	
			
			response.addCookie(cookie);
			
			return byUsrName;
		}
		
		return null;
	}

}
