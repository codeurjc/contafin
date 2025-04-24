package com.daw.contafin.controller;

import java.io.ByteArrayOutputStream;
import java.util.List;

import javax.transaction.Transactional;

import com.daw.contafin.dto.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.daw.contafin.service.ExcelService;
import com.daw.contafin.service.UserService;



@Slf4j
@Transactional
@RestController
@RequestMapping("api/Admin")
public class AdminRestController {

	@Autowired
	UserService userService;

	@Autowired
	ExcelService excelService;

	@GetMapping(path = "/UserData")
	@ResponseBody
	@Operation(summary = "Devuelve una lista de usuarios la función utilizada es userData()")
	public ResponseEntity<List<UserDto>> userData() {
		log.info("Se ha recibido una solicitud para listar los usuarios");
		ResponseEntity<List<UserDto>> response;
		try{
			if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString().contains("ROLE_ANONYMOUS")) {
				response = new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			} else {
				response = new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
			}
		}catch (Exception e){
			String error = "No se han podido listar los usuarios";
			log.warn(error,e);
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;

	}
	@GetMapping(path = "/UserData/Excel")
	@ResponseBody
	@Operation(summary = "Devuelve un archvio excel convertido 'ByteArrayResource' la función utilizada userListReport()")
	public ResponseEntity<ByteArrayResource> userListReport() {
		log.info("Se va a crear un excel con los usuarios");
		ResponseEntity<ByteArrayResource> response;
		HttpHeaders header= new HttpHeaders();
		header.setContentType(new MediaType("application", "vnd.ms-excel"));
		header.add("Content-disposition", "attachment; filename=user_list.xls");
		try {
			if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString().contains("ROLE_ANONYMOUS")) {
				response = new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			} else {
				SXSSFWorkbook workbook = excelService.generateExcel();
				ByteArrayOutputStream stream = new ByteArrayOutputStream();
				workbook.write(stream);
				workbook.close();
				response = new ResponseEntity<>(new ByteArrayResource(stream.toByteArray()),header,HttpStatus.OK);
			}

		} catch (Exception e) {
			String error = "No se han podido crear el excel";
			log.warn(error,e);
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}

}
