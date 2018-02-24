package com.daw.contafin.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.daw.contafin.ExcelExport;
import com.daw.contafin.user.User;
import com.daw.contafin.user.UserComponent;
import com.daw.contafin.user.UserService;


@Controller
@RequestMapping ("Admin")
public class AdminController  {
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserComponent userComponent;
	
	
	@RequestMapping("UnitCreation")
	public String unitCreation() {
		return "unitCreation";
	}
	
	@RequestMapping("UsersData")
	public String usersData(Model model) {
		model.addAttribute("users", userService.getUsers());
		return "userData";
	}
	
	@RequestMapping ("ExportPreview")
	public ModelAndView userData (ModelAndView model) {
		
		 List<User> users = userService.getUsers();
		return new ModelAndView("exportPreview", "userList", users);
	}
	 
	 @RequestMapping(value="Export", method=RequestMethod.GET)
	 public ModelAndView userListReport(){
	  
		 List<User> users = userService.getUsers();
		 return new ModelAndView(new ExcelExport(), "userList", users);
	  
	 }
	 
	
}
	 
