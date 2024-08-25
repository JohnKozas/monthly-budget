package com.portfolio.rest.restful.monthly_budget.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.portfolio.rest.restful.monthly_budget.incomes.Income;
import com.portfolio.rest.restful.monthly_budget.repositories.IncomeRepository;
import com.portfolio.rest.restful.monthly_budget.service.errors.ResourceNotFoundException;

@Service
public class IncomeService {

	private IncomeRepository incomeRepository;
	
	public IncomeService(IncomeRepository incomeRepository) {
		super();
		this.incomeRepository = incomeRepository;
	}

	public Integer getSumOfIncomes() {
		return incomeRepository.sumAmount();
	}

	public Income updateIncome(int id, Income updatedIncome) {
		Income income = incomeRepository.findById(id).get();
//	.orElseThrow(() -> new ResourceNotFoundException("Income id not found"));//This is for the errors
		income.setAmount(updatedIncome.getAmount());
		income.setCategory(updatedIncome.getCategory());
		incomeRepository.save(income);
		return income;
	}
}
