package com.sp.studentapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sp.studentapp.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

}
