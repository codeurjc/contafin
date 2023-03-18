package com.daw.contafin.lesson;

import java.util.ArrayList;
import java.util.List;

import com.daw.contafin.completedLesson.CompletedLessonDto;
import com.daw.contafin.exercise.ExerciseDto;
import com.daw.contafin.unit.UnitDto;
import com.daw.contafin.user.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.daw.contafin.ImageService;
import com.daw.contafin.completedExercise.CompletedExerciseService;
import com.daw.contafin.completedLesson.CompletedLessonService;
import com.daw.contafin.completedLesson.CompletedLesson;
import com.daw.contafin.exercise.ExerciseService;
import com.daw.contafin.lesson.Lesson;
import com.daw.contafin.lesson.LessonService;
import com.daw.contafin.lesson.Lesson.LessonBasic;
import com.daw.contafin.unit.Unit;
import com.daw.contafin.unit.UnitService;
import com.daw.contafin.user.User;
import com.daw.contafin.user.UserComponent;
import com.daw.contafin.user.UserService;
import com.fasterxml.jackson.annotation.JsonView;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/api/Lesson")
@CrossOrigin(maxAge =3600)
@Slf4j
@Transactional
public class LessonRestController{
	
	@Autowired
	LessonService lessonService;
	
	@Autowired
	CompletedLessonService completedLessonService;
	
	@Autowired
	CompletedExerciseService completedExerciseService;

	//See all the lessons
	/*@JsonView(LessonBasic.class)
	@GetMapping(value = "/Lessons/")
	@ResponseBody
	public ResponseEntity<Page<Lesson>> getLessons(Pageable page) {
		return new ResponseEntity<>(lessonService.getLessons(page), HttpStatus.OK);
	}*/
	
	//See one lesson
	@GetMapping(value = "/{id}")
	@ResponseBody
	public ResponseEntity<LessonDto> getLesson(@PathVariable long id) {
		log.info("Se ha recibido una solicitud para buscar una leccion");
		ResponseEntity<LessonDto> response;
		try{
			LessonDto lesson = lessonService.findById(id);
			response = new ResponseEntity<>(lesson, HttpStatus.OK);
		}catch (Exception e){
			String error = "No se ha podido encontrado la leccion";
			log.warn(error,e);
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return response;
	}

	@GetMapping(value = "/{idlesson}/Completed")
	@ResponseBody
	public ResponseEntity<Boolean> completedLesson(@PathVariable Long idlesson) {
		log.info("Se ha recibido una solicitud para comprobar si una leccion esta completa");
		ResponseEntity<Boolean> response;
		try{
			Boolean b = completedExerciseService.checkLessonComplete(idlesson);
			response = new ResponseEntity<>(b, HttpStatus.OK);
		}catch (Exception e){
			String error = "No se ha podido comprobar la leccion";
			log.warn(error,e);
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return response;

	}

	@GetMapping(value = "/{idunit}/AllCompleted")
	@ResponseBody
	public ResponseEntity<List<Boolean>> isCompletedLessonB(@PathVariable Long idunit) {
		log.info("Se ha recibido una solicitud para saber que lecciones estan completas de la unidaad con id : {}", idunit);
		ResponseEntity<List<Boolean>> response;
		try{
			List<Boolean> lessonComplete = completedLessonService.checkList(idunit);
			response = new ResponseEntity<>(lessonComplete, HttpStatus.OK);
		}catch (Exception e){
			String error = "No se han podido comprobar las lecciones";
			log.warn(error,e);
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return response;
	}
	
	//Update a lesson
	/*@JsonView(LessonBasic.class)
	@PutMapping(value = "/{idunit}/Lesson/{id}")
	@ResponseBody
	public ResponseEntity<LessonDto> updateLesson(@PathVariable long idunit,@PathVariable long id, @RequestBody LessonDto lessonAct) {
		LessonDto lesson = lessonService.findById((idunit-1)*3+id);
		if (lesson != null) {
			lesson.setName(lessonAct.getName());
			lessonAct.setId(id);
			lessonService.save(lesson);
			return new ResponseEntity<>(lesson, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}*/

	/*@GetMapping(value = "/Lesson/{idlesson}/isCompleted")
	@ResponseBody
	public ResponseEntity<Boolean> isCompletedLesson(@PathVariable int idlesson) {
		UserDto user = userComponent.getLoggedUser();
		LessonDto lesson = lessonService.findById(idlesson);
		Boolean b = completedLessonService.existCompletedLesson(user, lesson);
		if (b) {
			return new ResponseEntity<>(true, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(false, HttpStatus.OK);
		}

	}*/

}

