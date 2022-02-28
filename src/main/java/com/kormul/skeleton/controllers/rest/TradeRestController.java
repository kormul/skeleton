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

import com.kormul.skeleton.domain.Trade;
import com.kormul.skeleton.service.TradeService;

@RestController
@RequestMapping("/trade/rest")
public class TradeRestController {

	@Autowired
	TradeService tradeService;
	
	private static final Logger logger = LogManager.getLogger(TradeRestController.class);
	
	@PostMapping("/create")
    public String createTrade(@RequestBody  @Valid Trade trade) {
			logger.debug("Post request");
			tradeService.createTrade(trade);
			return "Trade create successfully";
	    }
	
    @GetMapping("/read")
    public List<Trade> findAllTrades() {
    	logger.debug("GET request");
        return tradeService.findAll();
    }
    
    @PutMapping("/update/{id}")
    public String updateTrade(@PathVariable final Integer id, @RequestBody @Valid Trade trade) {
		logger.debug("PUT request");
		tradeService.updateTrade(id, trade);
        return "Trade updated successfully";
    }
    
    @DeleteMapping("/delete/{id}")
    public String deleteTradeById(@PathVariable final Integer id) {
    	tradeService.deleteById(id);
        return "Trade deleted successfully";
    }
}
