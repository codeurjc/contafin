package com.daw.contafin.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.daw.contafin.ContentController;
import com.daw.contafin.EmailService;
import com.daw.contafin.user.User;
import com.daw.contafin.user.UserComponent;
import com.daw.contafin.user.UserService;

import freemarker.template.TemplateException;

@Controller
public class WebController extends ContentController {

	@Autowired
	UserService userService;

	@Autowired
	UserComponent userComponent;
	
	@Autowired
	EmailService emailService;

	// Login Controller

	@RequestMapping("/")
	public String index() {

		return "index";
	}

	// Home page Controller

	@RequestMapping("home")
	public String home(Model model) {

		model.addAttribute("loggedUser", userComponent.isLoggedUser());

		if (userComponent.isLoggedUser()) {
			if (userComponent.getLoggedUser().getRoles().contains("ROLE_ADMIN")) {
				model.addAttribute("isAdmin", true);
			}
			loadNavbar(model);
			model.addAttribute("dailyGoal", userService.findByEmail(userComponent.getLoggedUser().getEmail()).getDailyGoal());
			
			//Update the user's last connection
			User user = userComponent.getLoggedUser();
			user.setLastConnection(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
			userService.updateUserData(user);
			userComponent.setLoggedUser(user);
		}
		loadUnits(model);
		return "home";
	}
	
	@RequestMapping ("adminHomePage")
	public String adminHomePage(Model model) {

			model.addAttribute("name", userService.findByEmail(userComponent.getLoggedUser().getEmail()).getName());
			model.addAttribute("users", userService.getUsers());

			List<User> user = userService.getUsers();
			List<String> users = new ArrayList<>();
			List<String> emails = new ArrayList<>();
			List<Integer> levels = new ArrayList<>();

			for (int i = 0; i < user.size(); i++) {

				users.add(user.get(i).getName());
				levels.add(user.get(i).getLevel());
				emails.add(user.get(i).getEmail());

			}

			model.addAttribute("username", users);
			model.addAttribute("levels", levels);
			model.addAttribute("email", emails);

			loadUnits(model);

			return "adminHome";
		
	}

	// Sign Up Controller

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
