package com.kormul.skeleton.controllers.web;

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

import com.kormul.skeleton.domain.RuleName;
import com.kormul.skeleton.service.RuleNameService;

@Controller
public class RuleNameController {
	
	@Autowired
	RuleNameService ruleNameService;
	
	private static final Logger logger = LogManager.getLogger(RuleNameController.class);


    @RequestMapping("/ruleName/list")
    public String home(Model model)
    {
    	logger.debug("get request RuleName : List");
        model.addAttribute("list",ruleNameService.findAllRuleName());
        return "ruleName/list";
    }

    @GetMapping("/ruleName/add")
    public String addRuleForm(Model model) {
    	
    	logger.debug("get request RuleName : add");
    	RuleName ruleName =  new RuleName();
        model.addAttribute("ruleName", ruleName);
        return "ruleName/add";
    }

    @PostMapping("/ruleName/validate")
    public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {

        
     	logger.debug("post request RuleName : validate");
         if(!result.hasFieldErrors())  {
         	ruleNameService.createRuleName(ruleName);
         	return "redirect:list";
         }
         logger.error("Post Request error : Validate");
         model.addAttribute("ruleName", ruleName);
        
    	return "ruleName/add";
    }

    @GetMapping("/ruleName/update")
    public String showUpdateForm(@RequestParam("id") Integer id, Model model) {

        logger.debug("get request ruleName : update");

    	try {
    		model.addAttribute("ruleName", ruleNameService.getbyId(id));
    	}catch (NoSuchElementException e) {
    		logger.error("Get request Update error, ruleName not fund");
		}
    	
    	return "ruleName/update";
    }

    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid @ModelAttribute("ruleName") RuleName ruleName, BindingResult result, Model model) {

    	logger.debug("post request ruleName : update");
    	
    	ruleNameService.updateRuleName(id, ruleName);
    	
        
    	return "redirect:/ruleName/list";
    }

    @GetMapping("/ruleName/delete")
    public String deleteRuleName(@RequestParam("id") Integer id, Model model) {
    	
        logger.debug("get request ruleName : delete");
        try {
        	ruleNameService.deleteRuleNameById(id);
        }catch (NoSuchElementException e) {
            return "redirect:/ruleName/list";
		}
        
        return "redirect:/ruleName/list";
    }
}
