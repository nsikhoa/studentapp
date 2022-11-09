package com.sp.studentapp.advice;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.sp.studentapp.AppError;
import com.sp.studentapp.exception.ClassroomNotFoundException;

@RestControllerAdvice
public class ClassroomExceptionHandler {
	@ExceptionHandler(ClassroomNotFoundException.class)
	public ResponseEntity<AppError> handleException(ClassroomNotFoundException ex) {
		AppError appError = new AppError(
				UUID.randomUUID().toString(),
				ex.getMessage(),
				HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<>(appError, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
