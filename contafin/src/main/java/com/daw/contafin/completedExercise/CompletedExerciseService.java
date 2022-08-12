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

	@Autowired
	CompletedExerciseMapper completedExerciseMapper;

	@Autowired
	UserMapper userMapper;

	@Autowired
	ExerciseMapper exerciseMapper;


	
	public CompletedExerciseDto save(CompletedExerciseDto completedExerciseDto) {
		try{
			CompletedExercise completedExercise = completedExerciseMapper.CompletedExerciseDtoToCompletedExercise(completedExerciseDto);
			completedExerciseRepository.save(completedExercise);
		}catch (Exception e){
			completedExerciseDto = null;
		}
			return completedExerciseDto;
	}
	public CompletedExerciseDto findByUserAndExercise(UserDto userDto, ExerciseDto exerciseDto){
			CompletedExerciseDto completedExerciseDto = new CompletedExerciseDto();
		try{
			User user = userMapper.UserDtoToUser(userDto);
			Exercise exercise = exerciseMapper.ExerciseDtoToExercise(exerciseDto);
			CompletedExercise completedExercise = completedExerciseRepository.findByUserAndExercise(user,exercise);
			completedExerciseDto = completedExerciseMapper.CompletedExerciseToCompletedExerciseDto(completedExercise);
		}catch (Exception e){
			completedExerciseDto = null;
		}
		return completedExerciseDto;
	}
	
	public List<CompletedExerciseDto> findByUser(UserDto userDto){
		List<CompletedExerciseDto> completedExerciseDtos = new ArrayList<>();
		try{
			User user = userMapper.UserDtoToUser(userDto);
			List<CompletedExercise> completedExercises = completedExerciseRepository.findByUser(user);
			completedExerciseDtos = completedExerciseMapper.CompletedExercisesToCompletedExercisesDto(completedExercises);
		}catch (Exception e){
			completedExerciseDtos = null;
		}
		return completedExerciseDtos;
	}

	public void delete(CompletedExerciseDto completedExerciseDto) {
		try{
			CompletedExercise completedExercise = completedExerciseMapper.CompletedExerciseDtoToCompletedExercise(completedExerciseDto);
			completedExerciseRepository.delete(completedExercise);
		}catch (Exception e){

		}
	}
	public void delete(long Id) {
		CompletedExerciseDto completedExerciseDto = new CompletedExerciseDto();
		try{
			completedExerciseRepository.deleteById(Id);
		}catch (Exception e){

		}
	}
	//Get all ExerciseCompleted in the lesson and delete them (need to put wrong exercise last)
	public int numExercisesCompleted(long idlesson, int idunit, UserDto userDto) {
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
			numExercisesCompleted = 0;
		}
		return  numExercisesCompleted;
	}

	public void deleteAll(UserDto userDto) {
		try{
			User user =userMapper.UserDtoToUser(userDto);
			List<CompletedExercise> completedExercises =  completedExerciseRepository.findByUser(user);
			List<CompletedExerciseDto> completedExerciseDtos = completedExerciseMapper.CompletedExercisesToCompletedExercisesDto(completedExercises);
			if (completedExerciseDtos != null) {
				CompletedExercise completedExercise = new CompletedExercise();
				for (CompletedExerciseDto completedExerciseDto : completedExerciseDtos) {
					completedExercise = completedExerciseMapper.CompletedExerciseDtoToCompletedExercise(completedExerciseDto);
					if (completedExercise != null) {
						completedExerciseRepository.delete(completedExercise);
					}
				}
			}
		}catch (Exception e){

		}
	}
}