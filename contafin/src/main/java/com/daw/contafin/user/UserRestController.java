package com.daw.contafin.user;


import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
	
	@RequestMapping(value = "/Profile", method = RequestMethod.GET)
	public ResponseEntity<User> profile() {
		if (!userComponent.isLoggedUser()) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		} else {
			User user = userComponent.getLoggedUser();
			int [] progress = userService.progress(user);
			//Update user data
			user.setProgress(progress);
			userService.updateUserData(user);
			return new ResponseEntity<>(user, HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/Configuration", method = RequestMethod.PUT)
	public ResponseEntity<User> configuration(@RequestBody Map<String,String> userData) {
		if (!userComponent.isLoggedUser()) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		} else {
			User user = userComponent.getLoggedUser();
			boolean noData =false; 
			String name = userData.get("newName");
			String email = userData.get("newEmail");
			String pass = userData.get("newPass");
			
			if (!name.isEmpty()) {
				user.setName(name);
				noData =true;
			}
			if (!email.isEmpty()) {
				user.setEmail(email);
				noData =true;
			}
			if (!pass.isEmpty()) {
				user.setPasswordHash(new BCryptPasswordEncoder().encode(pass));
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

	@RequestMapping(value = "/Photo", method = RequestMethod.POST)
	public ResponseEntity<Boolean> profilePhoto(@RequestParam("file") MultipartFile file) {
		if (!userComponent.isLoggedUser()) {
			return new ResponseEntity<>(false,HttpStatus.UNAUTHORIZED);
		} else {
			if (!file.isEmpty()) {
				User user = userComponent.getLoggedUser();
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
			} else {
				return new ResponseEntity<>(false,HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}

	@RequestMapping(value = "/Goal/{goal}", method = RequestMethod.PUT)
	public ResponseEntity<User> goals(@PathVariable int goal) {
		if (!userComponent.isLoggedUser()) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		} else {
			User user = userComponent.getLoggedUser();
			if (goal == 1 || goal == 3 || goal == 5 || goal== 7 ) {
				user.setDailyGoal(goal);
				return new ResponseEntity<>(user, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}

		}
	}
	
}
