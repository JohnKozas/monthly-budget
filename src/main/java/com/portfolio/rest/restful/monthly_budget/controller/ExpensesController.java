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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.portfolio.rest.restful.monthly_budget.expense.Expense;
import com.portfolio.rest.restful.monthly_budget.income.Income;
import com.portfolio.rest.restful.monthly_budget.repositories.ExpensesRepository;
import com.portfolio.rest.restful.monthly_budget.service.ExpensesService;

import jakarta.validation.Valid;

@RestController
public class ExpensesController {

	private ExpensesRepository expensesRepository;
	
	private ExpensesService expensesService;
	
	public ExpensesController(ExpensesRepository expensesRepository, ExpensesService expensesService) {
		super();
		this.expensesRepository = expensesRepository;
		this.expensesService = expensesService;
	}
	
	@GetMapping("/expenses")
	public List<Expense> expensesPage() {
		return expensesRepository.findAll();
	}
	
	@GetMapping("/expense/{id}")
	public Expense showExpense(@PathVariable Integer id) {
		Optional<Expense> expense = expensesRepository.findById(id);
		
		if(expense.isEmpty()) {
			throw new ExpenseNotFoundException("id: "+id);
		}
			
		return expense.get();
	}
	
	@PostMapping("/expense")
	public ResponseEntity<Object> addExpense(@Valid @RequestBody Expense expense) {
		Expense savedExpense = expensesRepository.save(expense);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedExpense.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/delete-expense/{id}")
	public void deleteIncome(@PathVariable int id) {
		expensesRepository.deleteById(id);
	}
	
	@PutMapping("update-expense/{id}")
	public Expense putExpense(@RequestBody Expense expense, @PathVariable int id) {
		Expense updateExpense = expensesService.updateExpense(id, expense);
		return updateExpense;
	}
	
	@GetMapping("/sum-expenses")
	public Integer expensesSum() {
		return expensesService.getSumOfExpenses();
	}
	
}
