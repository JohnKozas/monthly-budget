package com.portfolio.rest.restful.monthly_budget.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class BudgetNotFoundException extends RuntimeException {

	public BudgetNotFoundException(String message) {
		super(message);
	}
	
}
