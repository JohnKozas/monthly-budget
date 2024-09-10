package com.portfolio.rest.restful.monthly_budget.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.portfolio.rest.restful.monthly_budget.dtos.ExpenseDTO;
import com.portfolio.rest.restful.monthly_budget.dtos.IncomeDTO;
import com.portfolio.rest.restful.monthly_budget.exception.ExpenseNotFoundException;
import com.portfolio.rest.restful.monthly_budget.expense.Expense;
import com.portfolio.rest.restful.monthly_budget.income.Income;
import com.portfolio.rest.restful.monthly_budget.repositories.ExpensesRepository;

import jakarta.validation.Valid;

@Service
public class ExpensesService {
	
	private ExpensesRepository expensesRepository;
	
	private ExpenseDTO expenseDTO;
	
	private ModelMapper modelMapper;

	public ExpensesService(ExpensesRepository expensesRepository, ModelMapper modelMapper) {
		super();
		this.expensesRepository = expensesRepository;
		this.modelMapper = modelMapper;
	}
	
    // Convert Expense entity to ExpenseDTO
    private ExpenseDTO convertToDTO(Expense expense) {
        return modelMapper.map(expense, ExpenseDTO.class);
    }
    
    // Convert IncomeDTO to Income entity
    private Expense convertToEntity(ExpenseDTO expenseDTO) {
        return modelMapper.map(expenseDTO, Expense.class);
    }
	
	public Integer getSumOfExpenses() {
		return expensesRepository.sumAmount();
	}

	public ExpenseDTO updateExpense(int id, ExpenseDTO updateExpense) {
		Expense expense = expensesRepository.findById(id)
		        .orElseThrow(() -> new ExpenseNotFoundException("Expense not found"));

		expense.setAmount(updateExpense.getAmount());
		expense.setCategory(updateExpense.getCategory());
		expensesRepository.save(expense);

		return convertToDTO(expense); // Return the updated ExpenseDTO
	}

	public List<ExpenseDTO> findAll() {
		return expensesRepository.findAll().stream().map(this::convertToDTO)
	            .collect(Collectors.toList());
	}

	public ExpenseDTO findById(Integer id) {
        Expense expense = expensesRepository.findById(id)
                .orElseThrow(() -> new ExpenseNotFoundException("Expense "));
            return convertToDTO(expense);
	}

	public Expense saveExpense(@Valid ExpenseDTO expenseDTO) {
        Expense expense = convertToEntity(expenseDTO);
        return expensesRepository.save(expense);  
	}

	public void deleteById(int id) {
		Expense expense = expensesRepository.findById(id)
                .orElseThrow(() -> new ExpenseNotFoundException("Expense "));
		expensesRepository.delete(expense);
	}

}
