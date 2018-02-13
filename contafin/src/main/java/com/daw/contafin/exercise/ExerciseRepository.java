package com.daw.contafin.exercise;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daw.contafin.student.Student;


public interface ExerciseRepository extends JpaRepository <Exercise, Long>{
	Exercise findById(long id);
}
