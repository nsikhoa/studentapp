package com.sp.studentapp.service;

import java.util.List;

import com.sp.studentapp.exception.StudentNotFoundException;
import com.sp.studentapp.request.StudentRequest;
import com.sp.studentapp.response.StudentResponse;

public interface StudentService {
	public List<StudentResponse> getAllStudents();
	
	public StudentResponse getStudentById(Integer studentId) throws StudentNotFoundException;
	
	public boolean createStudent(StudentRequest data);
	
	public StudentResponse updateStudentById(Integer studentId, StudentRequest data) throws StudentNotFoundException;
	
	public boolean deleteStudent(Integer studentId) throws StudentNotFoundException;
}
