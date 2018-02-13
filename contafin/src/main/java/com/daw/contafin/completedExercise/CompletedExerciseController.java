package com.daw.contafin.completedExercise;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.daw.contafin.exercise.Exercise;
import com.daw.contafin.exercise.ExerciseRepository;

public class CompletedExerciseController {
	
	@Autowired
	private CompletedExerciseRepository repository;
	
	@PostConstruct
	public void init () {
		
		/*repository.save(new Student("Ramón", "ramon@hotmail.es", "lalala"));
		repository.save(new Student("Julián", "juli@hotmail.es", "lelele"));
		repository.save(new Student("Luna", "luna@hotmail.es", "lilili"));*/
		
	}
	
	@RequestMapping("profile")
    public String profile( Model model) {
		
		/*model.addAttribute("name", repository.findByName("Julián").getName());
		model.addAttribute("level", repository.findByName("Julián").getLevel());
		model.addAttribute("points", repository.findByName("Julián").getPoints());
		model.addAttribute("streak", repository.findByName("Julián").getStreak());
		model.addAttribute("goals", false);*/
		
    	return "profile";
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
