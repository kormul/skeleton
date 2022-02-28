package com.kormul.skeleton.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kormul.skeleton.domain.Trade;
import com.kormul.skeleton.repository.TradeRepository;

@Service
public class TradeService{

	private static final Logger logger = LogManager.getLogger();
	
	
	@Autowired
	private TradeRepository tradeRepository;

	public Trade createTrade(@Valid Trade trade){
		return tradeRepository.save(trade);
	}

	public List<Trade> findAll() {
        List<Trade> allTrades = tradeRepository.findAll();
        return allTrades;
	}

	public Trade updateTrade(Integer id, Trade trade) {
		Optional<Trade> tradeModify = tradeRepository.findById(id);
		if(!tradeModify.isPresent()) {
			logger.error("Failed to update. Trade not Found");
			throw new NoSuchElementException("Failed to update Trade");
		}
		else {
            trade.setTradeId(tradeModify.get().getTradeId());
            trade.setCreationDate(tradeModify.get().getCreationDate());
            trade.setCreationName(tradeModify.get().getCreationName());
			logger.info("Trade has updated");
			return tradeRepository.save(trade);
		}
		
		
	}

	public void deleteById(Integer id) {
		Optional<Trade> tradeDelete = tradeRepository.findById(id);
		if (!tradeDelete.isPresent()) {
			logger.error("Failed to delete. Trade not Found");
			throw new NoSuchElementException("Failed to delete Trade");
        } else {
        	logger.info("Trade has deleted");
        	tradeRepository.deleteById(id);
        }
		
	}
	
	public Trade getbyId(Integer id) throws NoSuchElementException{
		Optional<Trade> trade = tradeRepository.findById(id);
		if (!trade.isPresent()) {
			logger.error("Failed to find. trade not Found");
			throw new NoSuchElementException("Failed to find trade");
        } else {
        	logger.debug("trade has find");
        	return trade.get();
        }
	}
}
