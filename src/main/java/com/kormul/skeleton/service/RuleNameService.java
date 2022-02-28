package com.kormul.skeleton.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kormul.skeleton.domain.RuleName;
import com.kormul.skeleton.repository.RuleNameRepository;

import lombok.Data;

@Service
@Data
public class RuleNameService {
	
	private static final Logger logger = LogManager.getLogger();
	
	@Autowired
	private RuleNameRepository ruleNameRepository;

	public RuleName createRuleName(@Valid RuleName ruleName) {
		return ruleNameRepository.save(ruleName);
	}

	public List<RuleName> findAllRuleName() {
        List<RuleName> allRuleNames = ruleNameRepository.findAll();
        return allRuleNames;
	}

	public RuleName updateRuleName(Integer id, RuleName ruleName) {
		Optional<RuleName> ruleNameModify = ruleNameRepository.findById(id);
		if(!ruleNameModify.isPresent()) {
			logger.error("Failed to update. RuleName not Found");
			throw new NoSuchElementException("Failed to update RuleName");
		}
		else {
			ruleName.setId(ruleNameModify.get().getId());
			logger.info("RuleName has updated");
			return ruleNameRepository.save(ruleName);
		}
	}

	public void deleteRuleNameById(Integer id) {
		Optional<RuleName> ruleNameDelete = ruleNameRepository.findById(id);
		if (!ruleNameDelete.isPresent()){
			logger.error("Failed to delete. RuleName not Found");
			throw new NoSuchElementException("Failed to delete RuleName");
        } else {
        	logger.info("RuleName has deleted");
        	ruleNameRepository.deleteById(id);
        }
		
	}
	
	public RuleName getbyId(Integer id) {
		
		Optional<RuleName> ruleName = ruleNameRepository.findById(id);
		if (!ruleName.isPresent()) {
			logger.error("Failed to find. ruleName not Found");
			throw new NoSuchElementException("Failed to find ruleName");
        } else {
        	logger.debug("ruleName has find");
        	return ruleName.get();
        }
	}
}
