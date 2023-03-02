package com.daw.contafin.completedExercise;


import java.util.ArrayList;
import java.util.List;

import com.daw.contafin.answer.AnswerDto;
import com.daw.contafin.completedLesson.CompletedLesson;
import com.daw.contafin.completedLesson.CompletedLessonMapper;
import com.daw.contafin.completedLesson.CompletedLessonService;
import com.daw.contafin.exercise.ExerciseDto;
import com.daw.contafin.exercise.ExerciseMapper;
import com.daw.contafin.lesson.LessonDto;
import com.daw.contafin.user.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw.contafin.exercise.Exercise;
import com.daw.contafin.exercise.ExerciseService;
import com.daw.contafin.lesson.Lesson;
import com.daw.contafin.lesson.LessonService;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
@Slf4j
@Transactional
public class CompletedExerciseService {
	
	@Autowired
	CompletedExerciseRepository completedExerciseRepository;
	
	@Autowired
	LessonService lessonService;
	
	@Autowired
	ExerciseService exerciseService;

	@Resource
	CompletedExerciseMapper completedExerciseMapper;

	@Resource
	UserMapper userMapper;

	@Resource
	ExerciseMapper exerciseMapper;

	@Autowired
	UserComponent userComponent;

	@Autowired
	UserService userService;

	@Autowired
	CompletedLessonService completedLessonService;

	
	public CompletedExerciseDto save(CompletedExerciseDto completedExerciseDto) {
		log.info("Guardado del ejercicio completo: {}", completedExerciseDto);
		try{
			CompletedExercise completedExercise = completedExerciseMapper.CompletedExerciseDtoToCompletedExercise(completedExerciseDto);
			completedExerciseRepository.save(completedExercise);
		}catch (Exception e){
			log.warn("Error al guardar el ejercicio completo");
			completedExerciseDto = null;
		}
			return completedExerciseDto;
	}
	/*public CompletedExerciseDto findByUserAndExercise(UserDto userDto, ExerciseDto exerciseDto){
		log.info("Busqueda de ejercicio completo usando el usuario y un ejercicio");
			CompletedExerciseDto completedExerciseDto;
		try{
			User user = userMapper.UserDtoToUser(userDto);
			Exercise exercise = exerciseMapper.ExerciseDtoToExercise(exerciseDto);
			CompletedExercise completedExercise = completedExerciseRepository.findByUserAndExercise(user,exercise);
			completedExerciseDto = completedExerciseMapper.CompletedExerciseToCompletedExerciseDto(completedExercise);
		}catch (Exception e){
			log.info("Error al buscar el ejercicio completo");
			completedExerciseDto = null;
		}
		return completedExerciseDto;
	}
	
	public List<CompletedExerciseDto> findByUser(UserDto userDto){
		log.info("Busqueda de ejercicio completo usando el usuario: {}", userDto);
		List<CompletedExerciseDto> completedExerciseDtos;
		try{
			User user = userMapper.UserDtoToUser(userDto);
			List<CompletedExercise> completedExercises = completedExerciseRepository.findByUser(user);
			completedExerciseDtos = completedExerciseMapper.CompletedExercisesToCompletedExercisesDto(completedExercises);
		}catch (Exception e){
			log.info("Error al buscar el ejercicio completo");
			completedExerciseDtos = null;
		}
		return completedExerciseDtos;
	}*/

	/*public void delete(CompletedExerciseDto completedExerciseDto) {
		log.info("Borrado del ejercicio completo: {}", completedExerciseDto);
		try{
			CompletedExercise completedExercise = completedExerciseMapper.CompletedExerciseDtoToCompletedExercise(completedExerciseDto);
			completedExerciseRepository.delete(completedExercise);
		}catch (Exception e){
			log.info("Error al borrar el ejercicio completo");
		}
	}
	public void delete(long Id) {
		log.info("Borrado del ejercicio completo por el id: {}", Id);
		try{
			completedExerciseRepository.deleteById(Id);
		}catch (Exception e){
			log.info("Error al borrar el ejercicio completo");
		}
	}*/
	//Get all ExerciseCompleted in the lesson and delete them (need to put wrong exercise last)
	public int numExercisesCompleted(long idlesson, UserDto userDto) {
		log.info("Calcular el número de ejercicios completados");
		int numExercisesCompleted = 0;
		try{
			User user = userMapper.UserDtoToUser(userDto);
			LessonDto lessonDto = lessonService.findById(idlesson);
			List<ExerciseDto> exerciseDtoList = lessonDto.getExercises();
			for (ExerciseDto exerciseDto : exerciseDtoList) {
				Exercise exercise = exerciseMapper.ExerciseDtoToExercise(exerciseDto);
				CompletedExercise completedExerciseS = completedExerciseRepository.findByUserAndExercise(user,exercise);
				if (completedExerciseS != null) {
					numExercisesCompleted++;
					completedExerciseRepository.delete(completedExerciseS);
				}
			}
		}catch (Exception e){
			log.warn("Error al calcular el número de ejercicios completados");
			numExercisesCompleted = 0;
		}
		return  numExercisesCompleted;
	}

	/*public void deleteAll(UserDto userDto) {
		log.info("Borrado de todos los ejercicios completados de un usuario");
		try{
			User user =userMapper.UserDtoToUser(userDto);
			List<CompletedExercise> completedExercises =  completedExerciseRepository.findByUser(user);
			List<CompletedExerciseDto> completedExerciseDtos = completedExerciseMapper.CompletedExercisesToCompletedExercisesDto(completedExercises);
			if (completedExerciseDtos != null) {
				CompletedExercise completedExercise;
				for (CompletedExerciseDto completedExerciseDto : completedExerciseDtos) {
					completedExercise = completedExerciseMapper.CompletedExerciseDtoToCompletedExercise(completedExerciseDto);
					if (completedExercise != null) {
						completedExerciseRepository.delete(completedExercise);
					}
				}
			}
		}catch (Exception e){
			log.info("Error al borrar los ejercicios completados de un usuario");
		}
	}*/

	public Boolean checkAnswer ( Long id, AnswerDto answerAct){
		log.info("Comprobar la solucion del ejercicio con id: {}", id);
		boolean goodanswer = false;
		try{
			UserDto user = userComponent.getLoggedUser();
			ExerciseDto exercise = exerciseService.findById(id);

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

				if(goodanswer) {
					save(new CompletedExerciseDto(user, exercise, 0));
					if (userComponent.isLoggedUser()) {
						user.updatePoints(user, 3);
						userService.save(user);
					}
				}else {
					if (userComponent.isLoggedUser()) {
						user.updatePoints(user, -3);
						userService.save(user);
					}
				}
			}
		}catch (Exception e){
			log.warn("Error comprobar la solucion del ejercicio");
			goodanswer = false;
		}
		return goodanswer;
	}

	public Boolean checkLessonComplete(Long idLesson){
		log.info("Se va a comprobar si la leccion esta completa");
		Boolean b;
		try{
			UserDto user = userComponent.getLoggedUser();
			LessonDto lessonDto = lessonService.findById(idLesson);
			b = completedLessonService.existCompletedLesson(user, lessonDto);
			if(!b){
				int numExercisesCompleted = numExercisesCompleted(idLesson, user);
				if(numExercisesCompleted == lessonDto.getExercises().size()){
					userService.completedLesson(user, idLesson);
					b = true;
				}
			}
		}catch (Exception e){
			log.warn("Error al comprobar si la leccion esta completa");
			b = null;
		}
		return b;
	}
}