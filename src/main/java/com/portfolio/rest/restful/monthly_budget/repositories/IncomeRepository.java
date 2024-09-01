package com.portfolio.rest.restful.monthly_budget.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.portfolio.rest.restful.monthly_budget.income.Income;

public interface IncomeRepository extends JpaRepository<Income, Integer>{
	
	
	@Query("SELECT SUM(e.amount) FROM INCOMES e")
    Integer sumAmount();
	
}
