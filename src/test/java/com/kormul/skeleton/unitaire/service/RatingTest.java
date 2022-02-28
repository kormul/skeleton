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

import com.kormul.skeleton.domain.Rating;
import com.kormul.skeleton.repository.RatingRepository;
import com.kormul.skeleton.service.RatingService;

@SpringBootTest
public class RatingTest {
	
	@Autowired
	private RatingService ratingService;
	
	@Mock
	private RatingRepository ratingRepository;
	
	private Rating rating1 = new Rating();
	private Rating rating2 = new Rating();
	private Rating rating3 = new Rating();
	private Optional<Rating> ratingOpt = Optional.of(rating1);
	private List<Rating> ratings = new ArrayList<Rating>();
	
	@BeforeEach
	public void setUp() {
		
		rating1.setId(1);
		rating2.setId(2);
		rating3.setId(3);
		
		ratings.add(rating1);
		ratings.add(rating2);
		ratings.add(rating3);
		ratingService.setRatingRepository(ratingRepository); 
		
		when(ratingRepository.save(any(Rating.class))).thenReturn(rating1);
		when(ratingRepository.findAll()).thenReturn(ratings);
		when(ratingRepository.findById(1)).thenReturn(ratingOpt);
	}
	
	@Test
	public void createRatingTest() {
		
		Rating rating = ratingService.createRating(rating1);
		assertTrue(1 == checkRatingNoModify(rating));

	}
	
	@Test
	public void findAllRatingTest() {
		List<Rating> ratingList = ratingService.findAllRating();
		assertTrue(1 == checkRatingNoModify(ratingList.get(0)));
		assertTrue(2 == checkRatingNoModify(ratingList.get(1)));
		assertTrue(3 == checkRatingNoModify(ratingList.get(2)));

	}	
	
	@Test
	public void UpdateRatingTest() {
		Rating rating = new Rating();
		rating.setId(4);
		
		Rating ratingModify = ratingService.updateRating(1, rating);
		assertTrue(ratingModify.getId() == 1);
	}
	
	@Test
	public void getRatingTest() {
		Rating rating = ratingService.getbyId(1);
		assertTrue(1 == checkRatingNoModify(rating));

	}
	
	public int checkRatingNoModify(Rating rating) {
		
		assertTrue(rating.getId() != null);
		
		return rating.getId();
	}

}
