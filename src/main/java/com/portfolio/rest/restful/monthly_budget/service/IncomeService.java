package com.portfolio.rest.restful.monthly_budget.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.portfolio.rest.restful.monthly_budget.dtos.IncomeDTO;
import com.portfolio.rest.restful.monthly_budget.exception.IncomeNotFoundException;
import com.portfolio.rest.restful.monthly_budget.income.Income;
import com.portfolio.rest.restful.monthly_budget.repositories.IncomeRepository;

@Service
public class IncomeService {

	private IncomeRepository incomeRepository;
	
    private ModelMapper modelMapper;
	
	public IncomeService(IncomeRepository incomeRepository, ModelMapper modelMapper) {
		super();
		this.incomeRepository = incomeRepository;
		this.modelMapper = modelMapper;
	}
	
    // Convert Income entity to IncomeDTO
    private IncomeDTO convertToDTO(Income income) {
        return modelMapper.map(income, IncomeDTO.class);
    }
    
    // Convert IncomeDTO to Income entity
    private Income convertToEntity(IncomeDTO incomeDTO) {
        return modelMapper.map(incomeDTO, Income.class);
    }

	public Integer getSumOfIncomes() {
		return incomeRepository.sumAmount();
	}

	public IncomeDTO updateIncome(int id, IncomeDTO updatedIncome) {
		Income income = incomeRepository.findById(id).orElseThrow(() -> new IncomeNotFoundException("Income not found"));
		income.setAmount(updatedIncome.getAmount());
		income.setCategory(updatedIncome.getCategory());
		incomeRepository.save(income);
		return updatedIncome;
	}
	
    public IncomeDTO getIncomeById(int id) {
        Income income = incomeRepository.findById(id).orElseThrow(() -> new IncomeNotFoundException("Income not found"));
        return modelMapper.map(income, IncomeDTO.class); // Convert Income to IncomeDTO
    }

    public Income saveIncome(IncomeDTO incomeDTO) {
        Income income = modelMapper.map(incomeDTO, Income.class); // Convert IncomeDTO to Income
        return incomeRepository.save(income);
    }
    
    public List<IncomeDTO> showAllIncomesDTO() {
    	List<Income> incomes = incomeRepository.findAll();
    	return incomes.stream()
                .map(this::convertToDTO) // Map each Income entity to a DTO
                .collect(Collectors.toList());
    }
    
	public void deleteIncome(int id) {
	    if (!incomeRepository.existsById(id)) {
	        throw new IncomeNotFoundException("Income not found");
	    }
		incomeRepository.deleteById(id);
	}
    
}
