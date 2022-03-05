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
import com.kormul.skeleton.domain.BidList;
import com.kormul.skeleton.service.BidListService;

@SpringBootTest
public class BidListRestControllerTest {

	@Autowired
	private WebApplicationContext webApplicationContext;
	
	private MockMvc mockMvc;
	
	@MockBean
	private BidListService bidListService;
	
	private BidList bid1 = new BidList();
	private BidList bid2 = new BidList();
	private BidList bid3 = new BidList();
	private List<BidList> bids = new ArrayList<BidList>();
	
	@BeforeEach
	public void setUp() {
		
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		
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
		when(bidListService.createBidList(bid1)).thenReturn(bid1);
		when(bidListService.findAll()).thenReturn(bids);
		when(bidListService.updateBidList(1, bid1)).thenReturn(bid1);
		
	}
	
	@Test
	public void BidListCreateTest() throws JsonProcessingException, Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/bidList/rest/create")
                .content(new ObjectMapper().writeValueAsString(bid1))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
	}
	
	@Test
	public void BidListCreateErrorTest() throws JsonProcessingException, Exception {
		bid1.setType("");
		mockMvc.perform(MockMvcRequestBuilders.post("/bidList/rest/create")
                .content(new ObjectMapper().writeValueAsString(bid1))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
	}
	
	@Test
	public void BidListReadTest()  throws JsonProcessingException, Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/bidList/rest/read"))
        .andExpect(status().isOk());
	}
	
	@Test
	public void BidListUpdateTest()  throws JsonProcessingException, Exception{
        mockMvc.perform(MockMvcRequestBuilders.put("/bidList/rest/update/1")
                .content(new ObjectMapper().writeValueAsString(bid1))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
	}
	
	@Test
	public void BidListUpdateErrorTest()  throws JsonProcessingException, Exception{
		bid1.setAccount("");
        mockMvc.perform(MockMvcRequestBuilders.put("/bidList/rest/update/1")
                .content(new ObjectMapper().writeValueAsString(bid1))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
	}
	
	
	@Test
	public void BidListDeleteTest()  throws JsonProcessingException, Exception{
        mockMvc.perform(MockMvcRequestBuilders.put("/bidList/rest/update/1")
                .content(new ObjectMapper().writeValueAsString(bid1))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
	}
}
