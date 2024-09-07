package com.portfolio.rest.restful.monthly_budget.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.portfolio.rest.restful.monthly_budget.budget.Budget;
import com.portfolio.rest.restful.monthly_budget.expense.Expense;
import com.portfolio.rest.restful.monthly_budget.income.Income;
import com.portfolio.rest.restful.monthly_budget.repositories.BudgetRepository;
import com.portfolio.rest.restful.monthly_budget.repositories.ExpensesRepository;
import com.portfolio.rest.restful.monthly_budget.repositories.IncomeRepository;

@Service
public class BudgetService {
	
	private  ExpensesService expensesService;
	
	private  IncomeService incomeService;
	
	private IncomeRepository incomeRepository;
	
	private ExpensesRepository expenseRepository;

	private BudgetRepository budgetRepository;

	
	public BudgetService(ExpensesService expensesService, IncomeService incomeService,
			IncomeRepository incomeRepository, ExpensesRepository expenseRepository,
			BudgetRepository budgetRepository) {
		super();
		this.expensesService = expensesService;
		this.incomeService = incomeService;
		this.incomeRepository = incomeRepository;
		this.expenseRepository = expenseRepository;
		this.budgetRepository = budgetRepository;
	}

	public Budget generateBudget() {
		
		List<Income> incomes = incomeRepository.findAll(); // Retrieve all incomes
        List<Expense> expenses = expenseRepository.findAll(); // Retrieve all expenses
		
		int sumExpenses = expensesService.getSumOfExpenses();
		
		int sumIncomes = incomeService.getSumOfIncomes();
		
		int sumBudget = sumIncomes - sumExpenses;
		
		Budget budget = new Budget();
		budget.setBudget(sumBudget);
		budget.setDate(LocalDate.now());
		budget.setExpenses(expenses);
		budget.setIncomes(incomes);
		
//		Here the budget that created is set with the incomes and expenses from which occurred
		incomes.stream()
		.forEach(income -> income.setBudget(budget));
		expenses.stream()
		.forEach(income -> income.setBudget(budget));
		
		budgetRepository.save(budget);
		
		return budget;
	}
	
//	public List<String> getIncomesAndExpenses() {
//	
//	List<Expense> expenses = expensesRepository.findAll();
//	
//	List<Income> incomes = incomeRepository.findAll();
//	
//	
//	int size = expenses.size();
//	
//	List<String> combinedList = new ArrayList<>();
//	
//	for (int i = 0; i < size; i++) {
//		String combined = expenses.get(i).toString() + " , " + incomes.get(i).toString();
//        combinedList.add(combined);
//	}
//	
//	return combinedList;
//}


}
