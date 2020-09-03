package com.test.wns.exception;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * This is custom exeption handler in case any execption occurs during execution
 * 
 * @author charusingla
 *
 */
@ControllerAdvice
public class CustomExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {
		String exceptionResponse = new String(ex.getMessage());
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(CsvDataTypeMismatchException.class)
	public final ResponseEntity<Object> handleCsvDataTypeMismatchException(CsvDataTypeMismatchException ex, WebRequest request) {
		String exceptionResponse = new String("CsvDataTypeMismatchException execption "+ ex.getMessage());
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	

	@ExceptionHandler(CsvRequiredFieldEmptyException.class)
	public final ResponseEntity<Object> handleCsvRequiredFieldEmptyException(CsvRequiredFieldEmptyException ex, WebRequest request) {
		String exceptionResponse = new String("CsvRequiredFieldEmptyException execption "+ ex.getMessage());
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
