package com.daw.contafin.service;

import java.util.List;

import com.daw.contafin.dto.AnswerDto;
import com.daw.contafin.dto.ExerciseDto;
import com.daw.contafin.entity.Exercise;
import com.daw.contafin.repository.ExerciseRepository;
import com.daw.contafin.mapper.ExerciseMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
@Slf4j
@Transactional
public class ExerciseService {


	@Autowired
	ExerciseRepository exerciseRepository;

	@Resource
	ExerciseMapper exerciseMapper;


	public ExerciseDto findById (long id) {
		log.info("Busqueda de un ejercicio por el id: {} ", id);
		ExerciseDto exerciseDto;
		try{
			Exercise exercise = exerciseRepository.findById(id);
			exerciseDto = exerciseMapper.ExerciseToExerciseDto(exercise);
		}catch (Exception e) {
			log.warn("Error al buscar ejercicio por  id");
			exerciseDto = null;
		}
		return exerciseDto;
	}

	public List<ExerciseDto> findAll(){
		log.info("Busqueda de todos los ejercicios");
		List<ExerciseDto> exercisesDto;
		try{
			List<Exercise> exercises = exerciseRepository.findAll();
			exercisesDto = exerciseMapper.ExercisesToExercisesDto(exercises);
		}catch (Exception e){
			log.warn("Error al buscar los ejercicios");
			exercisesDto = null;
		}
		return exercisesDto;
	}

	public void save(ExerciseDto exerciseDto) {
		log.info("Guardado del ejercicio: {}", exerciseDto);
		try{
			Exercise exercise = exerciseMapper.ExerciseDtoToExercise(exerciseDto);
			exercise = exerciseRepository.save(exercise);
			exerciseMapper.ExerciseToExerciseDto(exercise);
		}catch (Exception e){
			log.warn("Error al guardar el ejercicio");
		}
	}

	public void delete(long id){
		log.info("Eliminar el ejercicio con id: {}", id);
		try{
			exerciseRepository.deleteById(id);
		}catch (Exception e){
			log.warn("Error al eliminar la ejercicio");
		}
	}

	public Boolean checkAnswer ( Long id, AnswerDto answerAct){
		log.info("Comprobar la solucion del ejercicio con id: {}", id);
		boolean goodanswer = false;
		try{
			ExerciseDto exercise = findById(id);

			if (exercise != null) {
				AnswerDto answer = exercise.getAnswer();
				if (exercise.getKind() == 2) {
					String[] answergood = answer.getResult().split("\\.");
					String[] myanswer = answerAct.getResult().split("\\.");
					goodanswer = false;

					for (int i = 0; i < answergood.length; i++) {
						for (int j = 0; j < myanswer.length; j++) {
							if (answergood[i].equals(myanswer[j])) {
								goodanswer = true;
							}
						}
					}
				}else{
					goodanswer = answer.getResult().equals(answerAct.getResult());
				}
			}
		}catch (Exception e){
			log.warn("Error comprobar la solucion del ejercicio");
			goodanswer = false;
		}
		return goodanswer;
	}
}
