package com.portfolio.rest.restful.monthly_budget.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.rest.restful.monthly_budget.expenses.Expense;
import com.portfolio.rest.restful.monthly_budget.incomes.Income;
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
		Expense expense = expensesRepository.findById(id).get();
		return expense;
	}
	
	@PostMapping("/add-expense")
	public Expense addExpense(@Valid @RequestBody Expense expense) {
		expensesRepository.save(expense);
		return expense;
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
