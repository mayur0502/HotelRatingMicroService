package com.mayurTech.auth.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mayurTech.auth.service.entity.User;
import com.mayurTech.auth.service.service.UserService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	UserService userService;

	@PostMapping("/login")
	public ResponseEntity<User> getLogin(@RequestBody User user, HttpServletResponse response) {
		User user1 = userService.getLogin(user,response);
		if (user1 != null)
			return ResponseEntity.ok(user1);
		else
			return ResponseEntity.badRequest().build();
	}

	@PostMapping("/adduser")
	public User saveAuthUser(@RequestBody User user) {
//		userService.saveUser(user);
		return userService.saveUser(user);
	}

	@GetMapping("/logout")
	  public ResponseEntity<String> logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("jwt", null);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);             // Must match original cookie
        cookie.setPath("/");                // Must match original cookie path
        cookie.setDomain("yourdomain.com"); // Must match original cookie domain
        cookie.setMaxAge(0);                // Delete cookie immediately

        response.addCookie(cookie);

        return ResponseEntity.ok("Logged out successfully");	}
}
