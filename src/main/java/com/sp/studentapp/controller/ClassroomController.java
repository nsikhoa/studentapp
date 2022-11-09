package com.sp.studentapp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sp.studentapp.exception.ClassroomNotFoundException;
import com.sp.studentapp.request.ClassroomRequest;
import com.sp.studentapp.response.ClassroomResponse;
import com.sp.studentapp.response.Response;
import com.sp.studentapp.service.ClassroomService;

@RestController
@RequestMapping("/api/classroom")
public class ClassroomController {
	
	@Autowired
	private ClassroomService classService;
	
	@GetMapping("")
	public ResponseEntity<Response<ClassroomResponse>> getAllClassroom() {
		return ResponseEntity.ok().body(new Response<ClassroomResponse>("ok", "Success", null, classService.getAllClass()));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Response<ClassroomResponse>> getClassroom(@PathVariable(name = "id") Integer classId) throws ClassroomNotFoundException {
		return ResponseEntity.ok().body(new Response<ClassroomResponse>("ok", "Success", classService.getClassById(classId), null));
	}
	
	@PostMapping("")
	public ResponseEntity<?> createClassroom(@RequestBody @Valid ClassroomRequest classReq ) {
		boolean isCreated = classService.createClassroom(classReq);
		if(isCreated) {
			return ResponseEntity.created(null).body(new Response<ClassroomResponse>("ok", "Create success"));
		}
		return ResponseEntity.badRequest().body(new Response<ClassroomResponse>("error", "Create failed"));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Response<ClassroomResponse>> deleteClassroom(@PathVariable(name = "id") Integer classId) throws ClassroomNotFoundException {
		boolean isDeleted = classService.deleteClassroom(classId);
		if (isDeleted) {
			return ResponseEntity.ok().body(new Response<ClassroomResponse>("ok", "Delete success"));
		}
		return ResponseEntity.badRequest().body(new Response<ClassroomResponse>("error", "Delete failed!"));
	}
}
