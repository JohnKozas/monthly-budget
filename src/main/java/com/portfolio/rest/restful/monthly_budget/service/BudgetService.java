package com.portfolio.rest.restful.monthly_budget.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.portfolio.rest.restful.monthly_budget.budget.Budget;
import com.portfolio.rest.restful.monthly_budget.dtos.BudgetDTO;
import com.portfolio.rest.restful.monthly_budget.exception.BudgetNotFoundException;
import com.portfolio.rest.restful.monthly_budget.expense.Expense;
import com.portfolio.rest.restful.monthly_budget.income.Income;
import com.portfolio.rest.restful.monthly_budget.repositories.BudgetRepository;
import com.portfolio.rest.restful.monthly_budget.repositories.ExpensesRepository;
import com.portfolio.rest.restful.monthly_budget.repositories.IncomeRepository;

@Service
public class BudgetService {
	
	private  ExpensesService expensesService;
	
	private  IncomeService incomeService;
	
	private BudgetRepository budgetRepository;
	
	private ModelMapper modelMapper;
	
	private JdbcTemplate jdbcTemplate;

	
	public BudgetService(ExpensesService expensesService, IncomeService incomeService,
			BudgetRepository budgetRepository, ModelMapper modelMapper, JdbcTemplate jdbcTemplate) {
		super();
		this.expensesService = expensesService;
		this.incomeService = incomeService;
		this.budgetRepository = budgetRepository;
		this.modelMapper = modelMapper;
		this.jdbcTemplate = jdbcTemplate;
	}

	public BudgetDTO generateBudget() {
		
		List<Income> incomes = incomeService.showAllIncomes(); // Retrieve all incomes
        List<Expense> expenses = expensesService.findAll(); // Retrieve all expenses
		
		Integer sumExpenses = expensesService.getSumOfExpenses();
		
		Integer sumIncomes = incomeService.getSumOfIncomes();
		
		Integer sumBudget = sumIncomes - sumExpenses;
		
		Budget budget = new Budget();
		budget.setBudget(sumBudget);
		budget.setDate(LocalDate.now());
		budget.setExpenses(expenses);
		budget.setIncomes(incomes);
		
//		Here the budget that created is set with the incomes and expenses from which occurred
		incomes.stream()
		.forEach(income -> income.setBudget(budget));
		expenses.stream()
		.forEach(expense -> expense.setBudget(budget));
		
		budgetRepository.save(budget);
		
		return modelMapper.map(budget, BudgetDTO.class);
	}
	
	public String deleteAll() {
		
		expensesService.deleteAll();
		incomeService.deleteAll();
		budgetRepository.deleteAll();
		
		return "All entities are deleted";
	}
	
	public void addIncomesAndExpensesFromFileForTesting() {
		// Read data.sql file from resources
        String dataSql = new BufferedReader(new InputStreamReader(
            getClass().getClassLoader().getResourceAsStream("data.sql"), StandardCharsets.UTF_8))
            .lines()
            .collect(Collectors.joining("\n"));

        // Split SQL by semicolon and execute each statement
        String[] sqlStatements = dataSql.split(";");
        for (String statement : sqlStatements) {
            if (!statement.trim().isEmpty()) {
                jdbcTemplate.execute(statement.trim());
            }
        }
    }

	public List<Budget> showBudget() {
		if (budgetRepository.findAll().isEmpty()) {
			throw new BudgetNotFoundException("There are no budgets");
		} else {
			List<Budget> budgets = budgetRepository.findAll().stream().toList();
			return budgets;
		}
	}
//.forEach(budget -> modelMap.map(budget, budgetDTO))
}