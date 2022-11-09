package com.sp.studentapp;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AppError {
	private String errorCode;
	private String message;
	private HttpStatus httpStatus;
}
