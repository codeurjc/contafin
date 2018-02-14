package com.daw.contafin.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.daw.contafin.user.User;
import com.daw.contafin.user.UserRepository;

@Controller
public class LoginController {
	
	@Autowired
	UserRepository userRepository;
	
	@RequestMapping("login")
    public String login() {
		
    	return "login";
    }
	
	@RequestMapping("signUp")
	public String singUp (Model model, @RequestParam String name, @RequestParam String mail, @RequestParam String password, User user) {
		
		if (userRepository.findByEmail(mail) == null)
			user = new User(name, mail, password, "ROLE_USER");
		return "lesson";
		
	}

}
