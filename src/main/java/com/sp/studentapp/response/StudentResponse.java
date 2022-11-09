package com.sp.studentapp.response;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sp.studentapp.entity.Classroom;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponse {
	private Integer id;
	private String name;
	private String email;
	@JsonIgnore
	private String password;
//	@JsonIgnore
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Classroom classroom;
}
