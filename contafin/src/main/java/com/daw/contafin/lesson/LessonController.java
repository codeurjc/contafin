package com.daw.contafin.lesson;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
		Unit unit = unitRepository.findById(1);
		Lesson lesson1 = new Lesson("Lección 1", unit);
		lessonRepository.save(lesson1);
		Lesson lesson2 = new Lesson("Lección 2", unit);
		lessonRepository.save(lesson2);
		Lesson lesson3 = new Lesson("Lección 3", unit);
		lessonRepository.save(lesson3);
	}
	
	@RequestMapping("lessons")
    public String lessons(Model model) {
		
		List<String> lesson = Arrays.asList("Iniciación","Patrimonio","Inmovilizado");
		
		model.addAttribute("name", "");
		model.addAttribute("points", "");
		model.addAttribute("streak", "");
		model.addAttribute("done", "0");
		model.addAttribute("total", "3");
		model.addAttribute("lesson", lesson);
		model.addAttribute("title", "Introducción");
		
    	return "lessons" ;
    }

	@RequestMapping("lessons/{{-index}}")
    public String exercisePage() {
		
    	return "exerciseTemplate";
    }
	

}
