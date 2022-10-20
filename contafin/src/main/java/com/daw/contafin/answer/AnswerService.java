package com.daw.contafin.answer;

import com.daw.contafin.exercise.ExerciseDto;
import com.daw.contafin.exercise.ExerciseMapper;
import com.daw.contafin.exercise.ExerciseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw.contafin.exercise.Exercise;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
@Slf4j
@Transactional
public class AnswerService {
	
	@Autowired
	AnswerRepository answerRepository;

	@Autowired
	ExerciseRepository exerciseRepository;

	@Resource
	AnswerMapper answerMapper;

	@Resource
	ExerciseMapper exerciseMapper;
	
	public AnswerDto findById (long id) {
		log.info("Busqueda de una respuesta por el id: {} ", id);
		AnswerDto answerDto;
		try{
			Answer answer = answerRepository.findById(id);
			answerDto = answerMapper.AnswerToAnswerDto(answer);
		}catch (Exception e){
			log.info("Error al buscar la respuesta por  id");
			answerDto = null;
		}
		return answerDto;
	}


	public AnswerDto save(AnswerDto answerDto) {
		log.info("Guardado de la repuesta: {}", answerDto);
		try{
			Answer answer = answerMapper.AnswerDtoToAnswer(answerDto);
			answerRepository.save(answer);
		}catch (Exception e){
			log.info("Error al guardar la repuesta");
			answerDto = null;
		}
		return answerDto;
	}
	public void delete(AnswerDto answerDto) {
		log.info("Borrado de la repuesta: {}", answerDto);
		try{
			Answer answer = answerMapper.AnswerDtoToAnswer(answerDto);
			answerRepository.delete(answer);
		}catch (Exception e){
			log.info("Error al borrar la repuesta");
		}
	}
}
