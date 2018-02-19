package com.daw.contafin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.daw.contafin.user.UserComponent;
import com.daw.contafin.user.UserService;

@Controller
public class ContentController {

	@Autowired
	UserComponent userComponent;
	
	@Autowired
	UserService userService;
	
	public void loadNavbar(Model model) {
	
		if (userComponent.isLoggedUser()) {
			model.addAttribute("name", userService.findByEmail(userComponent.getLoggedUser().getEmail()).getName());
			model.addAttribute("level", userService.findByEmail(userComponent.getLoggedUser().getEmail()).getLevel());
			model.addAttribute("points", userService.findByEmail(userComponent.getLoggedUser().getEmail()).getPoints());
			model.addAttribute("streak", userService.findByEmail(userComponent.getLoggedUser().getEmail()).getStreak());
		}
	}
}
