package com.daw.contafin.user;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.daw.contafin.ContentController;

@Controller
public class UserController extends ContentController{

	@Autowired
	private UserService userService;

	@Autowired
	UserComponent userComponent;

	@RequestMapping("profile")
	public String profile(Model model){
		
		loadNavbar(model);
		model.addAttribute("goals", false);

		return "profile";
	}

	// Configuration Controller

	@RequestMapping("configuration")
	public String configuration(Model model, HttpServletRequest request) {

		loadNavbar(model);
		return "userConfiguration";
	}
	
	@RequestMapping("changesSaved")
	public String changes(Model model, @RequestParam("new_name") String name, @RequestParam("new_email") String email,
			@RequestParam("new_pass") String pass) {
		
		loadNavbar(model);
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
		if(!newData) {
			model.addAttribute("noData", true);
		}
		userService.updateUserData(user);
		userComponent.setLoggedUser(user);
		return "userConfiguration";
	}
	
	//Set Goal Controller
	
	@RequestMapping("goal")
	public String setGoal(Model model, HttpServletRequest request) {

		loadNavbar(model);
		return "setGoal";
	}
	
	@RequestMapping("newGoal")
	public String newGoal(Model model, @RequestParam (value="goal", required=false) String goal) {
		
		loadNavbar(model);
		User user = userService.findById(userComponent.getLoggedUser().getId());
		if (goal == null) {
			model.addAttribute("noGoal", true);
		}
		else {
			user.setDailyGoal(Integer.parseInt(goal));
			userService.updateUserData(user);
			userComponent.setLoggedUser(user);
		}
		return "setGoal";
	}

	
	
}
