package com.kormul.skeleton.controllers.web;

import java.sql.Timestamp;
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

import com.kormul.skeleton.domain.Trade;
import com.kormul.skeleton.service.TradeService;

@Controller
public class TradeController {
	
	@Autowired
	TradeService tradeService;
	
	private static final Logger logger = LogManager.getLogger(TradeController.class);

    @RequestMapping("/trade/list")
    public String home(Model model)
    {
    	logger.debug("get request trade : List");
    	model.addAttribute("list",tradeService.findAll());
        return "trade/list";
    }

    @GetMapping("/trade/add")
    public String addUser(Model model) {
    	
    	logger.debug("get request trade : add");
    	Trade trade = new Trade();
        model.addAttribute("trade", trade);
        return "trade/add";
    }

    @PostMapping("/trade/validate")
    public String validate(@Valid Trade trade, BindingResult result, Model model) {
       
    	logger.debug("post request trade : validate");
        if(!result.hasFieldErrors())  {
            trade.setTradeDate(new Timestamp(System.currentTimeMillis()));
            trade.setCreationDate(new Timestamp(System.currentTimeMillis()));
            trade.setRevisionName(null);
            trade.setRevisionDate(null);
        	tradeService.createTrade(trade);
        	return "redirect:list";
        }
        logger.error("Post Request error : Validate");
        model.addAttribute("trade", trade);
        
        return "trade/add";
    }

    @GetMapping("/trade/update")
    public String showUpdateForm(@RequestParam("id") Integer id, Model model) {

    	logger.debug("get request trade : update");

    	try {
    		model.addAttribute("trade", tradeService.getbyId(id));
    	}catch (NoSuchElementException e) {
    		logger.error("Get request Update error, trade not fund");
		}
    	return "trade/update";
    }

    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid @ModelAttribute("trade") Trade trade, BindingResult result, Model model) {

    	logger.debug("post request trade : update");
    	
    	tradeService.updateTrade(id, trade);
    	
    	return "redirect:/trade/list";
    }

    @GetMapping("/trade/delete")
    public String deleteTrade(@RequestParam("id") Integer id, Model model) {
    	
        logger.debug("get request Trade : delete");
        try {
            tradeService.deleteById(id);
        }catch (NoSuchElementException e) {
            return "redirect:/trade/list";
		}
        
        return "redirect:/trade/list";
    }
}
