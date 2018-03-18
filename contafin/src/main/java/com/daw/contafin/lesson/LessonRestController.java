package com.daw.contafin.lesson;

import java.sql.Date;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.daw.contafin.ImageService;
import com.daw.contafin.completedExercise.CompletedExerciseService;
import com.daw.contafin.completedLesson.CompletedLesson;
import com.daw.contafin.completedLesson.CompletedLessonService;
import com.daw.contafin.lesson.Lesson;
import com.daw.contafin.lesson.LessonService;
import com.daw.contafin.unit.Unit;
import com.daw.contafin.unit.UnitService;
import com.daw.contafin.user.User;
import com.daw.contafin.user.UserComponent;
import com.daw.contafin.user.UserService;

@RestController
@RequestMapping("/api/Unit")
public class LessonRestController{
	
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
	
	@Autowired
	CompletedLessonService completedLessonService;
	
	@Autowired
	CompletedExerciseService completedExerciseService;

	//See all the lessons
	@RequestMapping(value = "/Lessons/", method = RequestMethod.GET)
	public ResponseEntity<Page<Lesson>> getLessons(Pageable page) {
		return new ResponseEntity<>(lessonService.getLessons(page), HttpStatus.OK);
	}
	
	//See an unit with its lessons
	@RequestMapping(value = "/{idunit}/Lesson/", method = RequestMethod.GET)
	public ResponseEntity<Unit> getunitwithlesson(@PathVariable long idunit) {
		Unit unit = unitService.findById(idunit);
		if (unit != null) {
			return new ResponseEntity<>(unit, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	//See one lesson
	@RequestMapping(value = "/{idunit}/Lesson/{id}", method = RequestMethod.GET)
	public ResponseEntity<Lesson> getLesson(@PathVariable long idunit,@PathVariable long id) {
		Lesson lesson = lessonService.findById((idunit-1)*3+id);

		if (lesson != null) {
			return new ResponseEntity<>(lesson, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	//Update a lesson
	@RequestMapping(value = "/{idunit}/Lesson/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Lesson> updateLesson(@PathVariable long idunit,@PathVariable long id, @RequestBody Lesson lessonAct) {

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
	
	@RequestMapping(value = "/{idunit}/Lesson/{idlesson}/Completed", method = RequestMethod.GET)
	public ResponseEntity<Boolean> completedLesson(@PathVariable int idunit, @PathVariable int idlesson) {
		User user = userComponent.getLoggedUser();
		// Get all ExerciseCompleted in the lesson and delete them (need to put wrong exercise last)
		int numExercisesCompleted = completedExerciseService.numExercisesCompleted(idlesson, idunit, user);
		Lesson lesson = lessonService.findById(idlesson + (3 * (idunit - 1)));
		Calendar date = Calendar.getInstance();
		Date sqlDate = new Date((date.getTime()).getTime());
		CompletedLesson completedLessonS = completedLessonService.findByUserAndLesson(user, lesson);
		// If lesson is not completed yet and you do all exercise set the Lesson to done
		if ((completedLessonS == null) && (numExercisesCompleted == 4)) {
			CompletedLesson completedLesson = new CompletedLesson(user, lesson, sqlDate);
			completedLessonService.save(completedLesson);
			if (userComponent.isLoggedUser()) {
				user.setExp(user.getExp() + 10);
				user.upLevel();
				user.updateStreak(user, completedLessonService.getCompletedLessons(user, sqlDate));
				user.setRemainingGoals(userService.getRemainingGoals(user));
				user.setLastLesson(userService.getLesson(user));
				user.setLastUnit(userService.getUnit(user));
				userService.updateUserData(user);
				userComponent.setLoggedUser(user);
			}
		}
		if (numExercisesCompleted == 4) {
			return new ResponseEntity<>(true, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(false, HttpStatus.OK);
		}

	}
}

