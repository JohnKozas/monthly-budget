package com.portfolio.rest.restful.monthly_budget.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.rest.restful.monthly_budget.budget.Budget;
import com.portfolio.rest.restful.monthly_budget.dtos.BudgetDTO;
import com.portfolio.rest.restful.monthly_budget.service.BudgetService;

@RestController
public class BudgetController {
			
	private BudgetService budgetService;
	
	public BudgetController(BudgetService budgetService) {
		super();
		this.budgetService = budgetService;
	}
	
	@GetMapping("/showBudget")
	public List<Budget> showBudget() {
		return budgetService.showBudget();
	}

	@GetMapping("/generateBudget")
	public BudgetDTO generatedBudget() {
		return budgetService.generateBudget();
	}
	
	@DeleteMapping("/deleteAll")
	public String clearAll() {
		return budgetService.deleteAll();
	}
	
	@GetMapping("/addTestData")
	public void addTestData() {
		budgetService.addIncomesAndExpensesFromFileForTesting();
	}
	
}