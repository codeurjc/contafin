package com.daw.contafin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

	@Autowired
	ErrorMessage errorMessage;

	private static final String PATH = "/error";

	@RequestMapping(value = PATH)
	String error(Model model) {

		model.addAttribute("errorMessage", errorMessage.getMessage());
		errorMessage.setMessage("The web page doesn't exist or you don't have permission.");

		return "error2";
	}


	public String getErrorPath() {
		return PATH;
	}

}