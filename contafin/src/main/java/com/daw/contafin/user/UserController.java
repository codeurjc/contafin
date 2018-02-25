package com.daw.contafin.user;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import com.daw.contafin.ContentController;
import com.daw.contafin.ImageService;

@Controller
public class UserController extends ContentController{

	@Autowired
	private UserService userService;

	@Autowired
	UserComponent userComponent;
	
	@Autowired
	ImageService imageService;

	@RequestMapping("profile")
	public String profile(Model model){
		
		loadNavbar(model);
		model.addAttribute("goals", false);
		
		//This is an example. "progresss" is an array of lessons completed in one week
		int [] progress = {3,2,0,4,6,0,0};
		model.addAttribute("progress", progress);

		return "profile";
	}

	// Configuration Controller

	@RequestMapping("configuration")
	public String configuration(Model model, HttpServletRequest request) {

		loadNavbar(model);
		return "userConfiguration";
	}
	
	@RequestMapping("changesSaved")
	public String changes(Model model, @RequestParam("new_name") String name, @RequestParam("new_email") String email,
			@RequestParam("new_pass") String pass) {
		
		loadNavbar(model);
		boolean noData =false; 
		User user = userService.findById(userComponent.getLoggedUser().getId());
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
			model.addAttribute("noData", true);
			return "userConfiguration";
		}
		else {
			userService.updateUserData(user);
			userComponent.setLoggedUser(user);
			return "configuration";
		}
	}
	
	//Set Goal Controller
	
	@RequestMapping("goal")
	public String setGoal(Model model, HttpServletRequest request) {

		loadNavbar(model);
		return "setGoal";
	}
	
	@RequestMapping("newGoal")
	public String newGoal(Model model, @RequestParam (value="goal", required=false) String goal) {
		
		loadNavbar(model);
		User user = userService.findById(userComponent.getLoggedUser().getId());
		if (goal == null) {
			model.addAttribute("noGoal", true);
		}
		else {
			user.setDailyGoal(Integer.parseInt(goal));
			userService.updateUserData(user);
			userComponent.setLoggedUser(user);
		}
		return "setGoal";
	}
	
	//Save the image in the database
	
	@RequestMapping ("newImage")
	public String updateImage( Model model, @RequestParam("file") MultipartFile file) throws IOException {

		loadNavbar(model);
		if (!file.isEmpty()) {
	    		User user = userComponent.getLoggedUser();
	    		//Upload image
			byte[] bytes = imageService.uploadImage(file);
			//Update the user's data
			user.setImage(bytes);
			userService.updateUserData(user);
			userComponent.setLoggedUser(user);
			 return "configuration";   
	    }
	    else {
	    		model.addAttribute("noImage", true);
			return "userConfiguration";
	    }
	}

	// Show the image
	@RequestMapping("profilePicture")
	public void sowImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		byte [] image;
		if (userComponent.getLoggedUser().getImage() != null) {
			image = userComponent.getLoggedUser().getImage();
		} else {
			Path path = Paths.get(".//src//main//resources//static/img/profile.png");
			image = Files.readAllBytes(path);

		}
		response.setContentType("image/jpeg");
		ServletOutputStream outputStream = response.getOutputStream();
		outputStream.write(image);
		outputStream.close();
	}
	
	//Delete Account
	@RequestMapping("deleteAccount")
	public String deleteAccount() {
		User user = userComponent.getLoggedUser();
		userService.deleteAccount(user);
		return "/logout";
	}
	
	

}
