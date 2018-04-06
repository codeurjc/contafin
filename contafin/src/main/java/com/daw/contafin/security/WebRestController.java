package com.daw.contafin.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.daw.contafin.EmailService;
import com.daw.contafin.user.User;
import com.daw.contafin.user.UserComponent;
import com.daw.contafin.user.UserService;

import freemarker.template.TemplateException;


@RestController
@RequestMapping ("/api")
@CrossOrigin(maxAge =3600)
public class WebRestController {
	
	@Autowired
	UserService userService;
	
	@Autowired 
	UserComponent userComponent;
	
	@Autowired
	EmailService emailService;
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public ResponseEntity<User> signup(@RequestBody User user) {
		if (user.getEmail().isEmpty() || user.getName().isEmpty() || user.getPasswordHash().isEmpty()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		if (userService.findByEmail(user.getEmail())==null) {
			user.setRoles(new ArrayList<>(Arrays.asList("ROLE_USER")));
			user.setPasswordHash(new BCryptPasswordEncoder().encode(user.getPasswordHash()));
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
