package com.portfolio.rest.restful.monthly_budget.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.portfolio.rest.restful.monthly_budget.expense.Expense;

public interface ExpensesRepository extends JpaRepository<Expense, Integer>{
	
//	The Query must be FROM the Entity and not the Table
	@Query("SELECT SUM(e.amount) FROM Expense e")
    Integer sumAmount();
}
