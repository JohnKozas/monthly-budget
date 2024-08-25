package com.portfolio.rest.restful.monthly_budget.service;

import org.springframework.stereotype.Service;

import com.portfolio.rest.restful.monthly_budget.expenses.Expense;
import com.portfolio.rest.restful.monthly_budget.repositories.ExpensesRepository;

@Service
public class ExpensesService {
	
	private ExpensesRepository expensesRepository;

	public ExpensesService(ExpensesRepository expensesRepository) {
		super();
		this.expensesRepository = expensesRepository;
	}
	
	public Integer getSumOfExpenses() {
		return expensesRepository.sumAmount();
	}

	public Expense updateExpense(int id, Expense updateExpense) {
		Expense expense = expensesRepository.findById(id).get();
		expense.setAmount(updateExpense.getAmount());
		expense.setCategory(updateExpense.getCategory());
		expensesRepository.save(expense);
		return expense;
	}

}
