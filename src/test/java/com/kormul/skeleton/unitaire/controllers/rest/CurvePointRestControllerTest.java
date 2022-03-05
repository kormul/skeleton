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
import com.kormul.skeleton.domain.CurvePoint;
import com.kormul.skeleton.service.CurveService;

@SpringBootTest
public class CurvePointRestControllerTest {
	

	@Autowired
	private WebApplicationContext webApplicationContext;
	
	private MockMvc mockMvc;
	
	@MockBean
	private CurveService curveService;
	
	private CurvePoint curv1 = new CurvePoint();
	private CurvePoint curv2 = new CurvePoint();
	private CurvePoint curv3 = new CurvePoint();
	private List<CurvePoint> curves = new ArrayList<CurvePoint>();

	@BeforeEach
	public void setUp() {
		
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		
		curv1.setId(1);
		curv2.setId(2);
		curv3.setId(3);
		
		curv1.setCurveId(1);
		curv2.setCurveId(2);
		curv3.setCurveId(3);

		
		curves.add(curv1);
		curves.add(curv2);
		curves.add(curv3);
		
		when(curveService.createCurvePoint(curv1)).thenReturn(curv1);
		when(curveService.findAllCurvePoint()).thenReturn(curves);
		when(curveService.updateCurvePoint(1, curv1)).thenReturn(curv1);
	}
	
	@Test
	public void CurveCreateTest() throws JsonProcessingException, Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/curve/rest/create")
                .content(new ObjectMapper().writeValueAsString(curv1))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
	}
	
	@Test
	public void CurveCreateErrorTest() throws JsonProcessingException, Exception {
		curv1.setCurveId(null);
		mockMvc.perform(MockMvcRequestBuilders.post("/curve/rest/create")
                .content(new ObjectMapper().writeValueAsString(curv1))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
	}
	
	@Test
	public void CurveReadTest()  throws JsonProcessingException, Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/curve/rest/read"))
        .andExpect(status().isOk());
	}
	
	@Test
	public void CurveUpdateTest()  throws JsonProcessingException, Exception{
        mockMvc.perform(MockMvcRequestBuilders.put("/curve/rest/update/1")
                .content(new ObjectMapper().writeValueAsString(curv1))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
	}
	
	@Test
	public void CurveUpdateErrorTest()  throws JsonProcessingException, Exception{
		curv1.setCurveId(null);
        mockMvc.perform(MockMvcRequestBuilders.put("/curve/rest/update/1")
                .content(new ObjectMapper().writeValueAsString(curv1))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
	}
	
	
	@Test
	public void CurvetDeleteTest()  throws JsonProcessingException, Exception{
        mockMvc.perform(MockMvcRequestBuilders.put("/curve/rest/update/1")
                .content(new ObjectMapper().writeValueAsString(curv1))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
	}
	
}
