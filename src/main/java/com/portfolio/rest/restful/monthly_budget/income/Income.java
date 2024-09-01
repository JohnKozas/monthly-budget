package com.portfolio.rest.restful.monthly_budget.income;

import com.portfolio.rest.restful.monthly_budget.budget.Budget;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "INCOMES")
public class Income {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String category;
	
	private int amount;
	
	@ManyToOne
	@JoinColumn(name = "budget_id")
	private Budget budget;
	
	public Income() {}

	public Income(int id, String category, int amount) {
		super();
		this.id = id;
		this.category = category;
		this.amount = amount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Income [id=" + id + ", category=" + category + ", amount=" + amount + "]";
	}
	


}
