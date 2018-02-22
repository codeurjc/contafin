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
import com.daw.contafin.exercise.Exercise;
import com.daw.contafin.exercise.ExerciseRepository;
import com.daw.contafin.unit.Unit;
import com.daw.contafin.unit.UnitRepository;

@Controller
public class LessonController extends ContentController {
	
	@Autowired
	private UnitRepository unitRepository;
	@Autowired
	private LessonRepository lessonRepository; 
	@Autowired
	private ExerciseRepository exerciseRepository; 
	
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
		
		model.addAttribute("done", "0");
		model.addAttribute("total", "3");
		model.addAttribute("unit",id);
		model.addAttribute("lessonstring1", lessonsString.get(0));
		model.addAttribute("lessonstring2", lessonsString.get(1));
		model.addAttribute("lessonstring3", lessonsString.get(2));

		model.addAttribute("title", "Introducción");
		
		loadNavbar(model);
		
    	return "lessons" ;
    }	
	
}
