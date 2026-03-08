package com.mayurTech.user.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mayurTech.user.service.entites.User;


public interface UserRepository extends JpaRepository<User, String>{

}
