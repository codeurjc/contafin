package com.daw.contafin.user;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;

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
import com.daw.contafin.completedLesson.CompletedLessonService;

@Controller
@RequestMapping ("User")
public class UserController extends ContentController{

	@Autowired
	private UserService userService;

	@Autowired
	UserComponent userComponent;
	
	@Autowired
	ImageService imageService;
	
	@Autowired
	CompletedLessonService completedLessonService;

	@RequestMapping("Profile")
	public String profile(Model model){
		
		loadNavbar(model);
		model.addAttribute("goals", false);
		
		//Updating line chart
		
		//Create an array for weekly progress
		int [] progress = new int[7];
		User user = userComponent.getLoggedUser();
		//Get the current date and set first day of week Monday
		Calendar calendar = Calendar.getInstance();
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		//Convert java.util.Date to java.sql.Date
		Date date= calendar.getTime();
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		//Get the current day
		int day =calendar.get(Calendar.DAY_OF_WEEK);
		//Store weekly progress
		progress[day-1]=completedLessonService.getCompletedLessons(user, sqlDate);
		for (int i= day-1; i< 0; i-- ) { 
			calendar.add(Calendar.DATE, -1);
			date= calendar.getTime();
			sqlDate= new java.sql.Date(date.getTime());
			progress[i-1]=completedLessonService.getCompletedLessons(user, sqlDate);
		}
		
		model.addAttribute("progress", progress);

		return "profile";
	}

	// Configuration Controller

	@RequestMapping("Configuration")
	public String configuration(Model model, HttpServletRequest request) {

		loadNavbar(model);
		return "userConfiguration";
	}
	
	@RequestMapping("ChangesSaved")
	public String changes(Model model, @RequestParam("newName") String name, @RequestParam("newEmail") String email,
			@RequestParam("newPass") String pass) {
		
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
			return "Configuration";
		}
	}
	
	//Set Goal Controller
	
	@RequestMapping("Goal")
	public String setGoal(Model model, HttpServletRequest request) {

		loadNavbar(model);
		return "setGoal";
	}
	
	@RequestMapping("NewGoal")
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
	
	@RequestMapping ("NewImage")
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
			 return "Configuration";   
	    }
	    else {
	    		model.addAttribute("noImage", true);
			return "userConfiguration";
	    }
	}

	// Show the image
	@RequestMapping("ProfilePicture")
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
	@RequestMapping("DeleteAccount")
	public String deleteAccount() {
		User user = userComponent.getLoggedUser();
		userService.deleteAccount(user);
		return "/logout";
	}
	
	

}
