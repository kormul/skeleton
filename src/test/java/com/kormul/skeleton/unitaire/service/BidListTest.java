package com.kormul.skeleton.unitaire.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kormul.skeleton.domain.BidList;
import com.kormul.skeleton.repository.BidListRepository;
import com.kormul.skeleton.service.BidListService;

@SpringBootTest
public class BidListTest {
	
	@Autowired
	private BidListService bidListService;
	
	@Mock
	private BidListRepository bidListRepository;
	
	private BidList bid1 = new BidList();
	private BidList bid2 = new BidList();
	private BidList bid3 = new BidList();
	private Optional<BidList> bidOpt = Optional.of(bid1);
	private List<BidList> bids = new ArrayList<BidList>();
	
	@BeforeEach
	public void setUp() {
		
		bid1.setBidListId(1);
		bid2.setBidListId(2);
		bid3.setBidListId(3);
		
		bid1.setAccount("account 1");
		bid2.setAccount("account 2");
		bid3.setAccount("account 3");

		bid1.setType("type 1");
		bid2.setType("type 2");
		bid3.setType("type 3");
		
		bids.add(bid1);
		bids.add(bid2);
		bids.add(bid3);
		bidListService.setBidListRepository(bidListRepository); 
		
		when(bidListRepository.save(any(BidList.class))).thenReturn(bid1);
		when(bidListRepository.findAll()).thenReturn(bids);
		when(bidListRepository.findById(1)).thenReturn(bidOpt);
	}
	
	@Test
	public void createBidListTest() {
		
		BidList bid = bidListService.createBidList(bid1);
		assertTrue(1 == checkBidListNoModify(bid));

	}
	
	@Test
	public void findAllBidListTest() {
		List<BidList> ListBidLists = bidListService.findAll();
		assertTrue(1 == checkBidListNoModify(ListBidLists.get(0)));
		assertTrue(2 == checkBidListNoModify(ListBidLists.get(1)));
		assertTrue(3 == checkBidListNoModify(ListBidLists.get(2)));

	}	
	
	@Test
	public void UpdateBidListTest() {
		BidList bid4 = new BidList();
		bid4.setBidListId(4);
		bid4.setAccount("account 4");
		bid4.setType("type 4");
		
		BidList bid = bidListService.updateBidList(1, bid4);
		assertTrue(bid.getBidListId() == 1);
		assertTrue(bid.getAccount().equalsIgnoreCase("account 1"));
		assertTrue(bid.getType().equalsIgnoreCase("type 1"));
	}
	
	@Test
	public void getBidListTest() {
		BidList bid = bidListService.getbyId(1);
		assertTrue(1 == checkBidListNoModify(bid));

	}
	
	public int checkBidListNoModify(BidList bid) {
		assertTrue(bid.getBidListId() != null);
		assertTrue(bid.getAccount().equalsIgnoreCase("account "+bid.getBidListId()));
		
		assertTrue(bid.getType().equalsIgnoreCase("type "+bid.getBidListId()));
		return bid.getBidListId();
	}

}
