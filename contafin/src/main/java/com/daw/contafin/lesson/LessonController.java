package com.daw.contafin.lesson;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.daw.contafin.unit.Unit;
import com.daw.contafin.unit.UnitRepository;

@Controller
public class LessonController {
	
	@Autowired
	private UnitRepository unitRepository;
	@Autowired
	private LessonRepository lessonRepository; 
	
	@PostConstruct
	public void init() {

	}
	
	@RequestMapping("/Unit/{id}/lessons")
    public String lessons(Model model, @PathVariable long id) {
		
		Unit unit = unitRepository.findById(id);
		List<Lesson> lesson = lessonRepository.findByUnit(unit);
		List<String> lessonsString = new ArrayList<>();
		
		for(int i=0; i<lesson.size();i++) {
			lessonsString.add(lesson.get(i).getName());
		}
		
		model.addAttribute("name", "");
		model.addAttribute("points", "");
		model.addAttribute("streak", "");
		model.addAttribute("done", "0");
		model.addAttribute("total", "3");
		model.addAttribute("unit",id);
		model.addAttribute("lesson", lessonsString);
		model.addAttribute("title", "Introducción");
		
    	return "lessons" ;
    }

	@RequestMapping("lessons/{{-index}}")
    public String exercisePage() {
		
    	return "exerciseTemplate";
    }
	
	@RequestMapping("/lessonCompleted")
    public String completedLesson() {
		
    	return "completedlesson";
    }
	
	@RequestMapping("/continueLesson")
    public String continueLesson() {
		
    	return "continueLesson";
    }
	
	
	

}
