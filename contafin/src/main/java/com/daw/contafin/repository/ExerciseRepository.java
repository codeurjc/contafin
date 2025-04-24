package com.daw.contafin.repository;

import java.util.List;

import com.daw.contafin.entity.Exercise;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseRepository extends CrudRepository<Exercise, Long> {
	Exercise findById(long id);
	List<Exercise> findAll();
}
