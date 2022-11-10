package com.sp.studentapp.controller;

import java.util.List;

import javax.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sp.studentapp.exception.StudentNotFoundException;
import com.sp.studentapp.request.StudentRequest;
import com.sp.studentapp.response.Response;
import com.sp.studentapp.response.StudentResponse;
import com.sp.studentapp.service.StudentService;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {
	
	private final StudentService studentService;
	
	@GetMapping("")
	public ResponseEntity<Response<StudentResponse>> getAllStudents() {
		List<StudentResponse> students = studentService.getAllStudents();
		
		return ResponseEntity.ok().body(new Response<>("ok", "Success", null, students));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Response<StudentResponse>> getStudentById(
			@PathVariable(name = "id") Integer studentId) throws StudentNotFoundException {
		var student = studentService.getStudentById(studentId);
		
		return ResponseEntity.ok().body(new Response<>("ok", "Success", student, null));
	}
	
	@PostMapping("")
	public ResponseEntity<?> createStudent(@RequestBody @Valid StudentRequest data) {
		boolean isCreated = studentService.createStudent(data);
		if (isCreated) {
			return ResponseEntity.created(null).body(new Response<>("ok", "Create success"));
		}
		return ResponseEntity.badRequest().body(new Response<>("error", "Create failed!"));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Response<StudentResponse>> updateStudentById(
			@PathVariable(name = "id") Integer studentId,
			@RequestBody @Valid StudentRequest data) throws StudentNotFoundException{
		return ResponseEntity.ok().body(new Response<>("ok", "Update success", studentService.updateStudentById(studentId, data), null));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteStudentById(@PathVariable(name = "id") Integer studentId) throws StudentNotFoundException {
		boolean isDeleted = studentService.deleteStudent(studentId);
		if (isDeleted) {
			return ResponseEntity.ok().body(new Response<>("ok", "Delete success"));
		}
		return ResponseEntity.badRequest().body(new Response<>("error", "Delete failed!"));
	}
}
