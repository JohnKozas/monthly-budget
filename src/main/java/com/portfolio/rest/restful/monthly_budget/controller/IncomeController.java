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

import com.portfolio.rest.restful.monthly_budget.dtos.IncomeDTO;
import com.portfolio.rest.restful.monthly_budget.exception.IncomeNotFoundException;
import com.portfolio.rest.restful.monthly_budget.expense.Expense;
import com.portfolio.rest.restful.monthly_budget.income.Income;
import com.portfolio.rest.restful.monthly_budget.repositories.IncomeRepository;
import com.portfolio.rest.restful.monthly_budget.service.IncomeService;

@RestController
public class IncomeController {
	
	private IncomeService incomeService;

	public IncomeController(IncomeService incomeService) {
		super();
		this.incomeService = incomeService;
	}
	
	@GetMapping("/incomes")
	public ResponseEntity<List<IncomeDTO>> incomesPage() {
		List<IncomeDTO> incomes = incomeService.showAllIncomesDTO();
		return new ResponseEntity<List<IncomeDTO>>(incomes, HttpStatus.OK);
	}
	
	@GetMapping("/income/{id}")
	public ResponseEntity<IncomeDTO> getIncomeById(@PathVariable int id) {
	    return new ResponseEntity<>(incomeService.getIncomeById(id), HttpStatus.OK);
	}
	
	@PostMapping("/income")
	public ResponseEntity<Object> saveIncome(@RequestBody IncomeDTO incomeDTO) {
		Income savedIncome = incomeService.saveIncome(incomeDTO);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedIncome.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/delete-income/{id}")
	public ResponseEntity<Object> deleteIncome(@PathVariable int id) {
		incomeService.deleteIncome(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("update-income/{id}")
	public ResponseEntity<IncomeDTO> putIncome(@RequestBody IncomeDTO incomeDTO, @PathVariable int id) {
		IncomeDTO updateIncome = incomeService.updateIncome(id, incomeDTO);
		return new ResponseEntity<>(updateIncome, HttpStatus.OK);
	}
	
	@GetMapping("/sum-incomes")
	public ResponseEntity<Integer> incomesSum() {
		return new ResponseEntity<>(incomeService.getSumOfIncomes(), HttpStatus.OK);
	}
}
