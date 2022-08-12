package com.daw.contafin.lesson;

import java.util.ArrayList;
import java.util.List;

import com.daw.contafin.completedLesson.CompletedLessonDto;
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
@RequestMapping("/api/Unit")
@CrossOrigin(maxAge =3600)
@Slf4j
@Transactional
public class LessonRestController{
	
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
	
	@Autowired
	CompletedLessonService completedLessonService;
	
	@Autowired
	CompletedExerciseService completedExerciseService;
	
	@Autowired
	ExerciseService exerciseService;

	//See all the lessons
	@JsonView(LessonBasic.class)
	@GetMapping(value = "/Lessons/")
	@ResponseBody
	public ResponseEntity<Page<Lesson>> getLessons(Pageable page) {
		return new ResponseEntity<>(lessonService.getLessons(page), HttpStatus.OK);
	}
	
	//See an unit with its lessons
	@GetMapping(value = "/{idunit}/Lesson/")
	@ResponseBody
	public ResponseEntity<UnitDto> getunitwithlesson(@PathVariable long idunit) {
		UnitDto unit = unitService.findById(idunit);
		if (unit != null) {
			List<LessonDto> lessons = unit.getLessons();
			for (int i=0; i<lessons.size();i++) {
				lessons.get(i).setExercises(null);
			}
			UnitDto unittry = new UnitDto(unit.getName());
			unittry.setId(unit.getId());
			unittry.setLessons(lessons);
			return new ResponseEntity<>(unittry, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	//See one lesson
	@JsonView(LessonBasic.class)
	@GetMapping(value = "/{idunit}/Lesson/{id}")
	@ResponseBody
	public ResponseEntity<LessonDto> getLesson(@PathVariable long idunit,@PathVariable long id) {
		LessonDto lesson = lessonService.findById((idunit-1)*3+id);

		if (lesson != null) {
			return new ResponseEntity<>(lesson, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	//Update a lesson
	@JsonView(LessonBasic.class)
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
	}
	
	@GetMapping(value = "/{idunit}/Lesson/{idlesson}/Completed")
	@ResponseBody
	public ResponseEntity<Boolean> completedLesson(@PathVariable int idunit, @PathVariable int idlesson) {
		UserDto user = userComponent.getLoggedUser();
		// Get all ExerciseCompleted in the lesson and delete them (need to put wrong exercise last)
		int numExercisesCompleted = completedExerciseService.numExercisesCompleted(idlesson, idunit, user);
		//Update user data
		lessonService.completedLesson(user, idlesson, idunit, numExercisesCompleted);
		if (numExercisesCompleted == 4) {
			return new ResponseEntity<>(true, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(false, HttpStatus.OK);
		}

	}
	
	@GetMapping(value = "/{idunit}/Lesson/{idlesson}/isCompleted")
	@ResponseBody
	public ResponseEntity<Boolean> isCompletedLesson(@PathVariable int idunit, @PathVariable int idlesson) {
		UserDto user = userComponent.getLoggedUser();
		LessonDto lesson = lessonService.findById(idlesson);
		CompletedLessonDto completedLesson = completedLessonService.findByUserAndLesson(user, lesson);
		if (completedLesson != null) {
			return new ResponseEntity<>(true, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(false, HttpStatus.OK);
		}

	}
	
	@GetMapping(value = "/{idunit}/Lessons/Completed")
	@ResponseBody
	public ResponseEntity<ArrayList<Boolean>> isCompletedLessonB(@PathVariable int idunit) {
		UserDto user = userComponent.getLoggedUser();
		List<LessonDto> lessons = unitService.findById(idunit).getLessons();
		ArrayList<Boolean> booleans = new ArrayList<Boolean>(); 
		for (int i=0; i< lessons.size(); i++) {
			CompletedLessonDto completedLesson = completedLessonService.findByUserAndLesson(user, lessons.get(i));
			if(completedLesson != null) {
				booleans.add(true);
			}
			else {
				booleans.add(false);
			}
		}
		return new ResponseEntity<>(booleans, HttpStatus.OK);
	}
}

