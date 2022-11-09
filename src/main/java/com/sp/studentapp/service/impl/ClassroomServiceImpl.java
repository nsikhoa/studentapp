package com.sp.studentapp.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sp.studentapp.entity.Classroom;
import com.sp.studentapp.exception.ClassroomNotFoundException;
import com.sp.studentapp.repository.ClassroomRepository;
import com.sp.studentapp.request.ClassroomRequest;
import com.sp.studentapp.response.ClassroomResponse;
import com.sp.studentapp.service.ClassroomService;

@Service
public class ClassroomServiceImpl implements ClassroomService{

	@Autowired
	private ClassroomRepository classRepo;
	
	@Override
	public List<ClassroomResponse> getAllClass() {
		List<Classroom> classrooms = classRepo.findAll();
		List<ClassroomResponse> classResponses = new ArrayList<>();
		
		classrooms.stream()
			.forEach(classroom -> {
				ClassroomResponse classRes = new ClassroomResponse();
				classRes.setId(classroom.getId());
				classRes.setClassname(classroom.getClassname());
				classResponses.add(classRes);
			});
		return classResponses;
	}

	@Override
	public ClassroomResponse getClassById(Integer classId) throws ClassroomNotFoundException {
		ClassroomResponse classRes = new ClassroomResponse();
		Optional<Classroom> classroomOpt = classRepo.findById(classId);
		if (classroomOpt.isEmpty()) {
			throw new ClassroomNotFoundException("Not found classroom with id: " + classId);
		}
		classRes.setId(classroomOpt.get().getId());
		classRes.setClassname(classroomOpt.get().getClassname());
		return classRes;
	}

	@Override
	public boolean createClassroom(ClassroomRequest classroomRequest) {
		Classroom classroom = new Classroom();
		classroom.setClassname(classroomRequest.getClassname());
		classRepo.save(classroom);
		
		return true;
	}

	@Override
	public boolean deleteClassroom(Integer classId) throws ClassroomNotFoundException {
		Optional<Classroom> classroomOpt = classRepo.findById(classId);
		if (classroomOpt.isEmpty()) {
			throw new ClassroomNotFoundException("Not found classroom with id: " + classId);
		}
		classRepo.delete(classroomOpt.get());
		return false;
	}

}
