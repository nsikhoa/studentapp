package com.sp.studentapp.advice;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.sp.studentapp.AppError;
import com.sp.studentapp.exception.NotFoundException;

@RestControllerAdvice
public class AppExceptionHandler {
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<AppError> handleException(NotFoundException ex) {
		AppError appError = new AppError(
				UUID.randomUUID().toString(),
				ex.getMessage(),
				HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<>(appError, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
