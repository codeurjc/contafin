package com.daw.contafin.completedExercise;


import java.util.List;

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
	
	public List<CompletedExercise> findByUser(User user){
		return completedExerciseRepository.findByUser(user);
	}
	
	public void delete(CompletedExercise completedExercise) {
		completedExerciseRepository.delete(completedExercise);
	}
	public void delete(long Id) {
		completedExerciseRepository.delete(Id);
	}
}