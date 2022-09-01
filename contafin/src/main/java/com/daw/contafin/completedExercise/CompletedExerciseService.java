package com.daw.contafin.completedExercise;


import java.util.ArrayList;
import java.util.List;

import com.daw.contafin.completedLesson.CompletedLesson;
import com.daw.contafin.completedLesson.CompletedLessonMapper;
import com.daw.contafin.exercise.ExerciseDto;
import com.daw.contafin.exercise.ExerciseMapper;
import com.daw.contafin.lesson.LessonDto;
import com.daw.contafin.user.UserDto;
import com.daw.contafin.user.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw.contafin.exercise.Exercise;
import com.daw.contafin.exercise.ExerciseService;
import com.daw.contafin.lesson.Lesson;
import com.daw.contafin.lesson.LessonService;
import com.daw.contafin.user.User;

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


	
	public CompletedExerciseDto save(CompletedExerciseDto completedExerciseDto) {
		log.info("Guardado del ejercicio completo: {}", completedExerciseDto);
		try{
			CompletedExercise completedExercise = completedExerciseMapper.CompletedExerciseDtoToCompletedExercise(completedExerciseDto);
			completedExerciseRepository.save(completedExercise);
		}catch (Exception e){
			log.info("Error al guardar el ejercicio completo");
			completedExerciseDto = null;
		}
			return completedExerciseDto;
	}
	public CompletedExerciseDto findByUserAndExercise(UserDto userDto, ExerciseDto exerciseDto){
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
	}

	public void delete(CompletedExerciseDto completedExerciseDto) {
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
	}
	//Get all ExerciseCompleted in the lesson and delete them (need to put wrong exercise last)
	public int numExercisesCompleted(long idlesson, int idunit, UserDto userDto) {
		log.info("Calcular el número de ejercicios completados");
		int numExercisesCompleted = 0;
		try{
			User user = userMapper.UserDtoToUser(userDto);
			LessonDto lessonDto = lessonService.findById(idlesson + (3 * (idunit - 1)));
			List<ExerciseDto> exerciseDtoList = exerciseService.findByLesson(lessonDto);
			for (ExerciseDto exerciseDto : exerciseDtoList) {
				Exercise exercise = exerciseMapper.ExerciseDtoToExercise(exerciseDto);
				CompletedExercise completedExerciseS = completedExerciseRepository.findByUserAndExercise(user,exercise);
				if (completedExerciseS != null) {
					numExercisesCompleted++;
					completedExerciseRepository.delete(completedExerciseS);
				}
			}
		}catch (Exception e){
			log.info("Error al calcular el número de ejercicios completados");
			numExercisesCompleted = 0;
		}
		return  numExercisesCompleted;
	}

	public void deleteAll(UserDto userDto) {
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
	}
}