package com.daw.contafin.unit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.daw.contafin.lesson.Lesson;
import com.daw.contafin.lesson.LessonRepository;

@Controller
public class UnitController {
	
	@Autowired
	private UnitRepository unitRepository;
	@Autowired
	private LessonRepository lessonRepository; 
	
	@PostConstruct
	public void init() {

		Unit unit= new Unit("Unidad 1");
		unitRepository.save(unit);
		
		Lesson lesson1 = new Lesson("Lección 1", unit);
		lessonRepository.save(lesson1);
		Lesson lesson2 = new Lesson("Lección 2", unit);
		lessonRepository.save(lesson2);
		Lesson lesson3 = new Lesson("Lección 3", unit);
		lessonRepository.save(lesson3);
	}
	
	@RequestMapping("units")
    public String units(Model model) {
		
		
    	return "units";
    }

	@RequestMapping("units/{{-index}}")
    public String lessonPage() {
		
    	return "lessonTemplate";
    }
		
}
