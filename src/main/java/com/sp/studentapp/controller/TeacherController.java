package com.sp.studentapp.controller;

import java.util.List;

import javax.validation.Valid;

import com.sp.studentapp.entity.Teacher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sp.studentapp.exception.NotFoundException;
import com.sp.studentapp.request.TeacherRequest;
import com.sp.studentapp.response.Response;
import com.sp.studentapp.response.TeacherResponse;
import com.sp.studentapp.service.TeacherService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/teachers")
@RequiredArgsConstructor
public class TeacherController {
	private final TeacherService teacherService;
	
	@GetMapping("")
	public ResponseEntity<Response<TeacherResponse>> getAllTeachers() {
		List<TeacherResponse> teachers = teacherService.getAllTeacher();
		
		return ResponseEntity.ok().body(new Response<TeacherResponse>("ok", "Success", null, teachers)); 
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Response<TeacherResponse>> getTeacherById(
			@PathVariable(name = "id") Integer teacherId) throws NotFoundException {
		var teacher = teacherService.getTeacherById(teacherId);
		
		return ResponseEntity.ok().body(new Response<TeacherResponse>("ok", "Success", teacher, null));
	}
	
	@PostMapping("")
	public ResponseEntity<?> createTeacher(@RequestBody @Valid TeacherRequest data) {
		boolean isCreated = teacherService.createTeacher(data);
		if (isCreated) {
			return ResponseEntity.created(null).body(new Response<>("ok", "Create success"));
		}
		return ResponseEntity.badRequest().body(new Response<>("error", "Create failed!"));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Response<TeacherResponse>> updateTeacher(
			@RequestBody @Valid TeacherRequest data,
			@PathVariable(name = "id") Integer teacherId) throws NotFoundException {

		var teacher = teacherService.updateTeacher(data, teacherId);

		return ResponseEntity.ok().body(new Response<>("ok", "Update success", teacher, null));
	}
}
