package com.daw.contafin.exercise;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw.contafin.lesson.Lesson;

@Service
public class ExerciseService {

	
		@Autowired
		ExerciseRepository exerciseRepository;
		
		
		public Exercise findById (long id) {
			return exerciseRepository.findById(id);
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
			exerciseRepository.delete(id);
		}
		
		
		
		
}
