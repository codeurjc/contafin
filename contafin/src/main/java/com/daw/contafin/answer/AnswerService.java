package com.daw.contafin.answer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw.contafin.exercise.Exercise;

@Service
public class AnswerService {
	
	@Autowired
	AnswerRepository answerRepository;
	
	public Answer findByExercise (Exercise exercise) {
		return answerRepository.findByExercise(exercise);
	}
}
