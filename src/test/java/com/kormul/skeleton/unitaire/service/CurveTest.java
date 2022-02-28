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

import com.kormul.skeleton.domain.CurvePoint;
import com.kormul.skeleton.repository.CurvePointRepository;
import com.kormul.skeleton.service.CurveService;

@SpringBootTest
public class CurveTest {
	
	@Autowired
	private CurveService curveService;
	
	@Mock
	private CurvePointRepository curveRepository;
	
	private CurvePoint curv1 = new CurvePoint();
	private CurvePoint curv2 = new CurvePoint();
	private CurvePoint curv3 = new CurvePoint();
	private Optional<CurvePoint> curvOpt = Optional.of(curv1);
	private List<CurvePoint> curves = new ArrayList<CurvePoint>();
	
	@BeforeEach
	public void setUp() {
		
		curv1.setId(1);
		curv2.setId(2);
		curv3.setId(3);
		
		curv1.setCurveId(1);
		curv2.setCurveId(2);
		curv3.setCurveId(3);

		
		curves.add(curv1);
		curves.add(curv2);
		curves.add(curv3);
		curveService.setCurvepointRepository(curveRepository); 
		
		when(curveRepository.save(any(CurvePoint.class))).thenReturn(curv1);
		when(curveRepository.findAll()).thenReturn(curves);
		when(curveRepository.findById(1)).thenReturn(curvOpt);
	}
	
	@Test
	public void createCurvListTest() {
		
		CurvePoint curv = curveService.createCurvePoint(curv1);
		assertTrue(1 == checkCurvListNoModify(curv));

	}
	
	@Test
	public void findAllCurvListTest() {
		List<CurvePoint> curvePoints = curveService.findAllCurvePoint();
		assertTrue(1 == checkCurvListNoModify(curvePoints.get(0)));
		assertTrue(2 == checkCurvListNoModify(curvePoints.get(1)));
		assertTrue(3 == checkCurvListNoModify(curvePoints.get(2)));

	}	
	
	@Test
	public void UpdateCurvListTest() {
		CurvePoint curve = new CurvePoint();
		curve.setId(4);
		curve.setCurveId(4);
		
		CurvePoint curvePoint = curveService.updateCurvePoint(1, curve);
		assertTrue(curvePoint.getId() == 1);
		assertTrue(curvePoint.getCurveId() == 1);
	}
	
	@Test
	public void getCurvListTest() {
		CurvePoint curvePoint = curveService.getbyId(1);
		assertTrue(1 == checkCurvListNoModify(curvePoint));

	}
	
	public int checkCurvListNoModify(CurvePoint curve) {
		
		assertTrue(curve.getId() != null);
		assertTrue(curve.getCurveId() == curve.getId());
		
		return curve.getId();
	}

}
