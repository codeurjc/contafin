package com.daw.contafin.unit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UnitController {
	
	@Autowired
	private UnitRepository unitRepository;
	
	@PostConstruct
	public void init() {

		Unit unit= new Unit("Unidad 1");
		unitRepository.save(unit);
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
