package com.daw.contafin.exercise;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.daw.contafin.exercise.Exercise;
import com.daw.contafin.exercise.ExerciseRepository;
import com.daw.contafin.lesson.Lesson;
import com.daw.contafin.lesson.LessonRepository;

@Controller
public class ExerciseController {
	
	@Autowired
	private ExerciseRepository exerciseRepository;
	
	@Autowired
	private LessonRepository lessonRepository; 
	
	List<String> texts;
	
	@PostConstruct
	public void init () {
		
	}
	
	@RequestMapping("/Unit/{id}/lessons/{numLesson}/Exercise/1")
    public String exercise1(Model model,@PathVariable int id, @PathVariable int numLesson) {
		
		
		Lesson lesson = lessonRepository.findById(numLesson);
		Exercise exercise = exerciseRepository.findByLessonAndKind(lesson,1);
		
		model.addAttribute("Image1", exercise.getRuteImages().get(0));
		model.addAttribute("Image2", exercise.getRuteImages().get(1));
		model.addAttribute("Image3", exercise.getRuteImages().get(2));
		model.addAttribute("Text1", exercise.getTexts().get(0));
		model.addAttribute("Text2", exercise.getTexts().get(1));
		model.addAttribute("Text3", exercise.getTexts().get(2));
		model.addAttribute("Statement", exercise.getStatement());
		
		model.addAttribute("idunit",id);
		model.addAttribute("idlesson",numLesson);

		
    	return "exerciseType1";
    }
	@RequestMapping("/Unit/{id}/lessons/{numLesson}/Exercise/2")
    public String exercise2(Model model,@PathVariable int id, @PathVariable int numLesson) {
		
		Lesson lesson = lessonRepository.findById(numLesson);
		Exercise exercise = exerciseRepository.findByLessonAndKind(lesson,2);
		
		model.addAttribute("idunit",id);
		model.addAttribute("idlesson",numLesson);
		
		model.addAttribute("Statement", exercise.getStatement());
		
    	return "exerciseType2";
    }
	
	@RequestMapping("/Unit/{id}/lessons/{numLesson}/Exercise/3")
    public String exercise3(Model model,@PathVariable int id, @PathVariable int numLesson) {
		
		Lesson lesson = lessonRepository.findById(numLesson);
		Exercise exercise = exerciseRepository.findByLessonAndKind(lesson,3);
		
		model.addAttribute("idunit",id);
		model.addAttribute("idlesson",numLesson);
		
		model.addAttribute("Statement", exercise.getStatement());
		model.addAttribute("texts", texts);
		
    	return "exerciseType3";
    }
	@RequestMapping("/Unit/{id}/lessons/{numLesson}/Exercise/4")
    public String exercise4(Model model,@PathVariable int id, @PathVariable int numLesson) {
		
		Lesson lesson = lessonRepository.findById(numLesson);
		Exercise exercise = exerciseRepository.findByLessonAndKind(lesson,3);
		
		model.addAttribute("idunit",id);
		model.addAttribute("idlesson",numLesson);
		
		model.addAttribute("Statement", exercise.getStatement());
		model.addAttribute("texts", texts);
		
    	return "exerciseType4";
    }
	
	@RequestMapping("/Unit/{id}/lessons/{numLesson}/Exercise/5")
    public String exercise5(Model model,@PathVariable int id, @PathVariable int numLesson) {
		
		Lesson lesson = lessonRepository.findById(numLesson);
		Exercise exercise = exerciseRepository.findByLessonAndKind(lesson,5);
		
		model.addAttribute("idunit",id);
		model.addAttribute("idlesson",numLesson);
		
		model.addAttribute("Statement", exercise.getStatement());
		model.addAttribute("texts", texts);
		
    	return "exerciseType5";
    }
	@RequestMapping("/Unit/{id}/lessons/{numLesson}/Exercise/6")
    public String exercise6(Model model,@PathVariable int id, @PathVariable int numLesson) {
		
		Lesson lesson = lessonRepository.findById(numLesson);
		Exercise exercise = exerciseRepository.findByLessonAndKind(lesson,6);
		
		model.addAttribute("idunit",id);
		model.addAttribute("idlesson",numLesson);
		
		String texts1 = exercise.getTexts().get(0);
		String texts2 = exercise.getTexts().get(1);
		String texts3 = exercise.getTexts().get(2);
		String texts4 = exercise.getTexts().get(3);
		
		model.addAttribute("Statement", exercise.getStatement());
		model.addAttribute("texts1", texts1);
		model.addAttribute("texts2", texts2);
		model.addAttribute("texts3", texts3);
		model.addAttribute("texts4", texts4);
		
    	return "exerciseType6";
    }
	
	@RequestMapping("/Unit/{id}/lessons/{numLesson}/Exercise/7")
    public String exercise7(Model model,@PathVariable int id, @PathVariable int numLesson) {
		
		Lesson lesson = lessonRepository.findById(numLesson);
		Exercise exercise = exerciseRepository.findByLessonAndKind(lesson,7);
		
		model.addAttribute("idunit",id);
		model.addAttribute("idlesson",numLesson);
		
		model.addAttribute("Statement", exercise.getStatement());
		model.addAttribute("texts", texts);
		
    	return "exerciseType7";
	}
	
	/*@RequestMapping("/lesson/{idlesson}/lessonCompleted/")
    public String exerciseaqui(Model model,@PathVariable int idlesson) {
		
		
		Lesson lesson = lessonRepository.findById(idLesson);
		List<Exercise> = exercise.findByLesson(lesson);
		
		
		
		
		
		
		Lesson lesson = lessonRepository.findById(numLesson);
		Exercise exercise = exerciseRepository.findByLessonAndKind(lesson,7);
		
		model.addAttribute("idunit",id);
		model.addAttribute("idlesson",numLesson);
		
		model.addAttribute("Statement", exercise.getStatement());
		model.addAttribute("texts", texts);
		
    	return "exerciseType7";
	}*/
}
