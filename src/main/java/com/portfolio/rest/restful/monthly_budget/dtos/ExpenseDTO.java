package com.portfolio.rest.restful.monthly_budget.dtos;

import com.portfolio.rest.restful.monthly_budget.expense.Category;

public class ExpenseDTO {
	
	private Category category;
	
	private int amount;

	public ExpenseDTO(Category category, int amount) {
		super();
		this.category = category;
		this.amount = amount;
	}
	
	public ExpenseDTO() {
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	

}
