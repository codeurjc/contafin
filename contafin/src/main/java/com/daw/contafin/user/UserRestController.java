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
		User user = userService.findById(id);
		try {
			userService.deleteAccount(user);
			userComponent.setLoggedUser(null);
			return new ResponseEntity<>(true, HttpStatus.OK);

		} catch (EmptyResultDataAccessException e) {
			return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
		}

	}
	
	@JsonView(UserBassic.class)
	@GetMapping(value = "/{id}")
	@ResponseBody
	public ResponseEntity<User> profile(@PathVariable long id) {
		User user = userService.findById(id);
		int[] progress = userService.progress(user);
		// Update user data
		user.setProgress(progress);
		userService.updateUserData(user);
		userComponent.setLoggedUser(user);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@JsonView(UserBassic.class)
	@PutMapping(value = "/{id}")
	@ResponseBody
	public ResponseEntity<User> updateUserData(@PathVariable long id, @RequestBody User updatedUser) {
			User user = userService.findById(id);
			user = userService.updateUser(user, updatedUser);
			if (!user.equals(null)) {
				userComponent.setLoggedUser(user);
				return new ResponseEntity<>(user, HttpStatus.OK);
				
			} else {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}
	}
	
	@GetMapping(value = "/{id}/Validation/{pass}")
	@ResponseBody
	public ResponseEntity<Boolean> validaion(@PathVariable long id, @PathVariable String pass) {
		User user = userService.findById(id);
		if (new BCryptPasswordEncoder().matches(pass, user.getPasswordHash())) {
			return new ResponseEntity<>(true, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(false, HttpStatus.OK);
		}
	}
	
	@GetMapping(value = "/{id}/Progress")
	@ResponseBody
	public ResponseEntity<int[]> progress(@PathVariable long id) {
		User user = userService.findById(id);
		int[] progress = userService.progress(user);
		// Update user data
		user.setProgress(progress);
		userService.updateUserData(user);
		return new ResponseEntity<>(progress, HttpStatus.OK);
	}
	
	@JsonView(UserBassic.class)
	@PutMapping(value = "/Name")
	@ResponseBody
	public ResponseEntity<User> updateName(@RequestBody Map<String,String> userData) {
		if (!userComponent.isLoggedUser()) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		} else {
			User user = userComponent.getLoggedUser();
			boolean noData =false; 
			String name = userData.get("newName");
			if (!name.isEmpty()) {
				user.setName(name);
				userService.updateUserData(user);
				userComponent.setLoggedUser(user);
				noData =true;
			}
			if(!noData) {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}
			else {
				return new ResponseEntity<>(user, HttpStatus.OK);
			}
		}
	}
	
	@JsonView(UserBassic.class)
	@PutMapping(value = "/Email")
	@ResponseBody
	public ResponseEntity<User> updateEmail(@RequestBody Map<String,String> userData) {
		if (!userComponent.isLoggedUser()) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		} else {
			User user = userComponent.getLoggedUser();
			boolean noData =false; 
			String email = userData.get("newEmail");
			if (!email.isEmpty()) {
				user.setEmail(email);
				userService.updateUserData(user);
				userComponent.setLoggedUser(user);
				noData =true;
			}
			if(!noData) {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}
			else {
				return new ResponseEntity<>(user, HttpStatus.OK);
			}
		}
	}
	
	@JsonView(UserBassic.class)
	@PutMapping(value = "/Password")
	@ResponseBody
	public ResponseEntity<User> updatePassword(@RequestBody Map<String,String> userData) {
		if (!userComponent.isLoggedUser()) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		} else {
			User user = userComponent.getLoggedUser();
			boolean noData =false;
			String oldPass = userData.get("oldPass");
			String newPass = userData.get("newPass");
			if (!newPass.isEmpty()) {
				if (new BCryptPasswordEncoder().matches(oldPass,user.getPasswordHash())) {
					user.setPasswordHash(new BCryptPasswordEncoder().encode(newPass));
					userService.updateUserData(user);
					userComponent.setLoggedUser(user);
					noData =true;
				}
			}
			if(!noData) {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}
			else {
				return new ResponseEntity<>(user, HttpStatus.OK);
			}
		}
	}
	
	@PostMapping(value = "/{id}/Photo")
	@ResponseBody
	public ResponseEntity<Boolean> profilePhoto(@PathVariable long id, @RequestParam("file") MultipartFile file) {
		if (!file.isEmpty()) {
			User user = userService.findById(id);
			// Upload image
			byte[] bytes;
			try {
				bytes = imageService.uploadImage(file);
				// Update the user's data
				user.setImage(bytes);
				userService.updateUserData(user);
				userComponent.setLoggedUser(user);
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
	public ResponseEntity<User> goals(@RequestBody Map<String,String> userData) {
		if (!userComponent.isLoggedUser()) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		} else {
			User user = userComponent.getLoggedUser();
			int goal = Integer.parseInt(userData.get("goal"));
			if (goal == 1 || goal == 3 || goal == 5 || goal== 7 ) {
				user.setDailyGoal(goal);
				user.setRemainingGoals(goal);
				userService.updateUserData(user);
				userComponent.setLoggedUser(user);
				return new ResponseEntity<>(user, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}

		}
	}
	
}
