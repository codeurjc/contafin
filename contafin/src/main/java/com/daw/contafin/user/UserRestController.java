package com.daw.contafin.user;


import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

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
	
	@Autowired
	UserComponent userComponent;
	
	@Autowired
	ImageService imageService;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteAccount(@PathVariable long id) {
		UserDto userDto = userService.findById(id);
		try {
			userService.deleteAccount(userDto);
			userComponent.setLoggedUser(null);
			return new ResponseEntity<>(true, HttpStatus.OK);

		} catch (EmptyResultDataAccessException e) {
			return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
		}

	}
	
	@JsonView(UserBassic.class)
	@GetMapping(value = "/{id}")
	@ResponseBody
	public ResponseEntity<UserDto> profile(@PathVariable long id) {
		UserDto userDto = userService.findById(id);
		int[] progress = userService.progress(userDto);
		// Update user data
		userDto.setProgress(progress);
		userService.updateUserData(userDto);
		userComponent.setLoggedUser(userDto);
		return new ResponseEntity<>(userDto, HttpStatus.OK);
	}
	
	@JsonView(UserBassic.class)
	@PutMapping(value = "/{id}")
	@ResponseBody
	public ResponseEntity<UserDto> updateUserData(@PathVariable long id, @RequestBody UserDto updatedUser) {
			UserDto userDto = userService.findById(id);
			userDto = userService.updateUser(userDto, updatedUser);
			if (!userDto.equals(null)) {
				userComponent.setLoggedUser(userDto);
				return new ResponseEntity<>(userDto, HttpStatus.OK);
				
			} else {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}
	}
	
	@GetMapping(value = "/{id}/Validation/{pass}")
	@ResponseBody
	public ResponseEntity<Boolean> validaion(@PathVariable long id, @PathVariable String pass) {
		UserDto userDto = userService.findById(id);
		if (new BCryptPasswordEncoder().matches(pass, userDto.getPasswordHash())) {
			return new ResponseEntity<>(true, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(false, HttpStatus.OK);
		}
	}
	
	@GetMapping(value = "/{id}/Progress")
	@ResponseBody
	public ResponseEntity<int[]> progress(@PathVariable long id) {
		UserDto userDto = userService.findById(id);
		int[] progress = userService.progress(userDto);
		// Update user data
		userDto.setProgress(progress);
		userService.updateUserData(userDto);
		return new ResponseEntity<>(progress, HttpStatus.OK);
	}
	
	@JsonView(UserBassic.class)
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
	
	@JsonView(UserBassic.class)
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
	
	@JsonView(UserBassic.class)
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
	
	@PostMapping(value = "/{id}/Photo")
	@ResponseBody
	public ResponseEntity<Boolean> profilePhoto(@PathVariable long id, @RequestParam("file") MultipartFile file) {
		if (!file.isEmpty()) {
			UserDto userDto = userService.findById(id);
			// Upload image
			byte[] bytes;
			try {
				bytes = imageService.uploadImage(file);
				// Update the user's data
				userDto.setImage(bytes);
				userService.updateUserData(userDto);
				userComponent.setLoggedUser(userDto);
				return new ResponseEntity<>(true, HttpStatus.OK);
			} catch (IOException e) {
				return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} 
		else {
			return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value = "/Photo")
	@ResponseBody
	public void sowImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		imageService.showImage(request, response);
	}

	@JsonView(UserBassic.class)
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
	
}
