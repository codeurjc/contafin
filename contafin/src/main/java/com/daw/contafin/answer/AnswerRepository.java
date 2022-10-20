package com.daw.contafin.answer;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daw.contafin.exercise.Exercise;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository <Answer, Long>{
	Answer findById(long Id);
}
