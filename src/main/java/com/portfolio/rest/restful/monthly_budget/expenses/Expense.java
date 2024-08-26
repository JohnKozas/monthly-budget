package com.portfolio.rest.restful.monthly_budget.expenses;


import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Positive;

@Entity(name = "EXPENSES")
public class Expense {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Enumerated(EnumType.STRING)
	private Category category;
	
	@Positive //This belongs to jakarta.validation, means that only positive values are acceptable 
	private int amount;
	
	public Expense() {}

	public Expense(Integer id, Category category, int amount) {
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

	@Override
	public String toString() {
		return "Expense [id=" + id + ", category=" + category + ", amount=" + amount + "]";
	}
	
	
	
}
