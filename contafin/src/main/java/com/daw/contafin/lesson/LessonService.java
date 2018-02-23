package com.daw.contafin.lesson;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw.contafin.answer.Answer;
import com.daw.contafin.answer.AnswerRepository;
import com.daw.contafin.exercise.Exercise;
import com.daw.contafin.unit.Unit;

@Service
public class LessonService {
	
	@Autowired
	LessonRepository lessonRepository;
	
	public Lesson findById (long id) {
		return lessonRepository.findById(id);
	}
	public List<Lesson> findByUnit(Unit Id){
		return lessonRepository.findByUnit(Id);
	}
	public void save(Lesson lesson) {
		lessonRepository.save(lesson);
	}
	
}

