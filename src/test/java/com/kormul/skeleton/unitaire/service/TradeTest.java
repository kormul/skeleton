package com.kormul.skeleton.unitaire.service;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kormul.skeleton.domain.Trade;
import com.kormul.skeleton.repository.TradeRepository;
import com.kormul.skeleton.service.TradeService;

@SpringBootTest
public class TradeTest {
	
	@Autowired
	private TradeService tradeService;
	
	@Mock
	private TradeRepository tradeRepository;
	
	private Trade trade1 = new Trade();
	private Trade trade2 = new Trade();
	private Trade trade3 = new Trade();
	private Optional<Trade> tradeOpt = Optional.of(trade1);
	private List<Trade> trades = new ArrayList<Trade>();
	
	@BeforeEach
	public void setUp() {
		
		trade1.setTradeId(1);
		trade2.setTradeId(2);
		trade3.setTradeId(3);
		
		trade1.setAccount("Account 1");
		trade2.setAccount("Account 2");
		trade3.setAccount("Account 3");
		
		trade1.setType("Type 1");
		trade2.setType("Type 2");
		trade3.setType("Type 3");

		
		trades.add(trade1);
		trades.add(trade2);
		trades.add(trade3);
		tradeService.setTradeRepository(tradeRepository); 
		
		when(tradeRepository.save(any(Trade.class))).thenReturn(trade1);
		when(tradeRepository.findAll()).thenReturn(trades);
		when(tradeRepository.findById(1)).thenReturn(tradeOpt);
	}
	
	@Test
	public void createTradeTest() {
		
		Trade trade = tradeService.createTrade(trade1);
		assertTrue(1 == checkTradeNoModify(trade));

	}
	
	@Test
	public void findAllTradeTest() {
		List<Trade> TradeList = tradeService.findAll();
		assertTrue(1 == checkTradeNoModify(TradeList.get(0)));
		assertTrue(2 == checkTradeNoModify(TradeList.get(1)));
		assertTrue(3 == checkTradeNoModify(TradeList.get(2)));

	}	
	
	@Test
	public void UpdateTradeTest() {
		Trade trade = new Trade();
		trade.setTradeId(4);
		trade.setAccount("Account 4");
		trade.setType("Type 4");
		
		Trade tradeModify = tradeService.updateTrade(1, trade);
		
		assertTrue(tradeModify.getTradeId() == 1);
		assertTrue(tradeModify.getAccount().equalsIgnoreCase("account 1"));
		assertTrue(tradeModify.getType().equalsIgnoreCase("type 1"));
		
	}
	
	@Test
	public void getTradeTest() {
		Trade trade = tradeService.getbyId(1);
		assertTrue(1 == checkTradeNoModify(trade));

	}
	
	public int checkTradeNoModify(Trade trade) {
		
		assertTrue(trade.getTradeId() != null);
		
		assertTrue(trade.getAccount().equalsIgnoreCase("account "+ trade.getTradeId()));
		
		assertTrue(trade.getType().equalsIgnoreCase("type "+ trade.getTradeId()));
		
		return trade.getTradeId();
	}

}
