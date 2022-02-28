package com.kormul.skeleton.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kormul.skeleton.domain.BidList;
import com.kormul.skeleton.repository.BidListRepository;

import lombok.Data;

@Service
@Data
public class BidListService {

	private static final Logger logger = LogManager.getLogger();
	
	
	@Autowired
	private BidListRepository bidListRepository;

	public BidList createBidList(@Valid BidList bidList) {
		
		/*List<BidList> doubleBidList = bidListRepository.findByAccountAndType(bidList.getAccount(), bidList.getType());
		
		if(!doubleBidList.isEmpty()) {
			logger.error("Failed to create a new BidList, anothoer BidList matches the one that");
			throw new NoSuchElementException("Failed to create the new BidList");
		}*/
		//else {
			logger.info("Creation of the list");
			return bidListRepository.save(bidList);
		//}
	}

	public List<BidList> findAll() {
        List<BidList> allBidLists = bidListRepository.findAll();
        return allBidLists;
	}

	public BidList updateBidList(Integer id, BidList bidList) {
		Optional<BidList> bidListModify = bidListRepository.findById(id);
		if(!bidListModify.isPresent()) {
			logger.error("Failed to update. BidList not Found");
			throw new NoSuchElementException("Failed to update BidList");
		}
		else {
			bidList.setBidListId(bidListModify.get().getBidListId());
			logger.info("BidLiast has updated");
			return bidListRepository.save(bidList);
		}
		
		
	}

	public void deleteById(Integer id) throws NoSuchElementException{
		Optional<BidList> bidListDelete = bidListRepository.findById(id);
		if (!bidListDelete.isPresent()) {
			logger.error("Failed to delete. BidList not Found");
			throw new NoSuchElementException("Failed to delete BidList");
        } else {
        	logger.info("BidList has deleted");
        	bidListRepository.deleteById(id);
        }
		
	}

	public BidList getbyId(Integer id) throws NoSuchElementException{
		Optional<BidList> bidList = bidListRepository.findById(id);
		if (!bidList.isPresent()) {
			logger.error("Failed to find. BidList not Found");
			throw new NoSuchElementException("Failed to find BidList");
        } else {
        	logger.debug("BidList has find");
        	return bidList.get();
        }
	}
}
