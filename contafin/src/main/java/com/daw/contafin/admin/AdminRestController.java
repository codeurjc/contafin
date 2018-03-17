package com.daw.contafin.admin;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.daw.contafin.ExcelService;
import com.daw.contafin.user.User;
import com.daw.contafin.user.UserComponent;
import com.daw.contafin.user.UserService;

@RestController
@RequestMapping("api/Admin")
public class AdminRestController {

	@Autowired
	UserComponent userComponent;

	@Autowired
	UserService userService;

	@Autowired
	ExcelService excelService;

	@RequestMapping(value = "/UserData", method = RequestMethod.GET)
	public ResponseEntity<Page<User>> userData(Pageable page) {
		if (!userComponent.isLoggedUser()) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		} else {
			return new ResponseEntity<>(userService.getUsers(page), HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/UserData/Export", method = RequestMethod.GET)
	public ResponseEntity<ServletOutputStream> userListReport(HttpServletResponse response) {
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment; filename=\"user_list.xls\"");
		Workbook workbook = excelService.generateExcel();
		try {
			workbook.write(response.getOutputStream());
			return new ResponseEntity<>(response.getOutputStream(), HttpStatus.OK);
		} catch (IOException e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
