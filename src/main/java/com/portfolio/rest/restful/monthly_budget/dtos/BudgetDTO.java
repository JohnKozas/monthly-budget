package com.portfolio.rest.restful.monthly_budget.dtos;

import java.time.LocalDate;
import java.util.List;

public class BudgetDTO {
	
	private int budget;
	
	private LocalDate date;
	
    private List<IncomeDTO> incomes;
    
    private List<ExpenseDTO> expenses;
    
	public int getBudget() {
		return budget;
	}

	public void setBudget(int budget) {
		this.budget = budget;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public List<IncomeDTO> getIncomes() {
		return incomes;
	}

	public void setIncomes(List<IncomeDTO> incomes) {
		this.incomes = incomes;
	}

	public List<ExpenseDTO> getExpenses() {
		return expenses;
	}

	public void setExpenses(List<ExpenseDTO> expenses) {
		this.expenses = expenses;
	}

}
