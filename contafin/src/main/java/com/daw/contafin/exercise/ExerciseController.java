package com.daw.contafin.exercise;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.daw.contafin.exercise.Exercise;
import com.daw.contafin.exercise.ExerciseRepository;

public class ExerciseController {
	
	@Autowired
	private ExerciseRepository repository;
	
	@PostConstruct
	public void init () {
		
		List<String> images =  Arrays.asList("../static/img/machine.jpg","../static/img/land.jpg","../static/img/truck.jpg");
		List<String> texts = Arrays.asList("machine","land","truck");
		repository.save(new Exercise(1,"Seleccione el asiento",images,texts,null));
		
	}
	
	@RequestMapping("/Unit1/Lesson1/Exercise1.1.1")
    public String exercise1(Model model) {
		
		model.addAttribute("Image1", repository.findById(1).getRuteImages().get(1));
		
    	return "exerciseType1";
    }
	
	@RequestMapping("home")
    public String home() {
		
    	return "home";
    }

	@RequestMapping("index")
    public String index() {
		
    	return "index";
    }
}
