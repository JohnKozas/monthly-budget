package com.portfolio.rest.restful.monthly_budget.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.portfolio.rest.restful.monthly_budget.dtos.ExpenseDTO;
import com.portfolio.rest.restful.monthly_budget.exception.ExpenseNotFoundException;
import com.portfolio.rest.restful.monthly_budget.expense.Expense;
import com.portfolio.rest.restful.monthly_budget.repositories.ExpensesRepository;
import com.portfolio.rest.restful.monthly_budget.service.ExpensesService;

import jakarta.validation.Valid;

@RestController
public class ExpensesController {
	
	private ExpensesService expensesService;
	
	public ExpensesController(ExpensesService expensesService) {
		super();
		this.expensesService = expensesService;
	}
	
	@GetMapping("/expenses")
	public ResponseEntity<List<ExpenseDTO>> expensesPage() {
		return new ResponseEntity<>(expensesService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/expense/{id}")
	public ResponseEntity<ExpenseDTO> showExpense(@PathVariable Integer id) {
		return new ResponseEntity<>(expensesService.findById(id), HttpStatus.OK);
	}
	
	@PostMapping("/expense")
	public ResponseEntity<Object> addExpense(@Valid @RequestBody ExpenseDTO expenseDTO) {
		Expense savedExpense = expensesService.saveExpense(expenseDTO);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedExpense.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/delete-expense/{id}")
	public ResponseEntity<Object> deleteExpense(@PathVariable int id) {
		expensesService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("update-expense/{id}")
	public ResponseEntity<ExpenseDTO> putExpense(@RequestBody ExpenseDTO expenseDTO, @PathVariable int id) {
		ExpenseDTO updateExpense = expensesService.updateExpense(id, expenseDTO);
		return new ResponseEntity<>(updateExpense, HttpStatus.OK);
	}
	
	@GetMapping("/sum-expenses")
	public ResponseEntity<Integer> expensesSum() {
		return new ResponseEntity<>(expensesService.getSumOfExpenses(), HttpStatus.OK);

	}
	
}
