package com.sp.studentapp.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.sp.studentapp.entity.Classroom;
import com.sp.studentapp.entity.Student;
import com.sp.studentapp.entity.Teacher;
import com.sp.studentapp.exception.NotFoundException;
import com.sp.studentapp.exception.StudentNotFoundException;
import com.sp.studentapp.repository.ClassroomRepository;
import com.sp.studentapp.repository.TeacherRepository;
import com.sp.studentapp.request.TeacherRequest;
import com.sp.studentapp.response.TeacherResponse;
import com.sp.studentapp.service.TeacherService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService{

	private final TeacherRepository teacherRepository;
	
	private final ClassroomRepository classroomRepository;
	
	@Override
	public List<TeacherResponse> getAllTeacher() {
		List<Teacher> teachers = teacherRepository.findAll();
		List<TeacherResponse> teacherResponse = new ArrayList<>();
		
		teachers.stream().forEach(teacher -> {
			TeacherResponse teacherRes = new TeacherResponse();
			teacherRes.setId(teacher.getId());
			teacherRes.setName(teacher.getName());
			teacherRes.setEmail(teacher.getEmail());
			teacherRes.setPassword(teacher.getPassword());
			teacherRes.setClassrooms(teacher.getClassrooms());
			
			teacherResponse.add(teacherRes);
		});
		return teacherResponse;
	}

	@Override
	public TeacherResponse getTeacherById(Integer teacherId) throws NotFoundException {
		TeacherResponse teacherRes = new TeacherResponse();
		Optional<Teacher> teacherOpt = teacherRepository.findById(teacherId);
		if (teacherOpt.isEmpty()) {
			throw new NotFoundException("Teacher not found with id: " + teacherId);
		}
		Teacher teacher = teacherOpt.get();
		
		teacherRes.setId(teacher.getId());
		teacherRes.setName(teacher.getName());
		teacherRes.setEmail(teacher.getEmail());
		teacherRes.setPassword(teacher.getPassword());
		teacherRes.setClassrooms(teacher.getClassrooms());
		
		return teacherRes;
	}

	@Override
	public boolean createTeacher(TeacherRequest data) {
		Teacher teacher = new Teacher();
		List<Classroom> classrooms = classroomRepository.findAllById(data.getClassIds());
		teacher.setName(data.getName());
		teacher.setEmail(data.getEmail());
		teacher.setPassword(data.getPassword());
		teacher.setClassrooms(classrooms);
		teacherRepository.save(teacher);
		return true;
	}

	@Override
	public TeacherResponse updateTeacher(TeacherRequest data, Integer teacherId) throws NotFoundException {

		Optional<Teacher> teacherOpt = teacherRepository.findById(teacherId);
		if (teacherOpt.isEmpty()) {
			throw new NotFoundException("Teacher not found with id: " + teacherId);
		}
		Teacher teacher = teacherOpt.get();
		List<Classroom> classrooms = classroomRepository.findAllById(data.getClassIds());
		teacher.setName(data.getName());
		teacher.setEmail(data.getEmail());
		teacher.setPassword(data.getPassword());
		teacher.setClassrooms(classrooms);
		teacherRepository.save(teacher);

		TeacherResponse teacherResponse = new TeacherResponse();
		teacherResponse.setId(teacher.getId());
		teacherResponse.setName(teacher.getName());
		teacherResponse.setEmail(teacher.getEmail());
		teacherResponse.setPassword(teacher.getPassword());
		teacherResponse.setClassrooms(teacher.getClassrooms());
		return teacherResponse;
	}

}
