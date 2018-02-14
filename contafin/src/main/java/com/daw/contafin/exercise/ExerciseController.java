package com.daw.contafin.exercise;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.daw.contafin.exercise.Exercise;
import com.daw.contafin.exercise.ExerciseRepository;
import com.daw.contafin.lesson.Lesson;
import com.daw.contafin.lesson.LessonRepository;
import com.daw.contafin.unit.Unit;

@Controller
public class ExerciseController {
	
	@Autowired
	private ExerciseRepository exerciseRepository;
	
	@Autowired
	private LessonRepository lessonRepository; 
	
	@PostConstruct
	public void init () {
		
		Lesson lesson = lessonRepository.findById(1);
		List<String> images =  Arrays.asList("../img/machine.jpg","../img/land.jpg","../img/truck.jpg");
		List<String> texts = Arrays.asList("213.Maquinaria","210.Terrenos y bienes naturales","218. Elementos de transporte");
		exerciseRepository.save(new Exercise(1,"Seleccione el asiento",images,texts,null,lesson));
		
	}
	
	@RequestMapping("/Unit1/Lesson1/Exercise1.1.1")
    public String exercise1(Model model) {
		
		model.addAttribute("Image1", exerciseRepository.findById(1).getRuteImages().get(0));
		model.addAttribute("Image2", exerciseRepository.findById(1).getRuteImages().get(1));
		model.addAttribute("Image3", exerciseRepository.findById(1).getRuteImages().get(2));
		model.addAttribute("Text1", exerciseRepository.findById(1).getTexts().get(0));
		model.addAttribute("Text2", exerciseRepository.findById(1).getTexts().get(1));
		model.addAttribute("Text3", exerciseRepository.findById(1).getTexts().get(2));
		model.addAttribute("Enunciado", exerciseRepository.findById(1).getStatement());
		
    	return "exerciseType1";
    }
	
	/*@RequestMapping("home")
    public String home1() {
		
    	return "home";
    }

	@RequestMapping("index")
    public String index() {
		
    	return "index";
    }*/
}
