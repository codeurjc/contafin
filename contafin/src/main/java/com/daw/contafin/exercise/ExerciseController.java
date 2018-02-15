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
	
	List<String> texts;
	
	@PostConstruct
	public void init () {
		
		Lesson lesson = lessonRepository.findById(1);
		List<String> images =  Arrays.asList("../img/machine.jpg","../img/land.jpg","../img/truck.jpg");
		List<String> texts = Arrays.asList("213.Maquinaria","210.Terrenos y bienes naturales","218. Elementos de transporte");
		exerciseRepository.save(new Exercise(1,"Seleccione el asiento",images,texts,null,lesson));
		
		exerciseRepository.save(new Exercise(2,"Escribe la denominación de la cuenta que recoge: " + "maquinarias para el proceso productivo de la empresa",null,null,null,lesson));
		
		texts = Arrays.asList("213.Pepe","210.Terrenos y bienes naturales","218. Elementos de transporte","206. Aplicaciones informáticas","213. Maquinaria","100. Capital social","Pasivo exigible","300. Mercaderías A","Patrimonio neto","Inmovilizado material","Existencias","Inmovilizado intangible");
		exerciseRepository.save(new Exercise(3,"Toca los pares",null,texts,null,lesson));
		
		texts = Arrays.asList("213.Hola","210.Terrenos y bienes naturales","218. Elementos de transporte","206. Aplicaciones informáticas","213. Maquinaria","100. Capital social","Pasivo exigible","300. Mercaderías A","Patrimonio neto","Inmovilizado material","Existencias","Inmovilizado intangible");
		exerciseRepository.save(new Exercise(4,"Realiza el asiento",null,texts,null,lesson));
		
	}
	
	@RequestMapping("/Unit1/Lesson1/Exercise1.1.1")
    public String exercise1(Model model) {
		
		model.addAttribute("Image1", exerciseRepository.findById(1).getRuteImages().get(0));
		model.addAttribute("Image2", exerciseRepository.findById(1).getRuteImages().get(1));
		model.addAttribute("Image3", exerciseRepository.findById(1).getRuteImages().get(2));
		model.addAttribute("Text1", exerciseRepository.findById(1).getTexts().get(0));
		model.addAttribute("Text2", exerciseRepository.findById(1).getTexts().get(1));
		model.addAttribute("Text3", exerciseRepository.findById(1).getTexts().get(2));
		model.addAttribute("Statement", exerciseRepository.findById(1).getStatement());
		
    	return "exerciseType1";
    }
	@RequestMapping("/Unit1/Lesson1/Exercise1.1.2")
    public String exercise2(Model model) {
		
		model.addAttribute("Statement", exerciseRepository.findById(2).getStatement());
		
    	return "exerciseType2";
    }
	
	@RequestMapping("/Unit1/Lesson1/Exercise1.1.3")
    public String exercise3(Model model) {
		
		texts = exerciseRepository.findById(3).getTexts();
		
		model.addAttribute("Statement", exerciseRepository.findById(3).getStatement());
		model.addAttribute("texts", texts);
		
    	return "exerciseType3";
    }
	@RequestMapping("/Unit1/Lesson1/Exercise1.1.4")
    public String exercise4(Model model) {
		
		texts = exerciseRepository.findById(4).getTexts();
		
		model.addAttribute("Statement", exerciseRepository.findById(4).getStatement());
		model.addAttribute("texts", texts);
		
    	return "exerciseType4";
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
