package com.kormul.skeleton.unitaire.service;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kormul.skeleton.domain.User;
import com.kormul.skeleton.repository.UserRepository;
import com.kormul.skeleton.service.UserService;

@SpringBootTest
public class UserTest {
	
	@Autowired
	private UserService userService;
	
	@Mock
	private UserRepository userRepository;
	
	private User user1 = new User();
	private User user2 = new User();
	private User user3 = new User();
	private Optional<User> userOpt = Optional.of(user1);
	private List<User> users = new ArrayList<User>();
	
	@BeforeEach
	public void setUp() {
		
		user1.setId(1);
		user2.setId(2);
		user3.setId(3);
		
		user1.setUsername("Username 1");
		user2.setUsername("Username 2");
		user3.setUsername("Username 3");
		
		user1.setPassword("Password 1");
		user2.setPassword("Password 2");
		user3.setPassword("Password 3");

		user1.setFullname("FullName 1");
		user2.setFullname("FullName 2");
		user3.setFullname("FullName 3");
		
		user1.setRole("Role 1");
		user2.setRole("Role 2");
		user3.setRole("Role 3");

		
		users.add(user1);
		users.add(user2);
		users.add(user3);
		userService.setUserRepository(userRepository); 
		
		when(userRepository.save(any(User.class))).thenReturn(user1);
		when(userRepository.findAll()).thenReturn(users);
		when(userRepository.findById(1)).thenReturn(userOpt);
		when(userRepository.findByUsername("Username 1")).thenReturn(userOpt);
	}
	
	@Test
	public void createUserTest() {
		
		User user = userService.createUser(user1);
		assertTrue(1 == checkUserNoModify(user));

	}
	
	@Test
	public void findAllUserTest() {
		List<User> userList = userService.findAllUser();
		assertTrue(1 == checkUserNoModify(userList.get(0)));
		assertTrue(2 == checkUserNoModify(userList.get(1)));
		assertTrue(3 == checkUserNoModify(userList.get(2)));

	}	
	
	@Test
	public void UpdateUserTest() {
		User user = new User();
		user.setId(4);
		user.setUsername("Username 4");
		user.setPassword("Password 4");
		user.setFullname("FullName 4");
		user.setRole("Role 4");
		
		User userModify = userService.updateUser(1, user);
		
		assertTrue(userModify.getId() == 1);
		assertTrue(userModify.getUsername().equalsIgnoreCase("Username 1"));
		assertTrue(userModify.getPassword().equalsIgnoreCase("Password 1"));
		assertTrue(userModify.getFullname().equalsIgnoreCase("FullName 1"));
		assertTrue(userModify.getRole().equalsIgnoreCase("Role 1"));

	}
	
	public int checkUserNoModify(User user) {
		
		assertTrue(user.getId() != null);
		
		assertTrue(user.getUsername().equalsIgnoreCase("Username "+ user.getId()));
		assertTrue(user.getPassword().equalsIgnoreCase("Password "+ user.getId()));
		assertTrue(user.getFullname().equalsIgnoreCase("FullName "+ user.getId()));
		assertTrue(user.getRole().equalsIgnoreCase("Role "+ user.getId()));

		
		return user.getId();
	}

}
