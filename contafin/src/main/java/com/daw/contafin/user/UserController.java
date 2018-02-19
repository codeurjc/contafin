package com.daw.contafin.user;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	UserComponent userComponent;

	@RequestMapping("profile")
	public String profile(Model model) {

		model.addAttribute("name", userService.findByEmail(userComponent.getLoggedUser().getEmail()).getName());
		model.addAttribute("level", userService.findByEmail(userComponent.getLoggedUser().getEmail()).getLevel());
		model.addAttribute("points", userService.findByEmail(userComponent.getLoggedUser().getEmail()).getPoints());
		model.addAttribute("streak", userService.findByEmail(userComponent.getLoggedUser().getEmail()).getStreak());
		model.addAttribute("goals", false);

		return "profile";
	}

	// Configuration Controller

	@RequestMapping("configuration")
	public String configuration(Model model, HttpServletRequest request) {

		model.addAttribute("name", userService.findByEmail(userComponent.getLoggedUser().getEmail()).getName());
		model.addAttribute("level", userService.findByEmail(userComponent.getLoggedUser().getEmail()).getLevel());
		model.addAttribute("points", userService.findByEmail(userComponent.getLoggedUser().getEmail()).getPoints());
		model.addAttribute("streak", userService.findByEmail(userComponent.getLoggedUser().getEmail()).getStreak());
		return "userConfiguration";
	}
	
	@RequestMapping("changesSaved")
	public String changes(Model model, @RequestParam("new_name") String name, @RequestParam("new_email") String email,
			@RequestParam("new_pass") String pass) {
		boolean newData =false; 
		User user = userService.findById(userComponent.getLoggedUser().getId());
		if (!name.isEmpty()) {
			user.setName(name);
			newData =true;
		}
		if (!email.isEmpty()) {
			user.setEmail(email);
			newData =true;
		}
		if (!pass.isEmpty()) {
			user.setPasswordHash(new BCryptPasswordEncoder().encode(pass));
			newData =true;
		}
		if(newData == false) {
			model.addAttribute("newData", true);
		}
		userService.updateUserData(user);
		userComponent.setLoggedUser(user);
		return "configuration";
	}
	

	
}
