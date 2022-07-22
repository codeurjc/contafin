package com.daw.contafin.admin;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.daw.contafin.ExcelService;
import com.daw.contafin.user.User;
import com.daw.contafin.user.UserComponent;
import com.daw.contafin.user.UserService;

@RestController
@RequestMapping("api/Admin")
@CrossOrigin(maxAge =3600)
@Slf4j
@Transactional
public class AdminRestController {

	@Autowired
	UserComponent userComponent;

	@Autowired
	UserService userService;

	@Autowired
	ExcelService excelService;

	@GetMapping(path = "/UserData")
	@ResponseBody
	public ResponseEntity<Page<User>> userData(Pageable page) {
		if (!userComponent.isLoggedUser()) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		} else {
			return new ResponseEntity<>(userService.getUsers(page), HttpStatus.OK);
		}
	}
	@GetMapping(path = "/UserData/Excel")
	@ResponseBody
	public ResponseEntity<ServletOutputStream> userListReport(HttpServletResponse response) { //Revisar si esto necesita un @RequestParam
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
