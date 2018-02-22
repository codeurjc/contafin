package com.daw.contafin.completedExercise;


import org.springframework.data.jpa.repository.JpaRepository;

import com.daw.contafin.exercise.Exercise;
import com.daw.contafin.user.User;


public interface CompletedExerciseRepository extends JpaRepository <CompletedExercise, Long>{
	//CompletedExercise findByUserAndExercise(User user, Exercise exercise);
}
