package com.daw.contafin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.daw.contafin.unit.Unit;
import com.daw.contafin.unit.UnitService;
import com.daw.contafin.user.UserComponent;
import com.daw.contafin.user.UserService;

@Controller
public class ContentController {

	@Autowired
	UserComponent userComponent;
	
	@Autowired
	UserService userService;
	
	@Autowired
	UnitService unitService;
	
	public void loadNavbar(Model model) {
	
		if (userComponent.isLoggedUser()) {
			model.addAttribute("name", userService.findByEmail(userComponent.getLoggedUser().getEmail()).getName());
			model.addAttribute("level", userService.findByEmail(userComponent.getLoggedUser().getEmail()).getLevel());
			model.addAttribute("points", userService.findByEmail(userComponent.getLoggedUser().getEmail()).getPoints());
			model.addAttribute("streak", userService.findByEmail(userComponent.getLoggedUser().getEmail()).getStreak());
		}
	}
	
	public void loadUnits(Model model) {

		List<Unit> unit = unitService.findAll();
		List<String> units = new ArrayList<>();
		for (int i = 0; i < unit.size(); i++) {
			units.add(unit.get(i).getName());
		}
		model.addAttribute("units", units);
	}
}
