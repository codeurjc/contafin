package com.daw.contafin.user;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import com.daw.contafin.unit.UnitDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.daw.contafin.ImageService;
import com.daw.contafin.user.User.UserBassic;
import com.fasterxml.jackson.annotation.JsonView;


@RestController
@RequestMapping ("/api/User")
@CrossOrigin(maxAge =3600)
@Slf4j
@Transactional
public class UserRestController {
	
	
	@Autowired
	UserService userService;
	
	@DeleteMapping(value = "/{id}")
	@ResponseBody
	public ResponseEntity<Boolean> deleteAccount(@PathVariable Long id) {
		log.info("Se ha recibido una peticion para borrar al usuario con id: {}",id);
		ResponseEntity<Boolean> response;
		try{
			userService.deleteAccount(id);
			response = new ResponseEntity<>(HttpStatus.OK);
		}catch (Exception e){
			String error = "No se ha podido borrar el usuario";
			log.warn(error,e);
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return response;
	}


	@GetMapping(value = "/{id}")
	@ResponseBody
	public ResponseEntity<UserDto> profile(@PathVariable Long id) {
		log.info("Se ha recibido una peticion para cargar el usuario con id: {}",id);
		ResponseEntity<UserDto> response;
		try{
			UserDto userDto = userService.getProfile(id);
			response = new ResponseEntity<>(userDto,HttpStatus.OK);
		}catch (Exception e){
			String error = "No se ha cargar el usuario";
			log.warn(error,e);
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return response;
	}


	@PutMapping(value = "/")
	@ResponseBody
	public ResponseEntity<UserDto> updateUserData(@RequestBody UserDto updatedUser) {
		log.info("Se ha recibido una peticion para actualizar el usuario con id: {}");
		ResponseEntity<UserDto> response;
		try{
			UserDto userDto = userService.updateUser(updatedUser);
			response = new ResponseEntity<>(userDto,HttpStatus.OK);
		}catch (Exception e){
			String error = "No se ha podido actualizar el usuario";
			log.warn(error,e);
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return response;
	}
	
	@GetMapping(value = "/{id}/Validation/{pass}")
	@ResponseBody
	public ResponseEntity<Boolean> validation(@PathVariable Long id, @PathVariable String pass) {
		log.info("Se ha recibido una peticion para comprobar el usuario con id: {}",id);
		ResponseEntity<Boolean> response;
		try{
			Boolean b = userService.checkPass(id,pass);
			response = new ResponseEntity<>(b,HttpStatus.OK);
		}catch (Exception e){
			String error = "No se ha comprobar el usuario";
			log.warn(error,e);
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return response;
	}
	
	@GetMapping(value = "/{id}/Progress")
	@ResponseBody
	public ResponseEntity<int[]> progress(@PathVariable Long id) {
		log.info("Se ha recibido una peticion para actualizar el progreso del usuario con id: {}",id);
		ResponseEntity<int[]> response;
		try{
			int[] i = userService.progress(id);
			response = new ResponseEntity<>(i,HttpStatus.OK);
		}catch (Exception e){
			String error = "No se ha actualizar el progreso del usuario";
			log.warn(error,e);
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return response;
	}

	@PostMapping(value = "/{id}/Photo")
	@ResponseBody
	public ResponseEntity<Boolean> profilePhoto(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
		log.info("Se ha recibido una peticion para actualizar la foto del usuario con id: {}",id);
		ResponseEntity<Boolean> response;
		try{
			Boolean b = userService.updatePhoto(id, file);
			response = new ResponseEntity<>(b,HttpStatus.OK);
		}catch (Exception e){
			String error = "No se ha actualizar la foto del usuario";
			log.warn(error,e);
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return response;
	}
	
/*
	@PutMapping(value = "/Name")
	@ResponseBody
	public ResponseEntity<UserDto> updateName(@RequestBody Map<String,String> userData) {
		if (!userComponent.isLoggedUser()) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		} else {
			UserDto userDto = userComponent.getLoggedUser();
			boolean noData =false; 
			String name = userData.get("newName");
			if (!name.isEmpty()) {
				userDto.setName(name);
				userService.updateUserData(userDto);
				userComponent.setLoggedUser(userDto);
				noData =true;
			}
			if(!noData) {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}
			else {
				return new ResponseEntity<>(userDto, HttpStatus.OK);
			}
		}
	}
	

	@PutMapping(value = "/Email")
	@ResponseBody
	public ResponseEntity<UserDto> updateEmail(@RequestBody Map<String,String> userData) {
		if (!userComponent.isLoggedUser()) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		} else {
			UserDto userDto = userComponent.getLoggedUser();
			boolean noData =false; 
			String email = userData.get("newEmail");
			if (!email.isEmpty()) {
				userDto.setEmail(email);
				userService.updateUserData(userDto);
				userComponent.setLoggedUser(userDto);
				noData =true;
			}
			if(!noData) {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}
			else {
				return new ResponseEntity<>(userDto, HttpStatus.OK);
			}
		}
	}
	

	@PutMapping(value = "/Password")
	@ResponseBody
	public ResponseEntity<UserDto> updatePassword(@RequestBody Map<String,String> userData) {
		if (!userComponent.isLoggedUser()) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		} else {
			UserDto userDto = userComponent.getLoggedUser();
			boolean noData =false;
			String oldPass = userData.get("oldPass");
			String newPass = userData.get("newPass");
			if (!newPass.isEmpty()) {
				if (new BCryptPasswordEncoder().matches(oldPass,userDto.getPasswordHash())) {
					userDto.setPasswordHash(new BCryptPasswordEncoder().encode(newPass));
					userService.updateUserData(userDto);
					userComponent.setLoggedUser(userDto);
					noData =true;
				}
			}
			if(!noData) {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}
			else {
				return new ResponseEntity<>(userDto, HttpStatus.OK);
			}
		}
	}

	@GetMapping(value = "/Photo")
	@ResponseBody
	public void sowImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		imageService.showImage(request, response);
	}

	@PutMapping(value = "/Goal")
	@ResponseBody
	public ResponseEntity<UserDto> goals(@RequestBody Map<String,String> userData) {
		if (!userComponent.isLoggedUser()) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		} else {
			UserDto userDto = userComponent.getLoggedUser();
			int goal = Integer.parseInt(userData.get("goal"));
			if (goal == 1 || goal == 3 || goal == 5 || goal== 7 ) {
				userDto.setDailyGoal(goal);
				userDto.setRemainingGoals(goal);
				userService.updateUserData(userDto);
				userComponent.setLoggedUser(userDto);
				return new ResponseEntity<>(userDto, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}

		}
	}

	*/
	
}
