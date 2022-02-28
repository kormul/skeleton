package com.kormul.skeleton.controllers.web;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	
	private static final Logger logger = LogManager.getLogger(LoginController.class);

    @GetMapping("/login")
    public String login() {
    	logger.debug("get request Login");
        return "login";
    }
    
    @GetMapping("/forbidden")
    public String forbidden() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	return "403";
    }
}
