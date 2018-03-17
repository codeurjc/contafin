package com.daw.contafin.answer;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daw.contafin.exercise.Exercise;
import com.daw.contafin.lesson.Lesson;


public interface AnswerRepository extends JpaRepository <Answer, Long>{
	Answer findByExercise (Exercise exercise);
	Answer findById(long Id);
}
