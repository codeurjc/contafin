package com.daw.contafin.answer;

import com.daw.contafin.exercise.ExerciseDto;
import com.daw.contafin.exercise.ExerciseMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw.contafin.exercise.Exercise;

import javax.transaction.Transactional;

@Service
@Slf4j
@Transactional
public class AnswerService {
	
	@Autowired
	AnswerRepository answerRepository;

	@Autowired
	AnswerMapper answerMapper;

	@Autowired
	ExerciseMapper exerciseMapper;
	
	public AnswerDto findById (long id) {
		AnswerDto answerDto = new AnswerDto();
		try{
			Answer answer = answerRepository.findById(id);
			answerDto = answerMapper.AnswerToAnswerDto(answer);
		}catch (Exception e){
			answerDto = null;
		}
		return answerDto;
	}


	public AnswerDto findByExercise (ExerciseDto exerciseDto) {
		AnswerDto answerDto = new AnswerDto();
		try{
			Exercise exercise = exerciseMapper.ExerciseDtoToExercise(exerciseDto);
			Answer answer = answerRepository.findByExercise(exercise);
			answerDto = answerMapper.AnswerToAnswerDto(answer);
		}catch (Exception e){
			answerDto = null;
		}
		return answerDto;
	}
	public AnswerDto save(AnswerDto answerDto) {
		try{
			Answer answer = answerMapper.AnswerDtoToAnswer(answerDto);
			answerRepository.save(answer);
		}catch (Exception e){
			answerDto = null;
		}
		return answerDto;
	}
	public void delete(AnswerDto answerDto) {
		try{
			Answer answer = answerMapper.AnswerDtoToAnswer(answerDto);
			answerRepository.delete(answer);
		}catch (Exception e){
		}
	}
}
