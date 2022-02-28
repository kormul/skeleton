package com.kormul.skeleton.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kormul.skeleton.domain.User;
import com.kormul.skeleton.repository.UserRepository;

@Service
public class UserService {
	
	private static final Logger logger = LogManager.getLogger();
	
	@Autowired
	private UserRepository userRepository;

	public User createUser(User user) {
		
		Optional<User> doubleUser = userRepository.findByUsername(user.getUsername());
		
		if(!doubleUser.isPresent()) {
			logger.error("Failed to create a new User, anothoer User matches the one that");
			throw new NoSuchElementException("Failed to create the new User");
		}
		else {
			logger.info("Creation of the User");
			return userRepository.save(user);
		}
	}

	public List<User> findAllUser() {
        List<User> allUsers = userRepository.findAll();
        return allUsers;
	}

	public User updateUser(Integer id, User user) {
		Optional<User> userModify = userRepository.findById(id);
		if(userModify.isPresent()) {
			logger.error("Failed to update. User not Found");
			throw new NoSuchElementException("Failed to update User");
		}
		else {
			user.setId(userModify.get().getId());
			logger.info("User has updated");
			return userRepository.save(user);
		}
	}

	public void deleteUserById(Integer id) {
		Optional<User> userDelete = userRepository.findById(id);
		if (!userDelete.isPresent()){
			logger.error("Failed to delete. User not Found");
			throw new NoSuchElementException("Failed to delete User");
        } else {
        	logger.info("User has deleted");
        	userRepository.deleteById(id);
        }
		
	}
}