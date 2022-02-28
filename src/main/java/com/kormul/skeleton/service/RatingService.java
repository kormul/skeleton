package com.kormul.skeleton.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kormul.skeleton.domain.Rating;
import com.kormul.skeleton.repository.RatingRepository;

import lombok.Data;

@Data
@Service
public class RatingService {


	private static final Logger logger = LogManager.getLogger();
	
	@Autowired
	private RatingRepository ratingRepository;

	public Rating createRating(@Valid Rating rating) {
		return ratingRepository.save(rating);
	}

	public List<Rating> findAllRating() {
        List<Rating> allRatings = ratingRepository.findAll();
        return allRatings;
	}

	public Rating updateRating(Integer id, Rating rating) {
		Optional<Rating> ratingModify = ratingRepository.findById(id);
		if(!ratingModify.isPresent()) {
			logger.error("Failed to update. Rating not Found");
			throw new NoSuchElementException("Failed to update Rating");
		}
		else {
			rating.setId(ratingModify.get().getId());
			logger.info("Rating has updated");
			return ratingRepository.save(rating);
		}
	}

	public void deleteRatingById(Integer id) {
		Optional<Rating> ratingDelete = ratingRepository.findById(id);
		if (!ratingDelete.isPresent()) {
			logger.error("Failed to delete. Rating not Found");
			throw new NoSuchElementException("Failed to delete Rating");
        } else {
        	logger.info("Rating has deleted");
        	ratingRepository.deleteById(id);
        }
		
	}

	public Rating getbyId(Integer id) {
		
		Optional<Rating> rating = ratingRepository.findById(id);
		if (!rating.isPresent()) {
			logger.error("Failed to find. rating not Found");
			throw new NoSuchElementException("Failed to find rating");
        } else {
        	logger.debug("rating has find");
        	return rating.get();
        }
	}
	
}
