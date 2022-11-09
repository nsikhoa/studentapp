package com.sp.studentapp.advice;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.sp.studentapp.AppError;
import com.sp.studentapp.exception.StudentNotFoundException;

@RestControllerAdvice
public class StudentExceptionHandler {
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException ex) {
		Map<String, String> errorMap = new HashMap<String, String>();
		ex.getBindingResult().getFieldErrors().forEach(error -> {
			errorMap.put(error.getField(), error.getDefaultMessage());
		});
		
		return errorMap;
	}
	
	@ExceptionHandler(StudentNotFoundException.class)
	public ResponseEntity<AppError> handleException(StudentNotFoundException ex) {
		AppError appError = new AppError(
				UUID.randomUUID().toString(),
				ex.getMessage(),
				HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<>(appError, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
