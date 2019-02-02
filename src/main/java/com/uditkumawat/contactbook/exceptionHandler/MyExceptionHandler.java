package com.uditkumawat.contactbook.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Centralized exception handler
 * 
 * @author ukumawat
 *
 */
@RestControllerAdvice
public class MyExceptionHandler extends ResponseEntityExceptionHandler {

	@RequestMapping(produces = "application/json")
	@ExceptionHandler(ContactBookException.class)
	ResponseEntity<ProblemDetail> handleException(ContactBookException e) {

		ProblemDetail pd = new ProblemDetail();
		pd.setMessage(e.getMessage());
		pd.setStatus(e.getStatus());

		return new ResponseEntity<ProblemDetail>(pd, HttpStatus.valueOf(e.getStatus()));
	}
}
