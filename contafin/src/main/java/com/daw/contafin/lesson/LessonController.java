package com.daw.contafin.lesson;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.daw.contafin.ContentController;
import com.daw.contafin.completedLesson.CompletedLesson;
import com.daw.contafin.completedLesson.CompletedLessonRepository;
import com.daw.contafin.exercise.Exercise;
import com.daw.contafin.exercise.ExerciseRepository;
import com.daw.contafin.unit.Unit;
import com.daw.contafin.unit.UnitRepository;
import com.daw.contafin.user.User;
import com.daw.contafin.user.UserComponent;

@Controller
public class LessonController extends ContentController {
	
	@Autowired
	private UnitRepository unitRepository;
	@Autowired
	private LessonRepository lessonRepository; 
	@Autowired
	private ExerciseRepository exerciseRepository; 
	@Autowired
	private CompletedLessonRepository completedLessonRepository;
	
	@Autowired
	UserComponent userComponent;
	
	@PostConstruct
	public void init() {

	}
	
	@RequestMapping("/Unit/{id}/lessons")
    public String lessons(Model model, @PathVariable long id) {
		
		Unit unit = unitRepository.findById(id);
		List<Lesson> lesson = lessonRepository.findByUnit(unit);
		List<String> lessonsString = new ArrayList<>();
		
		//Find the first exercise id and kind
		List<Exercise> exercises1 = exerciseRepository.findByLesson(lesson.get(0));
		List<Exercise> exercises2 = exerciseRepository.findByLesson(lesson.get(1));
		List<Exercise> exercises3 = exerciseRepository.findByLesson(lesson.get(2));
		
		long idexercise;
		int kindexercise;
		idexercise = exercises1.get(0).getId();
		kindexercise = exercises1.get(0).getKind();
		model.addAttribute("idexercise1",idexercise);
		model.addAttribute("kindexercise1",kindexercise);
		
		idexercise = exercises2.get(0).getId();
		kindexercise = exercises2.get(0).getKind();
		model.addAttribute("idexercise2",idexercise);
		model.addAttribute("kindexercise2",kindexercise);
		
		idexercise = exercises3.get(0).getId();
		kindexercise = exercises3.get(0).getKind();
		model.addAttribute("idexercise3",idexercise);
		model.addAttribute("kindexercise3",kindexercise);



		
		
		for(int i=0; i<lesson.size();i++) {
			lessonsString.add(lesson.get(i).getName());
		}
		
		//Get lessons done (Posible forma de obtener las lecciones completadas)
		
		int done = 0;
		User user = userComponent.getLoggedUser();
		
		
		boolean lesson1Done = false;
		boolean lesson2Done = false;
		boolean lesson3Done = false;
		
		
		CompletedLesson completedLesson1 = completedLessonRepository.findByUserAndLesson(user, lesson.get(0));
		if(completedLesson1 != null) {
			lesson1Done = true;
			done++;
			}
		CompletedLesson completedLesson2 = completedLessonRepository.findByUserAndLesson(user, lesson.get(1));
		if(completedLesson2 != null) {
			lesson2Done = true;
			done++;
			}
		CompletedLesson completedLesson3 = completedLessonRepository.findByUserAndLesson(user, lesson.get(2));
		if(completedLesson3 != null) {
			lesson3Done = true;
			done++;
			}
		
		String title = unit.getName();
		  
		model.addAttribute("done", done);
		model.addAttribute("total", "3");
		model.addAttribute("lesson1done",lesson1Done);
		model.addAttribute("lesson2done",lesson2Done);
		model.addAttribute("lesson3done",lesson3Done);
		model.addAttribute("unit",id);
		model.addAttribute("lessonstring1", lessonsString.get(0));
		model.addAttribute("lessonstring2", lessonsString.get(1));
		model.addAttribute("lessonstring3", lessonsString.get(2));

		model.addAttribute("title", title);
		
		loadNavbar(model);
		
    	return "lessons" ;
    }	
	
}
