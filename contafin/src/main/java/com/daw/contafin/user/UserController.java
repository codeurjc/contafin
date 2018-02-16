package com.daw.contafin.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController{
	
	@Autowired
	private UserService userService;
	
	@Autowired
	UserComponent userComponent;
	
	
	@RequestMapping("profile")
    public String profile( Model model) {
		 
		model.addAttribute("name", userService.findByEmail(userComponent.getLoggedUser().getEmail()).getName());
		model.addAttribute("level", userService.findByEmail(userComponent.getLoggedUser().getEmail()).getLevel());
		model.addAttribute("points", userService.findByEmail(userComponent.getLoggedUser().getEmail()).getPoints());
		model.addAttribute("streak", userService.findByEmail(userComponent.getLoggedUser().getEmail()).getStreak());
		model.addAttribute("goals", false);
		
    	return "profile";
    }
	
	
}
