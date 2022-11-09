package com.sp.studentapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sp.studentapp.entity.Classroom;

@Repository
public interface ClassroomRepository extends JpaRepository<Classroom, Integer> {

}
