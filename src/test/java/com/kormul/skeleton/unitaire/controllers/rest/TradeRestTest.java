package com.kormul.skeleton.unitaire.controllers.rest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kormul.skeleton.domain.Trade;
import com.kormul.skeleton.service.TradeService;

@SpringBootTest
public class TradeRestTest {
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	private MockMvc mockMvc;
	
	@MockBean
	TradeService tradeService;
	
	private Trade trade1 = new Trade();
	private Trade trade2 = new Trade();
	private Trade trade3 = new Trade();
	private List<Trade> trades = new ArrayList<Trade>();

	@BeforeEach
	public void setUp() {
		
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		
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
		
		when(tradeService.createTrade(trade1)).thenReturn(trade1);
		when(tradeService.findAll()).thenReturn(trades);
		when(tradeService.updateTrade(1, trade1)).thenReturn(trade1);
		
	}
	
	@Test
	public void TradeCreateTest() throws JsonProcessingException, Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/trade/rest/create")
                .content(new ObjectMapper().writeValueAsString(trade1))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
	}
	
	@Test
	public void TradeCreateErrorTest() throws JsonProcessingException, Exception {
		trade1.setType("");
		mockMvc.perform(MockMvcRequestBuilders.post("/trade/rest/create")
                .content(new ObjectMapper().writeValueAsString(trade1))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
	}
	
	@Test
	public void TradeReadTest()  throws JsonProcessingException, Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/trade/rest/read"))
        .andExpect(status().isOk());
	}
	
	@Test
	public void TradeUpdateTest()  throws JsonProcessingException, Exception{
        mockMvc.perform(MockMvcRequestBuilders.put("/trade/rest/update/1")
                .content(new ObjectMapper().writeValueAsString(trade1))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
	}
	
	@Test
	public void TradeUpdateErrorTest()  throws JsonProcessingException, Exception{
		trade1.setAccount("");
        mockMvc.perform(MockMvcRequestBuilders.put("/trade/rest/update/1")
                .content(new ObjectMapper().writeValueAsString(trade1))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
	}
	
	
	@Test
	public void TradeDeleteTest()  throws JsonProcessingException, Exception{
        mockMvc.perform(MockMvcRequestBuilders.put("/trade/rest/update/1")
                .content(new ObjectMapper().writeValueAsString(trade1))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
	}
}
