package com.portfolio.rest.restful.monthly_budget.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.portfolio.rest.restful.monthly_budget.income.Income;

public interface IncomeRepository extends JpaRepository<Income, Integer>{
	
//	The Query must be FROM the Entity and not the Table
	@Query("SELECT SUM(e.amount) FROM Income e")
    Integer sumAmount();
	
}
