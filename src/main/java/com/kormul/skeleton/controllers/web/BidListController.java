package com.kormul.skeleton.controllers.web;

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

import com.kormul.skeleton.domain.BidList;
import com.kormul.skeleton.service.BidListService;

import java.sql.Timestamp;
import java.util.NoSuchElementException;

import javax.validation.Valid;

@Controller
@RequestMapping("/bidList")
public class BidListController {
    
	@Autowired
	BidListService bidListService;
	
	private static final Logger logger = LogManager.getLogger(BidListController.class);
    
    @RequestMapping("/list")
    public String home(Model model){
    	
        logger.debug("get request bidlist : List");
        model.addAttribute("list",bidListService.findAll());
    	return "bidList/list";
    }

    @GetMapping("/add")
    public String addBidForm(Model model) {
    	
        logger.debug("get request bidlist : add");
        BidList bidList = new BidList(); 
        model.addAttribute("bidList", bidList);
        return "bidList/add";
    }

    @PostMapping("/validate")
    public String validate(@Valid BidList bid, BindingResult result, Model model) {
        logger.debug("post request bidlist : validate");
        if(!result.hasFieldErrors())  {
        	bid.setBidListDate(new Timestamp(System.currentTimeMillis()));
        	bid.setCreationDate(new Timestamp(System.currentTimeMillis()));
        	bidListService.createBidList(bid);
        	return "redirect:list";
        }
        logger.error("Post Request error : Validate");
        model.addAttribute("bidList", bid);
    	return "bidList/add";
    }

    @GetMapping("/update")
    public String showUpdateForm(@RequestParam("id") Integer id, Model model) {
        logger.debug("get request bidlist : update");

    	try {
    		model.addAttribute("bidList", bidListService.getbyId(id));
    	}catch (NoSuchElementException e) {
    		logger.error("Get request Update error, BidList not fund");
		}
    	return "bidList/update";
    }

    @PostMapping("/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid @ModelAttribute("bidList") BidList bidList, BindingResult result, Model model) {
    	logger.debug("post request bidlist : update");
    	
    	bidListService.updateBidList(id, bidList);
    	
        return "redirect:/bidList/list";
    }

    @GetMapping("/delete")
    public String deleteBid(@RequestParam("id") Integer id, Model model) {
        logger.debug("get request bidlist : delete");
        try {
            bidListService.deleteById(id);
        }catch (NoSuchElementException e) {
            return "redirect:/bidList/list";
		}
        return "redirect:/bidList/list";
    }
}