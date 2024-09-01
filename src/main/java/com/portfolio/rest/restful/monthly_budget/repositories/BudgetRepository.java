package com.portfolio.rest.restful.monthly_budget.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.portfolio.rest.restful.monthly_budget.budget.Budget;

public interface BudgetRepository extends JpaRepository<Budget, Integer>{
	
	
}
