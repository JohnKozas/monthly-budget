package com.portfolio.rest.restful.monthly_budget.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.portfolio.rest.restful.monthly_budget.expense.Expense;

@Repository
public interface ExpensesRepository extends JpaRepository<Expense, Integer>{
	

	@Query("SELECT SUM(e.amount) FROM EXPENSES e")
    Integer sumAmount();
}
