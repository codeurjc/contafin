package com.daw.contafin.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.daw.contafin.EmailService;
import com.daw.contafin.unit.Unit;
import com.daw.contafin.unit.UnitRepository;
import com.daw.contafin.user.User;
import com.daw.contafin.user.UserComponent;
import com.daw.contafin.user.UserService;

import freemarker.template.TemplateException;

@Controller
public class WebController {

	@Autowired
	UserService userService;

	@Autowired
	UserComponent userComponent;
	
	@Autowired
	private UnitRepository unitRepository;
	
	@Autowired
	EmailService emailService;
	
	//Login Controller

	@RequestMapping("/")
	public String index() {

		return "index";
	}

	//Home page Controller
	
	@RequestMapping("home")
	public String home(Model model) {
		
		model.addAttribute("loggedUser", userComponent.isLoggedUser());
		
		if (userComponent.isLoggedUser()) {
			model.addAttribute("name", userService.findByEmail(userComponent.getLoggedUser().getEmail()).getName());
			model.addAttribute("points", userService.findByEmail(userComponent.getLoggedUser().getEmail()).getPoints());
			model.addAttribute("streak", userService.findByEmail(userComponent.getLoggedUser().getEmail()).getStreak());
			model.addAttribute("dailyGoal", userService.findByEmail(userComponent.getLoggedUser().getEmail()).getDailyGoal());
			
		}
		
		List<Unit> unit = unitRepository.findAll();
		List<String> units = new ArrayList<>();
		
		for(int i=0; i<unit.size();i++) {
			units.add(unit.get(i).getName());
		}
		
		model.addAttribute("units",units);
		
		return "home";
	}
	
	//Sign Up Controller
	
	@RequestMapping("signup")
	public String register(Model model, @RequestParam("name") String name, @RequestParam("email") String email,
			@RequestParam("pass") String pass) {

		if (userService.findByEmail(email) == null) {
			userService.save(new User(name, email, pass, "ROLE_USER"));
			try {
				emailService.sendSimpleMessage(userService.findByEmail(email));
			} catch (MessagingException messaginException) {
				System.out.println(messaginException);	
			} catch (IOException IOexception) {
				System.out.println(IOexception);	
			} catch (TemplateException templateException) {
				System.out.println(templateException);	
			}
			;
			return "/";
		} else {
			model.addAttribute("loggedUser", true);
			return "signup";
		}
		
	}

}
