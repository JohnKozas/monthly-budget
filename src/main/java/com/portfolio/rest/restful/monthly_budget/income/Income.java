package com.portfolio.rest.restful.monthly_budget.income;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.portfolio.rest.restful.monthly_budget.budget.Budget;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "incomes")
public class Income {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false)
	private String category;
	
	@Column(nullable = false)
	private int amount;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "budget_id")
	private Budget budget;
	
	public Income() {}

	public Income(int id, String category, int amount) {
		super();
		this.id = id;
		this.category = category;
		this.amount = amount;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public Budget getBudget() {
		return budget;
	}

	public void setBudget(Budget budget) {
		this.budget = budget;
	}

	@Override
	public String toString() {
		return "Income [id=" + id + ", category=" + category + ", amount=" + amount + "]";
	}
	


}
