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

import com.kormul.skeleton.domain.RuleName;
import com.kormul.skeleton.service.RuleNameService;

@RestController
@RequestMapping("/rulename/rest")
public class RuleNameRestController {
	
	@Autowired
	RuleNameService ruleNameService;
	
	private static final Logger logger = LogManager.getLogger(RuleNameRestController.class);
	
	@PostMapping("/create")
    public String createRuleName(@RequestBody  @Valid RuleName ruleName) {
			logger.debug("Post request");
			ruleNameService.createRuleName(ruleName);
			return "RuleName create successfully";
	    }
	
    @GetMapping("/read")
    public List<RuleName> findAllRuleNames() {
    	logger.debug("GET request");
        return ruleNameService.findAllRuleName();
    }
    
    @PutMapping("/update/{id}")
    public String updateRuleName(@PathVariable final Integer id, @RequestBody @Valid RuleName ruleName) {
		logger.debug("PUT request");
		ruleNameService.updateRuleName(id, ruleName);
        return "RuleName updated successfully";
    }
    
    @DeleteMapping("/delete/{id}")
    public String deleteRuleNameById(@PathVariable final Integer id) {
    	ruleNameService.deleteRuleNameById(id);
        return "RuleName deleted successfully";
    }

}
