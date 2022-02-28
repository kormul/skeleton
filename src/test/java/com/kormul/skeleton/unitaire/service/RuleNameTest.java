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

import com.kormul.skeleton.domain.RuleName;
import com.kormul.skeleton.repository.RuleNameRepository;
import com.kormul.skeleton.service.RuleNameService;

@SpringBootTest
public class RuleNameTest {

	@Autowired
	private RuleNameService ruleNameService;
	
	@Mock
	private RuleNameRepository ruleNameRepository;
	
	private RuleName rule1 = new RuleName();
	private RuleName rule2 = new RuleName();
	private RuleName rule3 = new RuleName();
	private Optional<RuleName> ruleOpt = Optional.of(rule1);
	private List<RuleName> rules = new ArrayList<RuleName>();
	
	@BeforeEach
	public void setUp() {
		
		rule1.setId(1);
		rule2.setId(2);
		rule3.setId(3);
		
		rules.add(rule1);
		rules.add(rule2);
		rules.add(rule3);
		ruleNameService.setRuleNameRepository(ruleNameRepository); 
		
		when(ruleNameRepository.save(any(RuleName.class))).thenReturn(rule1);
		when(ruleNameRepository.findAll()).thenReturn(rules);
		when(ruleNameRepository.findById(1)).thenReturn(ruleOpt);
	}
	
	@Test
	public void createRuleNameTest() {
		
		RuleName rule = ruleNameService.createRuleName(rule1);
		assertTrue(1 == checkRuleNoModify(rule));

	}
	
	@Test
	public void findAllRuleNameTest() {
		List<RuleName> ruleNames = ruleNameService.findAllRuleName();
		assertTrue(1 == checkRuleNoModify(ruleNames.get(0)));
		assertTrue(2 == checkRuleNoModify(ruleNames.get(1)));
		assertTrue(3 == checkRuleNoModify(ruleNames.get(2)));

	}	
	
	@Test
	public void UpdateRuleNameTest() {
		RuleName rule = new RuleName();
		rule.setId(4);
		
		RuleName ruleName = ruleNameService.updateRuleName(1, rule);
		assertTrue(ruleName.getId() == 1);
	}
	
	@Test
	public void getRuleNameTest() {
		RuleName ruleName = ruleNameService.getbyId(1);
		assertTrue(1 == checkRuleNoModify(ruleName));

	}
	
	public int checkRuleNoModify(RuleName ruleName) {
		
		assertTrue(ruleName.getId() != null);
		
		return ruleName.getId();
	}

}
