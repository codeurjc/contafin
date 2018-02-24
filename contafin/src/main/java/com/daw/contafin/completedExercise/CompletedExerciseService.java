package com.daw.contafin.completedExercise;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw.contafin.exercise.Exercise;
import com.daw.contafin.user.User;

@Service
public class CompletedExerciseService {
	
	@Autowired
	CompletedExerciseRepository completedExerciseRepository;
	
	public void save(CompletedExercise completedExercise) {
		completedExerciseRepository.save(completedExercise);
	}
	public CompletedExercise findByUserAndExercise(User user, Exercise exercise){
		return completedExerciseRepository.findByUserAndExercise(user,exercise);
	}
	
}