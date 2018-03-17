package com.daw.contafin.exercise;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.daw.contafin.ImageService;
import com.daw.contafin.answer.Answer;
import com.daw.contafin.lesson.Lesson;
import com.daw.contafin.lesson.LessonService;
import com.daw.contafin.user.UserComponent;
import com.daw.contafin.user.UserService;

@RestController
@RequestMapping("/api/unit")
public class ExerciseRestController{
	
	interface UnitLesson extends Lesson.UnitLesson {}
	
	
	@Autowired
	private LessonService lessonService;
	
	@Autowired
	private ExerciseService exerciseService;
	
	@Autowired
	UserService userService;

	@Autowired
	UserComponent userComponent;

	@Autowired
	ImageService imageService;

	//See all the exercise
	@RequestMapping(value = "/lesson/exercises/", method = RequestMethod.GET)
	public List<Exercise> getExercises() {
		return exerciseService.findAll();
	}
		
	@RequestMapping(value = "/{idunit}/lessoan/{idlesson}/exercise/{id}", method = RequestMethod.GET)
	public ResponseEntity<Exercise> getOneExercise(@PathVariable long idunit,@PathVariable long idlesson,@PathVariable long id) {
		Lesson lesson = lessonService.findById((idunit-1)*3+idlesson);
		Exercise exercise = exerciseService.findByLessonAndId(lesson, id);
		if (exercise != null) {
			return new ResponseEntity<>(exercise, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@RequestMapping(value = "/{idunit}/lesson/{idlesson}/exercise/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Exercise> updateExercise(@PathVariable long idunit,@PathVariable long idlesson,@PathVariable long id, @RequestBody Exercise exerciseAct) {
		Lesson lesson = lessonService.findById((idunit-1)*3+idlesson);
		Exercise exercise = exerciseService.findByLessonAndId(lesson, id);
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
	}
	
	//Ask for a answer
	@RequestMapping(value = "/{idunit}/lessoan/{idlesson}/exercise/{id}/answer", method = RequestMethod.GET)
	public ResponseEntity<Answer> getOneAnswer(@PathVariable long idunit,@PathVariable long idlesson,@PathVariable long id) {
		Lesson lesson = lessonService.findById((idunit-1)*3+idlesson);
		Exercise exercise = exerciseService.findByLessonAndId(lesson, id);
		if (exercise != null) {
			return new ResponseEntity<>(exercise.getAnswer(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/{idunit}/lesson/{idlesson}/exercise/{id}/answer", method = RequestMethod.PUT)
	public ResponseEntity<Exercise> changeAnswer(@PathVariable long idunit,@PathVariable long idlesson,@PathVariable long id, @RequestBody Answer answerAct) {
		Lesson lesson = lessonService.findById((idunit-1)*3+idlesson);
		Exercise exercise = exerciseService.findByLessonAndId(lesson, id);
		if (exercise != null) {
			Answer answer = exercise.getAnswer();
			answer.setResult(answerAct.getResult());
			answerAct.setId(id);
			exercise.setAnswer(answer);
			exerciseService.save(exercise);
			return new ResponseEntity<>(exercise, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/{idunit}/lesson/{idlesson}/exercise/{id}/check", method = RequestMethod.PUT)
	public ResponseEntity<Boolean> checkExercise(@PathVariable long idunit,@PathVariable long idlesson,@PathVariable long id, @RequestBody Answer answerAct) {
		Lesson lesson = lessonService.findById((idunit-1)*3+idlesson);
		Exercise exercise = exerciseService.findByLessonAndId(lesson, id);
		boolean goodanswer;
		if (exercise != null) {
			Answer answer = exercise.getAnswer();
			if(exercise.getKind()==2) {
				String[] answergood = answer.getResult().split(" ");
				String[] myanswer = answerAct.getResult().split(" ");
				int counter = 0;
	
				for (int i = 0; i < answergood.length; i++) {
					for (int j = 0; j < myanswer.length; j++) {
						if (answergood[i].equals(myanswer[j])) {
							counter++;
						}
					}
				}
				if (counter >= 3) {
					goodanswer=true;
				} else {
					goodanswer=false;
				}
			}
			else {
				if(answer.getResult().equals(answerAct.getResult())) {
					goodanswer=true;
				}
				else {
					goodanswer=false;
				}
			}
			return new ResponseEntity<>(goodanswer, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	/*
	{
	    "kind": 1,
	    "statement": "1.1.1 Seleccione el asiento",
	    "texts": [
	        "213.Maquinaria",
	        "210.Terrenos y bienes naturales",
	        "218. Elementos de transporte"
	    ]
	}
	*/
	
	/* to ask for the result and change the exercise
	{
		"result": "dos"
	}
	*/
}


