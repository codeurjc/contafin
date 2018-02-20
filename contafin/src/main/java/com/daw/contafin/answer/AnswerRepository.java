package com.daw.contafin.answer;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daw.contafin.exercise.Exercise;


public interface AnswerRepository extends JpaRepository <Answer, Long>{
	Answer findByExercise (Exercise exercise);
}
