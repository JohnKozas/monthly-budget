package com.portfolio.rest.restful.monthly_budget.budget;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.portfolio.rest.restful.monthly_budget.expense.Expense;
import com.portfolio.rest.restful.monthly_budget.income.Income;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "budget")
public class Budget {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	private int budget;
	
	@Column(nullable = false)
	private LocalDate date;
	
    @OneToMany(mappedBy = "budget")
    @Column(nullable = false)
    private List<Income> incomes;
    
    @OneToMany(mappedBy = "budget")
    @Column(nullable = false)
    private List<Expense> expenses;
    
    public Budget() {}

	public Budget(int id, int budget, LocalDate date, List<Income> incomes, List<Expense> expenses) {
		super();
		this.id = id;
		this.budget = budget;
		this.date = LocalDate.now();
		this.incomes = incomes;
		this.expenses = expenses;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public List<Income> getIncomes() {
		return incomes;
	}

	public void setIncomes(List<Income> incomes) {
		this.incomes = incomes;
	}

	public List<Expense> getExpenses() {
		return expenses;
	}

	public void setExpenses(List<Expense> expenses) {
		this.expenses = expenses;
	}

	@Override
	public String toString() {
		return "Budget [id=" + id + ", budget=" + budget + ", date=" + date + ", incomes=" + incomes + ", expenses="
				+ expenses + "]";
	}
    
    

}
