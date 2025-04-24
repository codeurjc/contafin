package com.daw.contafin.controller;

import com.daw.contafin.dto.AnswerDto;
import com.daw.contafin.dto.ExerciseDto;
import com.daw.contafin.service.ExerciseService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;



@Slf4j
@Transactional
@RestController
@RequestMapping("/api/Exercise")
public class ExerciseRestController{

	@Autowired
	private ExerciseService exerciseService;


	//YASSSS
	@GetMapping(value = "/{id}")
	@ResponseBody
	@Operation(summary = "Busca un ejercicio por su id la función utilizada getOneExercise(long)")
	public ResponseEntity<ExerciseDto> getOneExercise(@PathVariable long id) {
		log.info("Se ha recibido una solicitud para buscar un ejercicio");
		ResponseEntity<ExerciseDto> response;
		try{
			ExerciseDto exercise = exerciseService.findById(id);
			response = new ResponseEntity<>(exercise, HttpStatus.OK);
		}catch (Exception e){
			String error = "No se ha podido encontrado el ejercicio";
			log.warn(error,e);
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;

	}

	//YASSSSS
	@PostMapping(value = "/{id}/Solution")
	@ResponseBody
	@Operation(summary = "Se comprueba la solución de un ejercicio la función utilizada getOneExercise(long,AnswerDto)")
	public ResponseEntity<Boolean> checkExercise(@PathVariable long id, @RequestBody AnswerDto answerAct) {
		log.info("Se ha recibido una solicitud para comprobar la solucion de un ejercicio");
		ResponseEntity<Boolean> response;
		try{
			Boolean b = exerciseService.checkAnswer(id,answerAct);
			response = new ResponseEntity<>(b, HttpStatus.OK);
		}catch (Exception e){
			String error = "No se ha podido comprobar la solucion";
			log.warn(error,e);
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

}


