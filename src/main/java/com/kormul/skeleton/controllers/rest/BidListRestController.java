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

import com.kormul.skeleton.domain.BidList;
import com.kormul.skeleton.service.BidListService;

@RestController
@RequestMapping("/bidList/rest")
public class BidListRestController {
	
	@Autowired
	BidListService bidListService;
	
	private static final Logger logger = LogManager.getLogger(BidListRestController.class);
	
	@PostMapping("/create")
    public String createBidList(@RequestBody  @Valid BidList bidList) {
			logger.debug("Post request");
	        bidListService.createBidList(bidList);
			return "BidList create successfully";
	    }
	
    @GetMapping("/read")
    public List<BidList> findAllBidLists() {
    	logger.debug("GET request");
        return bidListService.findAll();
    }
    
    @PutMapping("/update/{id}")
    public String updateBidList(@PathVariable final Integer id, @RequestBody @Valid BidList bidList) {
		logger.debug("PUT request");
		bidListService.updateBidList(id, bidList);
        return "BidList updated successfully";
    }
    
    @DeleteMapping("/delete/{id}")
    public String deleteBidListById(@PathVariable final Integer id) {
    	bidListService.deleteById(id);
        return "BidList deleted successfully";
    }

}
