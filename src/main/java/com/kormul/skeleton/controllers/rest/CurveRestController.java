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

import com.kormul.skeleton.domain.CurvePoint;
import com.kormul.skeleton.service.CurveService;

@RestController
@RequestMapping("/curve/rest")
public class CurveRestController {

	@Autowired
	CurveService curveService;
	
	private static final Logger logger = LogManager.getLogger(CurveRestController.class);

	@PostMapping("/create")
    public String createCurve(@RequestBody  @Valid CurvePoint curvePoint) {
			logger.debug("Post request");
			curveService.createCurvePoint(curvePoint);
			return "CurvePoint create successfully";
	    }
	
    @GetMapping("/read")
    public List<CurvePoint> findAllCurvePoints() {
    	logger.debug("GET request");
        return curveService.findAllCurvePoint();
    }
    
    @PutMapping("/update/{id}")
    public String updateCurvePoint(@PathVariable final Integer id, @RequestBody @Valid CurvePoint curvePoint) {
		logger.debug("PUT request");
		curveService.updateCurvePoint(id, curvePoint);
        return "CurvePoint updated successfully";
    }
    
    @DeleteMapping("/delete/{id}")
    public String deleteCurvePointById(@PathVariable final Integer id) {
    	curveService.deleteCurvePointById(id);
        return "CurvePoint deleted successfully";
    }
}
