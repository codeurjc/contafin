package com.daw.contafin.exercise;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.daw.contafin.lesson.Lesson;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseRepository extends JpaRepository <Exercise, Long>{
	Exercise findById(long id);
	List<Exercise> findAll();
	Page <Exercise> findAll(Pageable page);
}
