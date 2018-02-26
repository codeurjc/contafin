package com.daw.contafin.exercise;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daw.contafin.lesson.Lesson;



public interface ExerciseRepository extends JpaRepository <Exercise, Long>{
	Exercise findById(long id);
	Exercise findByLessonAndId(Lesson lesson, long id);
	Exercise findByLessonAndKind(Lesson lesson, int kind);
	List <Exercise> findByLesson(Lesson lesson);
}
