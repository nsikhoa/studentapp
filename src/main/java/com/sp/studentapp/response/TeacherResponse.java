package com.sp.studentapp.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sp.studentapp.entity.Classroom;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeacherResponse {

	private Integer id;
	
	private String name;
	
	private String email;
	
	@JsonIgnore
	private String password;
	
	private List<Classroom> classrooms;
}
