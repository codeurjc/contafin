package com.daw.contafin.user;


import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.daw.contafin.ImageService;


@RestController
@RequestMapping ("/api/User")
@CrossOrigin(maxAge =3600)
public class UserRestController {
	
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserComponent userComponent;
	
	@Autowired
	ImageService imageService;
	
	@RequestMapping(value = "/", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteAccount() {
		User user = userComponent.getLoggedUser();
		try {
			userService.deleteAccount(user);
			userComponent.setLoggedUser(null);
			return new ResponseEntity<>(true, HttpStatus.OK);

		} catch (EmptyResultDataAccessException e) {
			return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
		}

	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> profile(@PathVariable long id) {
		User user = userService.findById(id);
		int[] progress = userService.progress(user);
		// Update user data
		user.setProgress(progress);
		userService.updateUserData(user);
		userComponent.setLoggedUser(user);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}/Data", method = RequestMethod.PUT)
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
	
	@RequestMapping(value = "/{id}/Progress", method = RequestMethod.GET)
	public ResponseEntity<int[]> progress(@PathVariable long id) {
		User user = userService.findById(id);
		int[] progress = userService.progress(user);
		// Update user data
		user.setProgress(progress);
		userService.updateUserData(user);
		return new ResponseEntity<>(progress, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/Name", method = RequestMethod.PUT)
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
	
	@RequestMapping(value = "/Email", method = RequestMethod.PUT)
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
	
	@RequestMapping(value = "/Password", method = RequestMethod.PUT)
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

	@RequestMapping(value = "/{id}/Photo", method = RequestMethod.POST)
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
	
	@RequestMapping(value = "/Photo", method = RequestMethod.GET)
	public void sowImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		imageService.showImage(request, response);
	}

	@RequestMapping(value = "/Goal", method = RequestMethod.PUT)
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
