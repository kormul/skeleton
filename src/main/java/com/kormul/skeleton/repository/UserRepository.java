package com.kormul.skeleton.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kormul.skeleton.domain.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	User getByUsername(String username);

	Optional<User> findByUsername(String username);

}
