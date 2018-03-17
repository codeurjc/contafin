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
import com.daw.contafin.lesson.Lesson;
import com.daw.contafin.lesson.LessonService;
import com.daw.contafin.unit.Unit;
import com.daw.contafin.unit.UnitService;
import com.daw.contafin.user.UserComponent;
import com.daw.contafin.user.UserService;

@RestController
@RequestMapping("/api/unit")
public class ExerciseRestController{
	
	interface UnitLesson extends Lesson.UnitLesson {}
	
	@Autowired
	private UnitService unitService;
	
	@Autowired
	private LessonService lessonService;
	
	@Autowired
	UserService userService;

	@Autowired
	UserComponent userComponent;

	@Autowired
	ImageService imageService;

	//See all the Unit
	@RequestMapping(value = "/lessaons/", method = RequestMethod.GET)
	public List<Lesson> getunit() {
		return lessonService.findAll();
	}
	
	//See an unit with its lessons
	@RequestMapping(value = "/{idunit}/lessona/", method = RequestMethod.GET)
	public ResponseEntity<Unit> getunitwithlesson(@PathVariable long idunit) {
		Unit unit = unitService.findById(idunit);
		if (unit != null) {
			return new ResponseEntity<>(unit, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/{idunit}/lessoan/{id}", method = RequestMethod.GET)
	public ResponseEntity<Lesson> getItem(@PathVariable long idunit,@PathVariable long id) {
		Lesson lesson = lessonService.findById((idunit-1)*3+id);

		if (lesson != null) {
			return new ResponseEntity<>(lesson, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@RequestMapping(value = "/{idunit}/lessona/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Lesson> actulizaItem(@PathVariable long idunit,@PathVariable long id, @RequestBody Lesson lessonAct) {

		Lesson lesson = lessonService.findById((idunit-1)*3+id);

		if (lesson != null) {
			lesson.setName(lessonAct.getName());
			lessonAct.setId(id);
			lessonService.save(lesson);
			return new ResponseEntity<>(lesson, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}


