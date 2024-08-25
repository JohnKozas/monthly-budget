package com.portfolio.rest.restful.monthly_budget.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.rest.restful.monthly_budget.expenses.Expense;
import com.portfolio.rest.restful.monthly_budget.incomes.Income;
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
	

	@GetMapping("/incomes-expenses")
	public List<String> incomeExpencestPage() {
		return budgetService.getIncomesAndExpenses();
	}
	
	
}
