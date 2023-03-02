package com.daw.contafin.admin;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import com.daw.contafin.exercise.ExerciseDto;
import com.daw.contafin.user.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
	public ResponseEntity<List<UserDto>> userData() {
		log.info("Se ha recibido una solicitud para listar los usuarios");
		ResponseEntity<List<UserDto>> response;
		try{
			if (!userComponent.isLoggedUser()) {
				response = new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			} else {
				response = new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
			}
		}catch (Exception e){
			String error = "No se han podido listar los usuarios";
			log.warn(error,e);
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return response;

	}
	@GetMapping(path = "/UserData/Excel")
	@ResponseBody
	public ResponseEntity<ByteArrayResource> userListReport() {
		log.info("Se va a crear un excel con los usuarios");
		ResponseEntity<ByteArrayResource> response;
		HttpHeaders header= new HttpHeaders();
		header.setContentType(new MediaType("application", "vnd.ms-excel"));
		header.add("Content-disposition", "attachment; filename=user_list.xls");
		try {
			XSSFWorkbook workbook = excelService.generateExcel();
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			workbook.write(stream);
			workbook.close();
			response = new ResponseEntity<>(new ByteArrayResource(stream.toByteArray()),header,HttpStatus.OK);
		} catch (Exception e) {
			String error = "No se han podido crear el excel";
			log.warn(error,e);
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}

}
