package com.daw.contafin.lesson;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.daw.contafin.ImageService;
import com.daw.contafin.exercise.Exercise;
import com.daw.contafin.exercise.ExerciseRepository;
import com.daw.contafin.exercise.ExerciseService;
import com.daw.contafin.lesson.Lesson;
import com.daw.contafin.lesson.LessonRepository;
import com.daw.contafin.lesson.LessonService;
import com.daw.contafin.unit.Unit;
import com.daw.contafin.unit.UnitService;
import com.daw.contafin.lesson.Lesson.UnitLesson;
import com.daw.contafin.user.User;
import com.daw.contafin.user.UserComponent;
import com.daw.contafin.user.UserService;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping("/api/unit")
public class LessonRestController{
	
	interface UnitLesson extends Lesson.UnitLesson {}
	
	@Autowired
	private UnitService unitService;
	
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

	//See all the Unit
	@RequestMapping(value = "/lessons/", method = RequestMethod.GET)
	public List<Lesson> getunit() {
		return lessonService.findAll();
	}
	
	//See an unit with its lessons
	@RequestMapping(value = "/{idunit}/lesson/", method = RequestMethod.GET)
	public ResponseEntity<Unit> getunitwithlesson(@PathVariable long idunit) {
		Unit unit = unitService.findById(idunit);
		if (unit != null) {
			return new ResponseEntity<>(unit, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/{idunit}/lesson/{id}", method = RequestMethod.GET)
	public ResponseEntity<Lesson> getItem(@PathVariable long idunit,@PathVariable long id) {
		Lesson lesson = lessonService.findById((idunit-1)*3+id);

		if (lesson != null) {
			return new ResponseEntity<>(lesson, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@RequestMapping(value = "/{idunit}/lesson/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Unit> actulizaItem(@PathVariable long idunit,@PathVariable long id, @RequestBody Lesson lessonAct) {

		Unit unit = unitService.findById(id);

		if (unit != null) {
			unit.setName(lessonAct.getName());
			lessonAct.setId(id);
			unitService.save(unit);
			return new ResponseEntity<>(unit, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}

