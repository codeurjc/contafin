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
import com.daw.contafin.user.User;
import com.daw.contafin.user.UserRepository;

@Controller
public class LessonController {
	
	@Autowired
	private UnitRepository unitRepository;
	@Autowired
	private LessonRepository lessonRepository; 
	@Autowired
	//private UserRepository userRepository; 
	
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

	@RequestMapping("/lessonCompleted")
    public String completedLesson() {
		/*User user = userRepository.findById(id);
		user.setLevel(user.getLevel()+10);
		user.upLevel(); */
    	return "completedlesson";
    }
	
	@RequestMapping("/continueLesson")
    public String continueLesson(Model model) {
		//obtener el numero de ejercicios completados y saber su porcentaje del total y pasarselo.
		model.addAttribute("percentage", "31");
    	return "continueLesson";
    }
	
	
	

}
