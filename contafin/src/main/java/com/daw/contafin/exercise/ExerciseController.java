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
		
		List<String> imagenes =  Arrays.asList("../static/img/machine.jpg","../static/img/land.jpg","../static/img/truck.jpg");
		List<String> textos = Arrays.asList("machine","land","truck");
		ClassExercise exercise = new ClassExercise(1,"Seleccione el asiento",imagenes,textos);
		repository.save(new Exercise(exercise.getKind(),exercise,null));
		
	}
	
	@RequestMapping("/Unit1/Lesson1/Exercise1.1.1")
    public String exercise1(Model model) {
		
		model.addAttribute("Imagen1", repository.findById(1).getExercise().getRutaImagenes().get(1));
		
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
