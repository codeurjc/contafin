package com.daw.contafin.user;


import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController{
	
	@Autowired
	private UserRepository repository;
	
	@PostConstruct
	public void init () {
		
		repository.save(new User("Ramón", "ramon@hotmail.es", "lalala"));
		repository.save(new User("Julián", "juli@hotmail.es", "lelele"));
		repository.save(new User("Luna", "luna@hotmail.es", "lilili"));
		
	}
	
	@RequestMapping("profile")
    public String profile( Model model) {
		
		model.addAttribute("name", repository.findByName("Julián").getName());
		model.addAttribute("level", repository.findByName("Julián").getLevel());
		model.addAttribute("points", repository.findByName("Julián").getPoints());
		model.addAttribute("streak", repository.findByName("Julián").getStreak());
		model.addAttribute("goals", false);
		
    	return "profile";
    }
	
	@RequestMapping("home")
    public String home() {
		
    	return "home";
    }

	@RequestMapping("/")
    public String index() {
		
    	return "index";
    }
}
