package com.daw.contafin.exercise;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.daw.contafin.lesson.LessonDto;
import com.daw.contafin.lesson.LessonMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.daw.contafin.ImageService;
import com.daw.contafin.completedExercise.CompletedExercise;
import com.daw.contafin.completedExercise.CompletedExerciseService;
import com.daw.contafin.completedLesson.CompletedLesson;
import com.daw.contafin.completedLesson.CompletedLessonService;
import com.daw.contafin.lesson.Lesson;
import com.daw.contafin.lesson.LessonService;
import com.daw.contafin.user.User;

import javax.transaction.Transactional;

@Service
@Slf4j
@Transactional
public class ExerciseService {

	
		@Autowired
		ExerciseRepository exerciseRepository;
		
		@Autowired
		ImageService imageService;
		
		@Autowired
		CompletedLessonService completedLessonService;

		@Autowired
		ExerciseMapper exerciseMapper;

		@Autowired
		LessonMapper lessonMapper;

		public ExerciseDto findById (long id) {
			ExerciseDto exerciseDto = new ExerciseDto();
			try{
				Exercise exercise = exerciseRepository.findById(id);
				exerciseDto = exerciseMapper.ExerciseToExerciseDto(exercise);
			}catch (Exception e){
				exerciseDto = null;
			}
			return exerciseDto;
		}
		
		public List<ExerciseDto> findAll(){
			List<ExerciseDto> exercisesDto = new ArrayList<>();
			try{
				List<Exercise> exercises = exerciseRepository.findAll();
				exercisesDto = exerciseMapper.ExercisesToExercisesDto(exercises);
			}catch (Exception e){
				exercisesDto = null;
			}
			return exercisesDto;
		}
		
		public ExerciseDto findByLessonAndId (LessonDto lessonDto, long id) {
			ExerciseDto exerciseDto = new ExerciseDto();
			try{
				Lesson lesson = lessonMapper.LessonDtoToLesson(lessonDto);
				Exercise exercise = exerciseRepository.findByLessonAndId(lesson,id);
				exerciseDto = exerciseMapper.ExerciseToExerciseDto(exercise);
			}catch (Exception e){
				exerciseDto = null;
			}
			return exerciseDto;
		}
		
		public ExerciseDto findByLessonAndKind(LessonDto lessonDto, int kind) {
			ExerciseDto exerciseDto = new ExerciseDto();
			try{
				Lesson lesson = lessonMapper.LessonDtoToLesson(lessonDto);
				Exercise exercise = exerciseRepository.findByLessonAndKind(lesson,kind);
				exerciseDto = exerciseMapper.ExerciseToExerciseDto(exercise);
			}catch (Exception e){
				exerciseDto = null;
			}
			return exerciseDto;
		}
		
		public List<ExerciseDto> findByLesson(LessonDto lessonDto) {
			List<ExerciseDto> exercisesDto = new ArrayList<>();
			try{
				Lesson lesson = lessonMapper.LessonDtoToLesson(lessonDto);
				List<Exercise> exercises = exerciseRepository.findByLesson(lesson);
				exercisesDto = exerciseMapper.ExercisesToExercisesDto(exercises);
			}catch (Exception e){
				exercisesDto = null;
			}
			return exercisesDto;
		}
		public void save(ExerciseDto exerciseDto) {
			try{
				Exercise exercise = exerciseMapper.ExerciseDtoToExercise(exerciseDto);
				exerciseRepository.save(exercise);
			}catch (Exception e){

			}

		}
		public void delete(long id) {
			try{
				exerciseRepository.deleteById(id);
			}catch (Exception e){

			}

		}
		public Page<Exercise> getExercises(Pageable page) {///////////////////////////////Revisar
			try{
				return exerciseRepository.findAll(page);
			}catch (Exception e){
				return null;
			}
		}
		
		//Upload exercise images
		public void uploadExerciseImages( ExerciseDto exerciseDto, MultipartFile image1, MultipartFile image2,MultipartFile image3) throws IOException{
			byte[] bytes1 = imageService.uploadImage(image1);
			byte[] bytes2 = imageService.uploadImage(image2);
			byte[] bytes3 = imageService.uploadImage(image3);
			exerciseDto.setImage1(bytes1);
			exerciseDto.setImage2(bytes2);
			exerciseDto.setImage3(bytes3);
		}
		

		
}
