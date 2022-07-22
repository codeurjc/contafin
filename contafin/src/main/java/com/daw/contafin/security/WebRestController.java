package com.daw.contafin.security;

import java.io.IOException;
import java.util.Map;

import javax.mail.MessagingException;
import javax.transaction.Transactional;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.daw.contafin.EmailService;
import com.daw.contafin.user.User;
import com.daw.contafin.user.UserComponent;
import com.daw.contafin.user.UserService;
import com.daw.contafin.user.User.UserBassic;
import com.fasterxml.jackson.annotation.JsonView;

import freemarker.template.TemplateException;


@RestController
@RequestMapping ("/api")
@CrossOrigin(maxAge =3600)
@Slf4j
@Transactional
public class WebRestController {
	
	@Autowired
	UserService userService;
	
	@Autowired 
	UserComponent userComponent;
	
	@Autowired
	EmailService emailService;
	
	@JsonView(UserBassic.class)
	@PostMapping(value = "/signup")
	@ResponseBody
	public ResponseEntity<User> signup(@RequestBody Map<String,String> userData) {
		String name = userData.get("name");
		String email = userData.get("email");
		String pass = userData.get("pass");
		if (name.isEmpty() || email.isEmpty() || pass.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		if (userService.findByEmail(email)==null) {
			User user = new User(name, email, pass, "ROLE_USER");
			user.setLastConnection(user.newConnection());
			userService.save(user);
			userComponent.setLoggedUser(user);
			try {
				emailService.sendSimpleMessage(user);
			} catch (MessagingException messaginException) {
				System.out.println(messaginException);
			} catch (IOException IOexception) {
				System.out.println(IOexception);
			} catch (TemplateException templateException) {
				System.out.println(templateException);
			}
			;
			return new ResponseEntity<>(user, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
