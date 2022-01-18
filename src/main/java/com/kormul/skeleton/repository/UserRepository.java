package com.kormul.skeleton.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kormul.skeleton.domain.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	User getByUsername(String username); 

}
