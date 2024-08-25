package com.portfolio.rest.restful.monthly_budget.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.portfolio.rest.restful.monthly_budget.expenses.Expense;
import com.portfolio.rest.restful.monthly_budget.incomes.Income;
import com.portfolio.rest.restful.monthly_budget.repositories.ExpensesRepository;
import com.portfolio.rest.restful.monthly_budget.repositories.IncomeRepository;

@Service
public class BudgetService {
	
	private final ExpensesRepository expensesRepository;
	
	private final IncomeRepository incomeRepository;

	public BudgetService(ExpensesRepository expensesRepository, IncomeRepository incomeRepository) {
		super();
		this.expensesRepository = expensesRepository;
		this.incomeRepository = incomeRepository;
	}
	
	public List<String> getIncomesAndExpenses() {
		
		List<Expense> expenses = expensesRepository.findAll();
		
		List<Income> incomes = incomeRepository.findAll();
		
		int size = expenses.size();
		
		List<String> combinedList = new ArrayList<>();
		
		for (int i = 0; i < size; i++) {
			String combined = expenses.get(i).toString() + " , " + incomes.get(i).toString();
            combinedList.add(combined);
		}
		
		
		return combinedList;
	}

}
