package com.daw.contafin.exercise;

import java.io.IOException;
import java.util.List;

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

		
		public Exercise findById (long id) {
			return exerciseRepository.findById(id);
		}
		
		public List<Exercise> findAll(){
			return exerciseRepository.findAll();
		}
		
		public Exercise findByLessonAndId (Lesson lesson, long id) {
			return exerciseRepository.findByLessonAndId(lesson,id);
		}
		
		public Exercise findByLessonAndKind(Lesson lesson, int kind) {
			return exerciseRepository.findByLessonAndKind(lesson,kind);
		}
		
		public List <Exercise> findByLesson(Lesson lesson) {
			 return exerciseRepository.findByLesson(lesson);
		}
		public void save(Exercise exercise) {
			exerciseRepository.save(exercise);
		}
		public void delete(long id) {
			exerciseRepository.deleteById(id);
		}
		public Page<Exercise> getExercises(Pageable page) {
			return exerciseRepository.findAll(page);
		}
		
		//Upload exercise images
		public void uploadExerciseImages( Exercise exercise, MultipartFile image1, MultipartFile image2,MultipartFile image3) throws IOException{
			byte[] bytes1 = imageService.uploadImage(image1);
			byte[]bytes2 = imageService.uploadImage(image2);
			byte[] bytes3 = imageService.uploadImage(image3);
			exercise.setImage1(bytes1);
			exercise.setImage2(bytes2);
			exercise.setImage3(bytes3);
		}
		

		
}
