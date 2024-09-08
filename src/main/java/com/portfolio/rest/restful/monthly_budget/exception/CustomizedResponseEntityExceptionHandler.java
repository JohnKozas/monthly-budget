package com.portfolio.rest.restful.monthly_budget.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex, WebRequest request) throws Exception {
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), 
				ex.getMessage(), request.getDescription(false));
		
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}

	@ExceptionHandler(ExpenseNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleExpenseNotFoundException(ExpenseNotFoundException ex) {
		ErrorDetails errorResponse = new ErrorDetails(LocalDateTime.now(), ex.getMessage(), HttpStatus.NOT_FOUND.toString());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
		
	
	@ExceptionHandler(IncomeNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleIncomeNotFoundException(IncomeNotFoundException ex) {
		ErrorDetails errorResponse = new ErrorDetails(LocalDateTime.now(), ex.getMessage(), HttpStatus.NOT_FOUND.toString());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

	
}
