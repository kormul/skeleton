package com.kormul.skeleton.unitaire.controllers.rest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kormul.skeleton.domain.User;
import com.kormul.skeleton.service.UserService;

@SpringBootTest
public class UserRestTest {
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	private MockMvc mockMvc;
	
	@MockBean
	UserService userService;
	
	private User user1 = new User();
	private User user2 = new User();
	private User user3 = new User();
	private List<User> users = new ArrayList<User>();
	
	@BeforeEach
	public void setUp() {

		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		
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
		
		when(userService.createUser(user1)).thenReturn(user1);
		when(userService.findAllUser()).thenReturn(users);
		when(userService.updateUser(1, user1)).thenReturn(user1);
	}
	
	@Test
	public void UserCreateTest() throws JsonProcessingException, Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/user/rest/create")
                .content(new ObjectMapper().writeValueAsString(user1))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
	}
	
	@Test
	public void UserCreateErrorTest() throws JsonProcessingException, Exception {
		user1.setPassword("");
		mockMvc.perform(MockMvcRequestBuilders.post("/user/rest/create")
                .content(new ObjectMapper().writeValueAsString(user1))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
	}
	
	@Test
	public void UserReadTest()  throws JsonProcessingException, Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/user/rest/read"))
        .andExpect(status().isOk());
	}
	
	@Test
	public void UserUpdateTest()  throws JsonProcessingException, Exception{
        mockMvc.perform(MockMvcRequestBuilders.put("/user/rest/update/1")
                .content(new ObjectMapper().writeValueAsString(user1))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
	}
	
	@Test
	public void UserUpdateErrorTest()  throws JsonProcessingException, Exception{
		user1.setUsername("");
        mockMvc.perform(MockMvcRequestBuilders.put("/user/rest/update/1")
                .content(new ObjectMapper().writeValueAsString(user1))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
	}
	
	
	@Test
	public void UserDeleteTest()  throws JsonProcessingException, Exception{
        mockMvc.perform(MockMvcRequestBuilders.put("/user/rest/update/1")
                .content(new ObjectMapper().writeValueAsString(user1))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
	}

}
