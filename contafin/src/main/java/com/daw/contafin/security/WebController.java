package com.daw.contafin.security;

import java.io.IOException;
import java.util.Calendar;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.daw.contafin.ContentController;
import com.daw.contafin.EmailService;
import com.daw.contafin.ErrorMessage;
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
	
	@Autowired
	ErrorMessage errorMessage;

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
	
	@RequestMapping ("Admin/Home")
	public String adminHomePage(Model model) {
		return "adminHome";
	}

	// Sign Up Controller

	@RequestMapping("signup")
	public String register(Model model, @RequestParam("name") String name, @RequestParam("email") String email,
			@RequestParam("pass") String pass) {
		if (name.isEmpty() || email.isEmpty() || pass.isEmpty()) {
			model.addAttribute("errorMessage","¡Error al crear la cuenta! Es necesario que rellenes todos los campos.");
		    return "error2";
		}
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
			model.addAttribute("errorMessage","¡Error al crear la cuenta! El correo introducido ya está vinculado a otra cuenta.");
		    return "error2";
		}

	}
	
	//Wrong login controller
	
	@RequestMapping("/loginerror")
	public String loginerror(Model model) {
		model.addAttribute("errorMessage","¡Error al iniciar sesión! Datos erróneos.");
	    return "error2";
	}
	

}
