package com.sp.studentapp.service;

import java.util.List;

import com.sp.studentapp.exception.NotFoundException;
import com.sp.studentapp.request.TeacherRequest;
import com.sp.studentapp.response.StudentResponse;
import com.sp.studentapp.response.TeacherResponse;

public interface TeacherService {
	public List<TeacherResponse> getAllTeacher();
	
	public TeacherResponse getTeacherById(Integer teacherId) throws NotFoundException;
	
	public boolean createTeacher(TeacherRequest data);

	public TeacherResponse updateTeacher(TeacherRequest data, Integer teacherId) throws NotFoundException;
}
