package com.daw.contafin.completedExercise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompletedExerciseService {
	
	@Autowired
	CompletedExerciseRepository completedExerciseRepository;
	
	public void save(CompletedExercise completedExercise) {
		completedExerciseRepository.save(completedExercise);
	}
	
}