package com.sp.studentapp.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.sql.rowset.spi.SyncFactoryException;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sp.studentapp.entity.Classroom;
import com.sp.studentapp.entity.Student;
import com.sp.studentapp.exception.StudentNotFoundException;
import com.sp.studentapp.repository.ClassroomRepository;
import com.sp.studentapp.repository.StudentRepository;
import com.sp.studentapp.request.StudentRequest;
import com.sp.studentapp.response.StudentResponse;
import com.sp.studentapp.service.StudentService;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

	private final StudentRepository studentRepo;

	private final ClassroomRepository classRepo;
	
	@Override
	public List<StudentResponse> getAllStudents() {
		List<Student> students = studentRepo.findAll();
		List<StudentResponse> studentResponses = new ArrayList<>();
		
		students.stream()
			.forEach(student -> {
				StudentResponse studentRes = new StudentResponse();
				studentRes.setId(student.getId());
				studentRes.setName(student.getName());
				studentRes.setEmail(student.getEmail());
				studentRes.setPassword(student.getPassword());
				studentRes.setClassroom(student.getClassroom());
				studentResponses.add(studentRes);
			});
		return studentResponses;
	}

	@Override
	public StudentResponse getStudentById(Integer studentId) throws StudentNotFoundException {
		StudentResponse studentRes = new StudentResponse();
		Optional<Student> studentOpt = studentRepo.findById(studentId);
		if (studentOpt.isEmpty()) {
			throw new StudentNotFoundException("Student not found with id: " + studentId);
		}
		
		studentRes.setId(studentOpt.get().getId());
		studentRes.setName(studentOpt.get().getName());
		studentRes.setEmail(studentOpt.get().getEmail());
		studentRes.setPassword(studentOpt.get().getPassword());
		studentRes.setClassroom(studentOpt.get().getClassroom());
		return studentRes;
	}

	@Override
	public boolean createStudent(StudentRequest data) {
		Student student = new Student();
		student.setName(data.getName());
		student.setEmail(data.getEmail());
		student.setPassword(data.getPassword());
		student.setClassroom(classRepo.getById(data.getClassId()));
		studentRepo.save(student);
		return true;
	}

	@Override
	public StudentResponse updateStudentById(Integer studentId, StudentRequest data) throws StudentNotFoundException {
		Optional<Student> studentOpt = studentRepo.findById(studentId);
		if (studentOpt.isEmpty()) {
			throw new StudentNotFoundException("Student not found with id: " + studentId);
		}
		Student student = studentOpt.get();
		StudentResponse studentResponse = new StudentResponse();
		student.setName(data.getName());
		student.setEmail(data.getEmail());
		student.setPassword(data.getPassword());
		student.setClassroom(classRepo.getById(data.getClassId()));
		studentRepo.save(student);
		
		studentResponse.setId(student.getId());
		studentResponse.setName(student.getName());
		studentResponse.setEmail(student.getEmail());
		studentResponse.setPassword(student.getPassword());
		studentResponse.setClassroom(student.getClassroom());
		return studentResponse;
	}

	@Override
	public boolean deleteStudent(Integer studentId) throws StudentNotFoundException {
		Optional<Student> studentOpt = studentRepo.findById(studentId);
		if (studentOpt.isEmpty()) {
			throw new StudentNotFoundException("Student not found with id: " + studentId);
		}
		studentRepo.delete(studentOpt.get());
		return true;
	}

}
