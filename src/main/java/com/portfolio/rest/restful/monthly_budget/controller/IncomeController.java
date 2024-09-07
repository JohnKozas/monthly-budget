package com.portfolio.rest.restful.monthly_budget.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.portfolio.rest.restful.monthly_budget.expense.Expense;
import com.portfolio.rest.restful.monthly_budget.income.Income;
import com.portfolio.rest.restful.monthly_budget.repositories.IncomeRepository;
import com.portfolio.rest.restful.monthly_budget.service.IncomeService;

@RestController
public class IncomeController {
	
	private IncomeRepository incomeRepository;
	private IncomeService incomeService;

	public IncomeController(IncomeRepository incomeRepository, IncomeService incomeService) {
		super();
		this.incomeRepository = incomeRepository;
		this.incomeService = incomeService;
	}
	
	@GetMapping("/incomes")
	public List<Income> incomesPage() {
		return incomeRepository.findAll();
	}
	
	@GetMapping("/income/{id}")
	public Income showIncome(@PathVariable Integer id) {
		Optional<Income> income = incomeRepository.findById(id);
		
		if(income.isEmpty()) {
			throw new IncomeNotFoundException("id "+ id);
		}
		return income.get();
	}
	
	@PostMapping("/income")
	public ResponseEntity<Object> addIncome(@RequestBody Income income) {
		Income savedIncome = incomeRepository.save(income);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedIncome.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/delete-income/{id}")
	public void deleteIncome(@PathVariable int id) {
		incomeRepository.deleteById(id);
	}
	
	@PutMapping("update-income/{id}")
	public Income putIncome(@RequestBody Income income, @PathVariable int id) {
		Income updateIncome = incomeService.updateIncome(id, income);
		return updateIncome;
	}
	
	@GetMapping("/sum-incomes")
	public Integer incomesSum() {
		return incomeService.getSumOfIncomes();
	}

}
