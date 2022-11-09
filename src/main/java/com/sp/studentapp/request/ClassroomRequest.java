package com.sp.studentapp.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClassroomRequest {

	@NotNull(message = "Class name must not be null")
	@NotBlank(message = "Class name must not be blank")
	private String classname;
}
