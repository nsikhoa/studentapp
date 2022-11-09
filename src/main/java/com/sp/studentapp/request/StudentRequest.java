package com.sp.studentapp.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentRequest {
	@NotNull(message = "Name could not be null")
	@NotBlank(message = "Name could not be blank")
	@Pattern(regexp = "^[a-zA-Z\s]*$", message = "Name must contain letters and space only")
	private String name;
	
	@NotNull(message = "Email could not be null")
	@Email(message = "Invalid email")
	private String email;
	
	@NotNull(message = "Password could not be null")
	@Pattern(regexp = ".{8,}", message = "Password must have length at least 8")
	private String password;
	private Integer classId;
}
