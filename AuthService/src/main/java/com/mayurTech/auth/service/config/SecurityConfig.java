package com.mayurTech.auth.service.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	JwtFilter jwtFilter;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeHttpRequests(
				auth -> auth.requestMatchers(HttpMethod.POST ,"/auth/login").permitAll()
				.requestMatchers(HttpMethod.POST,"/auth/adduser").permitAll().anyRequest().authenticated())
		
		.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

//		7470708378
		
		return http.build();
	}
}