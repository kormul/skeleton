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

import com.kormul.skeleton.domain.Rating;
import com.kormul.skeleton.service.RatingService;

@RestController
@RequestMapping("/rating/rest")
public class RatingRestController {

	@Autowired
	RatingService ratingService;
	
	private static final Logger logger = LogManager.getLogger(RatingRestController.class);
	
	@PostMapping("/create")
    public String createRating(@RequestBody  @Valid Rating rating) {
			logger.debug("Post request");
			ratingService.createRating(rating);
			return "Rating create successfully";
	    }
	
    @GetMapping("/read")
    public List<Rating> findAllRatings() {
    	logger.debug("GET request");
        return ratingService.findAllRating();
    }
    
    @PutMapping("/update/{id}")
    public String updateRating(@PathVariable final Integer id, @RequestBody @Valid Rating rating) {
		logger.debug("PUT request");
		ratingService.updateRating(id, rating);
        return "Rating updated successfully";
    }
    
    @DeleteMapping("/delete/{id}")
    public String deleteRatingById(@PathVariable final Integer id) {
    	ratingService.deleteRatingById(id);
        return "Rating deleted successfully";
    }
}
