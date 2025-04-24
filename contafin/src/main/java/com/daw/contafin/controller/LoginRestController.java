package com.daw.contafin.controller;

import javax.transaction.Transactional;
import com.daw.contafin.dto.JwtResponse;
import com.daw.contafin.dto.UserDto;
import com.daw.contafin.service.EmailService;
import com.daw.contafin.config.jwt.UserDetailsImpl;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.daw.contafin.service.UserService;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;




@Transactional
@Slf4j
@RestController
@RequestMapping("/api")
public class LoginRestController {

	@Autowired
	EmailService emailService;

	@Autowired
	UserService userService;


	@PostMapping("/login")
	@Operation(summary = "Función que permite autenticarse en la aplicación función utilizada logIn(Map<String,String>)")
	public ResponseEntity<JwtResponse> logIn(@RequestBody Map<String,String> userData) {
		log.info("Va a hacer login un usuario");
		ResponseEntity<JwtResponse> response;
		try{
			Map<String,Object> responseObjects = userService.createAuthentication(userData.get("name"), userData.get("pass"));
			UserDetailsImpl userDetails = (UserDetailsImpl) responseObjects.get("userDetails");
			userDetails.setUser(userService.getProfile(userDetails.getId()));

			response = ResponseEntity.ok(new JwtResponse((String) responseObjects.get("token"),
					userDetails.getUser(),
					userDetails.getId(),
					userDetails.getUsername(),
					userDetails.getEmail(),
					(List<String>) responseObjects.get("roles")));
		} catch (Exception e) {
			String error = "No se han podido hacer login el usuario";
			log.warn(error,e);
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}

	@PostMapping(value = "/signup")
	@ResponseBody
	@Operation(summary = "Función que permite crear una cuenta y autenticarse en la aplicación función utilizada signup(Map<String,String>)")
	public ResponseEntity<JwtResponse> signup(@RequestBody Map<String,String> userData) {
		log.info("Se va a dar de alta un usuario");
		ResponseEntity<JwtResponse> response;
		try{
			String name = userData.get("name");
			String email = userData.get("email");
			String pass = userData.get("pass");
			if (name == null || name.isEmpty() || email == null || email.isEmpty() || pass == null || pass.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}

			if (userService.findByEmail(email)==null) {
				UserDto user = new UserDto(name, email, pass, "ROLE_USER");
				user.setLastConnection(user.newConnection());
				byte []image = Files.readAllBytes(Paths.get("img/profile.png"));
				user.setImage(image);
				userService.save(user);

				Map<String,Object> responseObjects = userService.createAuthentication(userData.get("name"), userData.get("pass"));

				UserDetailsImpl userDetails = (UserDetailsImpl) responseObjects.get("userDetails");

				try {
					emailService.sendSimpleMessage(user);
				}catch (Exception e){

				}

				return ResponseEntity.ok(new JwtResponse((String) responseObjects.get("token"),
						userDetails.getUser(),
						userDetails.getId(),
						userDetails.getUsername(),
						userDetails.getEmail(),
						(List<String>) responseObjects.get("roles")));
			} else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		}catch (Exception e) {
			String error = "No se han podido crear el usuario";
			log.warn(error,e);
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;

	}

}
