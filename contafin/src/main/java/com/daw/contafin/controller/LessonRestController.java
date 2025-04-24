package com.daw.contafin.controller;

import java.util.List;

import com.daw.contafin.dto.LessonDto;
import com.daw.contafin.dto.UnitHomeDto;
import com.daw.contafin.service.LessonService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.daw.contafin.service.CompletedLessonService;

import javax.transaction.Transactional;


@Slf4j
@Transactional
@RestController
@RequestMapping("/api/Lesson")
public class LessonRestController{

	@Autowired
	LessonService lessonService;

	@Autowired
	CompletedLessonService completedLessonService;
	
	//See one lesson
	@GetMapping(value = "/{id}")
	@ResponseBody
	@Operation(summary = "Busca una por su id la función utilizada getLesson(long)")
	public ResponseEntity<LessonDto> getLesson(@PathVariable long id) {
		log.info("Se ha recibido una solicitud para buscar una leccion");
		ResponseEntity<LessonDto> response;
		try{
			LessonDto lesson = lessonService.findById(id);
			response = new ResponseEntity<>(lesson, HttpStatus.OK);
		}catch (Exception e){
			String error = "No se ha podido encontrado la leccion";
			log.warn(error,e);
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	@GetMapping(value = "/{idunit}/AllCompleted")
	@ResponseBody
	@Operation(summary = "Comprueba las lecciones completadas de una unidad con su id función utilizada isCompletedLesson(long)")
	public ResponseEntity<List<Boolean>> isCompletedLesson(@PathVariable Long idunit) {
		log.info("Se ha recibido una solicitud para saber que lecciones estan completas de la unidaad con id : {}", idunit);
		ResponseEntity<List<Boolean>> response;
		try{
			List<Boolean> lessonComplete = completedLessonService.checkList(idunit);
			response = new ResponseEntity<>(lessonComplete, HttpStatus.OK);
		}catch (Exception e){
			String error = "No se han podido comprobar las lecciones";
			log.warn(error,e);
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	//Get all unit
	@GetMapping(path = "/")
	@Operation(summary = "Extrae la lista de unidades del sistema getUnits()")
	public ResponseEntity<UnitHomeDto> getUnitCompletedForHome() {
		log.info("Se ha recibido una solicitud para listar las unidades");
		ResponseEntity<UnitHomeDto> response;
		try{
			UnitHomeDto unitHomeDto = completedLessonService.topLesson();
			response = new ResponseEntity<>(unitHomeDto, HttpStatus.OK);
		}catch (Exception e){
			String error = "No se ha podido listar las unidades";
			log.warn(error,e);
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}


}

