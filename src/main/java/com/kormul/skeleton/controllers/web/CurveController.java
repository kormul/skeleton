package com.kormul.skeleton.controllers.web;

import java.sql.Timestamp;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kormul.skeleton.domain.CurvePoint;
import com.kormul.skeleton.service.CurveService;

@Controller
public class CurveController {
	
	@Autowired
	CurveService curveService;
	
	private static final Logger logger = LogManager.getLogger(CurveController.class);

	//Api
	
	// Endpoint
	
    @RequestMapping("/curvePoint/list")
    public String home(Model model)
    {
    	
    	logger.debug("get request Curve : List");
        model.addAttribute("list",curveService.findAllCurvePoint());
        return "curvePoint/list";
    }

    @GetMapping("/curvePoint/add")
    public String addCurveForm(Model model) {
    	
    	logger.debug("get request CurvePoint : add");
        CurvePoint curvePoint = new CurvePoint();
        model.addAttribute("curvePoint", curvePoint);
     
        return "curvePoint/add";
    }

    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
    	
    	logger.debug("post request CurvePorint : validate");
        if(!result.hasFieldErrors())  {
        	curvePoint.setCreationDate(new Timestamp(System.currentTimeMillis()));
        	curveService.createCurvePoint(curvePoint);
        	return "redirect:list";
        }
        logger.error("Post Request error : Validate");
        model.addAttribute("curvePoint", curvePoint);
        return "curvePoint/add";
    }

    @GetMapping("/curvePoint/update")
    public String showUpdateForm(@RequestParam("id") Integer id, Model model) {

        logger.debug("get request curvePoint : update");
    	try {
    		model.addAttribute("curvePoint", curveService.getbyId(id));
    	}catch (NoSuchElementException e) {
    		logger.error("Get request Update error, curvePoint not found");
		}
    	
    	return "curvePoint/update";
    }

    @PostMapping("/curvePoint/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid @ModelAttribute("curve") CurvePoint curvePoint, BindingResult result, Model model) {
    	
    	logger.debug("post request CurvePoint : update");

    	curveService.updateCurvePoint(id, curvePoint);
    	
    	return "redirect:/curvePoint/list";
    }

    @GetMapping("/curvePoint/delete")
    public String deleteBid(@RequestParam("id") Integer id, Model model) {
        
        logger.debug("get request curvePoint : delete");
        try {
            curveService.deleteCurvePointById(id);
        }catch (NoSuchElementException e) {
            return "redirect:/curvePoint/list";
		}
        return "redirect:/curvePoint/list";
    }
}