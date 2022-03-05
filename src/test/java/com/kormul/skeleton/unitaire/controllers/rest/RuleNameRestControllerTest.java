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
import com.kormul.skeleton.domain.RuleName;
import com.kormul.skeleton.service.RuleNameService;

@SpringBootTest
public class RuleNameRestControllerTest {

	@Autowired
	private WebApplicationContext webApplicationContext;
	
	private MockMvc mockMvc;
	
	@MockBean
	RuleNameService ruleNameService;
	
	private RuleName rule1 = new RuleName();
	private RuleName rule2 = new RuleName();
	private RuleName rule3 = new RuleName();
	private List<RuleName> rules = new ArrayList<RuleName>();
	
	@BeforeEach
	public void setUp() {
		
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		
		rule1.setId(1);
		rule2.setId(2);
		rule3.setId(3);
		
		rules.add(rule1);
		rules.add(rule2);
		rules.add(rule3);
		when(ruleNameService.createRuleName(rule1)).thenReturn(rule1);
		when(ruleNameService.findAllRuleName()).thenReturn(rules);
		when(ruleNameService.updateRuleName(1, rule1)).thenReturn(rule1);
		
	}
	
	@Test
	public void RuleCreateTest() throws JsonProcessingException, Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/rulename/rest/create")
                .content(new ObjectMapper().writeValueAsString(rule1))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
	}
	
	@Test
	public void RuleReadTest()  throws JsonProcessingException, Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/rulename/rest/read"))
        .andExpect(status().isOk());
	}
	
	@Test
	public void RuleUpdateTest()  throws JsonProcessingException, Exception{
        mockMvc.perform(MockMvcRequestBuilders.put("/rulename/rest/update/1")
                .content(new ObjectMapper().writeValueAsString(rule1))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
	}
	
	
	@Test
	public void RuleDeleteTest()  throws JsonProcessingException, Exception{
        mockMvc.perform(MockMvcRequestBuilders.put("/rulename/rest/update/1")
                .content(new ObjectMapper().writeValueAsString(rule1))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
	}
}
