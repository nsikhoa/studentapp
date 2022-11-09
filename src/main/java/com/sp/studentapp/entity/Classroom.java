package com.sp.studentapp.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Classroom {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String classname;
	
	@JsonBackReference
	@OneToMany(mappedBy = "classroom", cascade = CascadeType.ALL)
	private List<Student> student;
	
	@JsonBackReference
	@ManyToMany(mappedBy = "classrooms", fetch = FetchType.LAZY)
	private List<Teacher> teachers;
}
