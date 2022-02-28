package com.kormul.skeleton.controllers.web;

import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kormul.skeleton.domain.Rating;
import com.kormul.skeleton.service.RatingService;

@Controller
public class RatingController {
	
	@Autowired
	RatingService ratingService;
	
	private static final Logger logger = LogManager.getLogger(RatingController.class);


    @RequestMapping("/rating/list")
    public String home(Model model)
    {

        logger.debug("get request Rating : List");
        model.addAttribute("list",ratingService.findAllRating());
        return "rating/list";
    }

    @GetMapping("/rating/add")
    public String addRatingForm(Model model) {
    	
    	logger.debug("get request Rating : add");
        Rating rating = new Rating();
        model.addAttribute("rating", rating);
        
        return "rating/add";
    }

    @PostMapping("/rating/validate")
    public String validate(@Valid Rating rating, BindingResult result, Model model) {
    	
    	logger.debug("post request CurvePorint : validate");
        if(!result.hasFieldErrors())  {
        	ratingService.createRating(rating);
        	return "redirect:list";
        }
        logger.error("Post Request error : Validate");
        model.addAttribute("rating", rating);
        
        return "rating/add";
    }

    @GetMapping("/rating/update")
    public String showUpdateForm(@RequestParam("id") Integer id, Model model) {

        logger.debug("get request rating : update");

    	try {
    		model.addAttribute("rating", ratingService.getbyId(id));
    	}catch (NoSuchElementException e) {
    		logger.error("Get request Update error, rating not fund");
		}
    	
    	return "rating/update";
    }

    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid @ModelAttribute("rating") Rating rating, BindingResult result, Model model) {

    	logger.debug("post request rating : update");
    	
    	ratingService.updateRating(id, rating);
    	
    	return "redirect:/rating/list";
    }

    @GetMapping("/rating/delete")
    public String deleteRating(@RequestParam("id") Integer id, Model model) {
    	logger.debug("get request rating : delete");
        try {
            ratingService.deleteRatingById(id);
        }catch (NoSuchElementException e) {
            return "redirect:/rating/list";
		}
        return "redirect:/rating/list";
    }
}