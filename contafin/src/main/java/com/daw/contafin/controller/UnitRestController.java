package com.daw.contafin.controller;

import java.util.List;


import javax.transaction.Transactional;
import com.daw.contafin.dto.UnitDto;
import com.daw.contafin.dto.UnitHomeDto;
import com.daw.contafin.service.UnitService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import com.daw.contafin.service.CompletedLessonService;



@Slf4j
@Transactional
@RestController
@RequestMapping("/api/Unit")
public class UnitRestController{


	@Autowired
	UnitService unitService;

	@Autowired
	CompletedLessonService completedLessonService;

	//Get all unit
	@GetMapping(path = "/")
	@Operation(summary = "Extrae la lista de unidades del sistema getUnits()")
	public ResponseEntity<List<UnitDto>> getUnits() {
		log.info("Se ha recibido una solicitud para listar las unidades");
		ResponseEntity<List<UnitDto>> response;
		try{
			List<UnitDto> unitDtoList = unitService.findAll();
			response = new ResponseEntity<>(unitDtoList, HttpStatus.OK);
		}catch (Exception e){
			String error = "No se ha podido listar las unidades";
			log.warn(error,e);
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
	
	//Get 1 unit
	@GetMapping(value = "/{id}")
	@Operation(summary = "Busca una unidad por el id, la función utilizada es getOneUnit(long)")
	public ResponseEntity<UnitDto> getOneUnit(@PathVariable long id) {
		log.info("Se ha recibido una peticion para buscar la unidad con el id: {}",id);
		ResponseEntity<UnitDto> response;
		try{
			UnitDto unitDto = unitService.findById(id);
			response = new ResponseEntity<>(unitDto, HttpStatus.OK);
		}catch (Exception e){
			String error = "No se ha podido encontrar la unidad";
			log.warn(error,e);
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
		
	//Post Unit
	@PostMapping(value = "/") // Cambiar esto en Angular
	@Operation(summary = "Guarda un objeto de tipo UnitDto, la función utilizada es saveUnit(UnitDto)")
	public ResponseEntity<UnitDto> saveUnit(@RequestBody UnitDto unit) {
		log.info("Se ha recibido una peticion para guardar una unidad");
		ResponseEntity<UnitDto> response;
		try{
			UnitDto unitDto = unitService.saveUnitComplete(unit);
			response = new ResponseEntity<>(unitDto, HttpStatus.OK);
		}catch (Exception e){
			String error = "No se ha podido guardar la unidad";
			log.warn(error,e);
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	@GetMapping(value = "/{id}/numberOfCompletedLessons")
	@Operation(summary = "Devuelve el número de lecciones completadas en una unidad dado su id, la función utilizada es numberOfCompletedLesson(long)")
	public ResponseEntity<Integer> numberOfCompletedLesson(@PathVariable Long id) {
		log.info("Se ha recibido una peticion calcular el numero de lecciones completas de la unidad con id: {}", id);
		ResponseEntity<Integer> response;
		try{
			int n = completedLessonService.numberOfCompletedLesson(id);
			response = new ResponseEntity<>(n, HttpStatus.OK);
		}catch (Exception e){
			String error = "No se ha calcular";
			log.warn(error,e);
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	@DeleteMapping(value = "/delete/{id}")
	@Operation(summary = "Elimina una unidad dado su id, la función utilizada es deleteUnit(long)")
	public ResponseEntity<Integer> deleteUnit(@PathVariable Long id) {
		log.info("Se ha recibido una peticion para eliminar la unidad con id: {}", id);
		ResponseEntity<Integer> response;
		try{
			unitService.delete(id);

			response = new ResponseEntity<>(HttpStatus.OK);
		}catch (Exception e){
			String error = "No se ha podido eliminar la unidad";
			log.warn(error,e);
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
}
