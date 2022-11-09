package com.sp.studentapp.service;

import java.util.List;

import com.sp.studentapp.exception.ClassroomNotFoundException;
import com.sp.studentapp.request.ClassroomRequest;
import com.sp.studentapp.response.ClassroomResponse;

public interface ClassroomService {
	public List<ClassroomResponse> getAllClass();
	
	public ClassroomResponse getClassById(Integer classId) throws ClassroomNotFoundException;
	
	public boolean createClassroom(ClassroomRequest classroomRequest);
	
	public boolean deleteClassroom(Integer classId) throws ClassroomNotFoundException;
}
