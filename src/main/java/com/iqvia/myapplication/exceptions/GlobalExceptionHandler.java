package com.iqvia.myapplication.exceptions;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request){
		ErrorDetails errorDetails = new ErrorDetails(new Date(),ex.getMessage(),request.getDescription(false));
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
	}
	
	@ExceptionHandler(UserAlreadyPresentException.class)
	public ResponseEntity<?> UserAlreadyPresentException(UserAlreadyPresentException ex,WebRequest request ){
		ErrorDetails errorDetails = new ErrorDetails(new Date(),ex.getMessage(),request.getDescription(false));
		return ResponseEntity.status(HttpStatus.OK).body(errorDetails);
	}
	
	@ExceptionHandler(org.springframework.dao.EmptyResultDataAccessException.class)
	public ResponseEntity<?> EmptyResultDataAccessException(org.springframework.dao.EmptyResultDataAccessException ex,WebRequest request ){
		ErrorDetails errorDetails = new ErrorDetails(new Date(),"User does not exist",request.getDescription(false));
		return ResponseEntity.status(HttpStatus.OK).body(errorDetails);
	}
	
	 @Override
	   protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
	                 HttpHeaders headers, HttpStatus status, WebRequest request) {
//	          String errorMessage = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
	          List<String> validationList = ex.getBindingResult().getFieldErrors().stream().map(fieldError->fieldError.getDefaultMessage()).collect(Collectors.toList());
	          Collections.reverse(validationList);
	          ErrorDetails errorDetails = new ErrorDetails(new Date(),"Input Validation Errors",request.getDescription(false));
	          errorDetails.setErrors(validationList);
	          return ResponseEntity.status(status).body(errorDetails);
	   }
	 
//	 @Override
//	 protected ResponseEntity<Object> handleHttpMessageNotReadable(
//				HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//		 
//         ErrorDetails errorDetails = new ErrorDetails(new Date(),"please enter true or false as value",request.getDescription(false));
//         return ResponseEntity.status(status).body(errorDetails);
//		}
}
