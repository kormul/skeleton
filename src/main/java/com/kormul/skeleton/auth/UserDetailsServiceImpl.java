package com.kormul.skeleton.auth;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kormul.skeleton.domain.User;
import com.kormul.skeleton.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UserRepository usersRepository;
	
	private static final Logger logger = LogManager.getLogger(UserDetailsServiceImpl.class);
	
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		logger.debug("Load User : " + username);
		Optional<User> user = usersRepository.findByUsername(username);
		if(!user.isPresent()) {
			logger.error("Load User error : User "+ username +" not found " );
			throw new UsernameNotFoundException(username + "Not found!");
		}
		else {
			return new UserDetailsImpl(user.get());
		}
		
	}
}
