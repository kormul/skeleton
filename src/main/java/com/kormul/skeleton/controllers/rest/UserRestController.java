package com.kormul.skeleton.controllers.rest;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kormul.skeleton.domain.User;
import com.kormul.skeleton.service.UserService;


@RestController
@RequestMapping("/user/rest")
public class UserRestController {

	
	@Autowired
	UserService userService;
	
	private static final Logger logger = LogManager.getLogger(UserRestController.class);
	
	@PostMapping("/create")
    public String createUser(@RequestBody  @Valid User user) {
			logger.debug("Post request");
			userService.createUser(user);
			return "User create successfully";
	    }
	
    @GetMapping("/read")
    public List<User> findAllUsers() {
    	logger.debug("GET request");
        return userService.findAllUser();
    }
    
    @PutMapping("/update/{id}")
    public String updateUser(@PathVariable final Integer id, @RequestBody @Valid User user) {
		logger.debug("PUT request");
		userService.updateUser(id, user);
        return "User updated successfully";
    }
    
    @DeleteMapping("/delete/{id}")
    public String deleteUserById(@PathVariable final Integer id) {
    	userService.deleteUserById(id);
        return "User deleted successfully";
    }
    
}
