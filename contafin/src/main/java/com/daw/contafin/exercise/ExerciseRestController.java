package com.daw.contafin.exercise;

import com.daw.contafin.answer.AnswerDto;
import com.daw.contafin.completedExercise.CompletedExerciseDto;
import com.daw.contafin.lesson.LessonDto;
import com.daw.contafin.user.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.daw.contafin.ImageService;
import com.daw.contafin.answer.Answer;
import com.daw.contafin.completedExercise.CompletedExercise;
import com.daw.contafin.completedExercise.CompletedExerciseService;
import com.daw.contafin.exercise.Exercise.ExerciseBassic;
import com.daw.contafin.lesson.Lesson;
import com.daw.contafin.lesson.LessonService;
import com.daw.contafin.user.User;
import com.daw.contafin.user.UserComponent;
import com.daw.contafin.user.UserService;
import com.fasterxml.jackson.annotation.JsonView;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/api/Unit")
@CrossOrigin(maxAge =3600)
@Slf4j
@Transactional
public class ExerciseRestController{
	
	@Autowired
	private ExerciseService exerciseService;
	
	@Autowired
	CompletedExerciseService completedExerciseService;


	//YASSSS
	@GetMapping(value = "/Exercise/{id}")
	@ResponseBody
	public ResponseEntity<ExerciseDto> getOneExercise(@PathVariable long id) {
		log.info("Se ha recibido una solicitud para buscar un ejercicio");
		ResponseEntity<ExerciseDto> response;
		try{
			ExerciseDto exercise = exerciseService.findById(id);
			response = new ResponseEntity<>(exercise, HttpStatus.OK);
		}catch (Exception e){
			String error = "No se ha podido encontrado el ejercicio";
			log.warn(error,e);
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return response;

	}

	//YASSSSS
	@PutMapping(value = "/Exercise/{id}/Solution")
	@ResponseBody
	public ResponseEntity<Boolean> checkExercise(@PathVariable long id, @RequestBody AnswerDto answerAct) {
		log.info("Se ha recibido una solicitud para comprobar la solucion de un ejercicio");
		ResponseEntity<Boolean> response;
		try{
			Boolean b = completedExerciseService.checkAnswer(id,answerAct);
			response = new ResponseEntity<>(b, HttpStatus.OK);
		}catch (Exception e){
			String error = "No se ha podido comprobar la solucion";
			log.warn(error,e);
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return response;
	}
	// SEGUNDA VERSION
	/*@PutMapping(value = "/{idunit}/Lesson/{idlesson}/Exercise/{id}")
	@ResponseBody
	public ResponseEntity<ExerciseDto> updateExercise(@PathVariable long idunit,@PathVariable long idlesson,@PathVariable long id, @RequestBody ExerciseDto exerciseAct) {
		LessonDto lesson = lessonService.findById((idunit-1)*3+idlesson);
		ExerciseDto exercise = exerciseService.findByLessonAndId(lesson, id);
		if (exercise != null) {
			exercise.setKind(exerciseAct.getKind());
			exercise.setStatement(exerciseAct.getStatement());
			exercise.setTexts(exerciseAct.getTexts());
			exerciseAct.setId(id);
			exerciseService.save(exercise);
			return new ResponseEntity<>(exercise, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}*/

	//Ask for a answer
	/*@GetMapping(value = "/{idunit}/Lesson/{idlesson}/Exercise/{id}/Answer")
	@ResponseBody
	public ResponseEntity<AnswerDto> getOneAnswer(@PathVariable long idunit, @PathVariable long idlesson, @PathVariable long id) {
		LessonDto lesson = lessonService.findById(idlesson);
		ExerciseDto exercise = exerciseService.findByLessonAndId(lesson, id);
		if (exercise != null) {
			return new ResponseEntity<>(exercise.getAnswer(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping(value = "/{idunit}/Lesson/{idlesson}/Exercise/{id}/Answer")
	@ResponseBody
	public ResponseEntity<ExerciseDto> changeAnswer(@PathVariable long idunit,@PathVariable long idlesson,@PathVariable long id, @RequestBody AnswerDto answerAct) {
		LessonDto lesson = lessonService.findById(idlesson);
		ExerciseDto exercise = exerciseService.findByLessonAndId(lesson, id);
		if (exercise != null) {
			AnswerDto answer = exercise.getAnswer();
			answer.setResult(answerAct.getResult());
			answerAct.setId(id);
			exercise.setAnswer(answer);
			exerciseService.save(exercise);
			return new ResponseEntity<>(exercise, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}*/

}


