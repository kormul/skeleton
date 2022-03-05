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
import com.kormul.skeleton.domain.Rating;
import com.kormul.skeleton.service.RatingService;

@SpringBootTest
public class RatingRestControllerTest {

	@Autowired
	private WebApplicationContext webApplicationContext;
	
	private MockMvc mockMvc;
	
	@MockBean
	private RatingService ratingService;
	
	private Rating rating1 = new Rating();
	private Rating rating2 = new Rating();
	private Rating rating3 = new Rating();
	private List<Rating> ratings = new ArrayList<Rating>();

	@BeforeEach
	public void setUp() {
		
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

		rating1.setId(1);
		rating2.setId(2);
		rating3.setId(3);
		
		ratings.add(rating1);
		ratings.add(rating2);
		ratings.add(rating3);
		
		when(ratingService.createRating(rating1)).thenReturn(rating1);
		when(ratingService.findAllRating()).thenReturn(ratings);
		when(ratingService.updateRating(1, rating1)).thenReturn(rating1);

	}
	
	@Test
	public void RatingCreateTest() throws JsonProcessingException, Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/rating/rest/create")
                .content(new ObjectMapper().writeValueAsString(rating1))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
	}
	
	@Test
	public void RatingReadTest()  throws JsonProcessingException, Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/rating/rest/read"))
        .andExpect(status().isOk());
	}
	
	@Test
	public void RatingUpdateTest()  throws JsonProcessingException, Exception{
        mockMvc.perform(MockMvcRequestBuilders.put("/rating/rest/update/1")
                .content(new ObjectMapper().writeValueAsString(rating1))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
	}
	
	
	@Test
	public void RatingDeleteTest()  throws JsonProcessingException, Exception{
        mockMvc.perform(MockMvcRequestBuilders.put("/rating/rest/update/1")
                .content(new ObjectMapper().writeValueAsString(rating1))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
	}

}
