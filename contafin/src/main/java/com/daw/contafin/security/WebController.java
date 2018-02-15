package com.daw.contafin.security;


import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.daw.contafin.user.User;
import com.daw.contafin.user.UserComponent;
import com.daw.contafin.user.UserRepository;



@Controller
public class WebController {
	
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserComponent userComponent;

	@RequestMapping("/")
    public String index() {
		
    	return "index";
    }
	
	@RequestMapping("home")
    public String home(Model model) {
		
		model.addAttribute("name", userRepository.findByEmail(userComponent.getLoggedUser().getEmail()).getName());
		model.addAttribute("points", userRepository.findByEmail(userComponent.getLoggedUser().getEmail()).getPoints());
		model.addAttribute("streak", userRepository.findByEmail(userComponent.getLoggedUser().getEmail()).getStreak());
		model.addAttribute("dailyGoal", userRepository.findByEmail(userComponent.getLoggedUser().getEmail()).getDailyGoal());
		
    	return "home";
    }
	
	@RequestMapping("login")
	public String login() {
		
	return "login";
	}
	


}
