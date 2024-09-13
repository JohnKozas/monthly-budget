package com.portfolio.rest.restful.monthly_budget.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.rest.restful.monthly_budget.budget.Budget;
import com.portfolio.rest.restful.monthly_budget.dtos.BudgetDTO;
import com.portfolio.rest.restful.monthly_budget.expense.Expense;
import com.portfolio.rest.restful.monthly_budget.income.Income;
import com.portfolio.rest.restful.monthly_budget.repositories.IncomeRepository;
import com.portfolio.rest.restful.monthly_budget.service.BudgetService;
import com.portfolio.rest.restful.monthly_budget.repositories.ExpensesRepository;

@RestController
public class BudgetController {
			
	private BudgetService budgetService;
	
	public BudgetController(BudgetService budgetService) {
		super();
		this.budgetService = budgetService;
	}
	

	@GetMapping("/budget")
	public BudgetDTO showGeneratedBudget() {
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
