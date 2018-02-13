package com.daw.contafin.lesson;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LessonController {
	
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
