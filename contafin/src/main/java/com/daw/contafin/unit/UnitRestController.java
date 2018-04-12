package com.daw.contafin.unit;

import java.io.IOException;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.daw.contafin.ImageService;
import com.daw.contafin.completedExercise.CompletedExerciseService;
import com.daw.contafin.completedLesson.CompletedLessonService;
import com.daw.contafin.exercise.Exercise;
import com.daw.contafin.exercise.ExerciseService;
import com.daw.contafin.lesson.Lesson;
import com.daw.contafin.lesson.LessonService;
import com.daw.contafin.unit.Unit.UnitBassic;
import com.daw.contafin.user.User;
import com.daw.contafin.user.UserComponent;
import com.daw.contafin.user.UserService;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping("/api/Unit")
@CrossOrigin(maxAge =3600)
public class UnitRestController{
	
	
	@Autowired
	private UnitService unitService;
	
	@Autowired
	private LessonService lessonService;

	@Autowired
	private ExerciseService exerciseService;
	
	@Autowired
	CompletedLessonService completedLessonService;
	
	@Autowired
	CompletedExerciseService completedExerciseService;
	
	
	@Autowired
	UserService userService;

	@Autowired
	UserComponent userComponent;

	@Autowired
	ImageService imageService;

	byte[] bytes1;
	byte[] bytes2;
	byte[] bytes3;

	//Get all unit
	@JsonView(UnitBassic.class)
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<Page<Unit>> getUnit(Pageable page) {
		return new ResponseEntity<>(unitService.getUnits(page), HttpStatus.OK);
	}
	
	//Get 1 unit
	@JsonView(UnitBassic.class)
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Unit> getOneUnit(@PathVariable long id) {
		Unit unit = unitService.findById(id);
		if (unit != null) {
			return new ResponseEntity<>(unit, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
		
	//Post Unit
	@RequestMapping(value = "/", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Unit> createUnit(@RequestBody Unit unit) {
		
		if (unit != null) {
			Exercise exercise;
			
			unitService.save(unit);
			
			Lesson lesson = unit.getLessons().get(0);
			lesson.setUnit(unit);
			lessonService.save(lesson);
			exercise = lesson.getExercises().get(0);
			exercise.setLesson(lesson);
			exerciseService.save(exercise);
			exercise = lesson.getExercises().get(1);
			exercise.setLesson(lesson);
			exerciseService.save(exercise);
			exercise = lesson.getExercises().get(2);
			exercise.setLesson(lesson);
			exerciseService.save(exercise);
			exercise = lesson.getExercises().get(3);
			exercise.setLesson(lesson);
			exerciseService.save(exercise);
			
			
			lesson = unit.getLessons().get(1);
			lesson.setUnit(unit);
			lessonService.save(lesson);
			exercise = lesson.getExercises().get(0);
			exercise.setLesson(lesson);
			exerciseService.save(exercise);
			exercise = lesson.getExercises().get(1);
			exercise.setLesson(lesson);
			exerciseService.save(exercise);
			exercise = lesson.getExercises().get(2);
			exercise.setLesson(lesson);
			exerciseService.save(exercise);
			exercise = lesson.getExercises().get(3);
			exercise.setLesson(lesson);
			exerciseService.save(exercise);
			
			
			
			lesson = unit.getLessons().get(2);
			lesson.setUnit(unit);
			lessonService.save(lesson);
			exercise = lesson.getExercises().get(0);
			exercise.setLesson(lesson);
			exerciseService.save(exercise);
			exercise = lesson.getExercises().get(1);
			exercise.setLesson(lesson);
			exerciseService.save(exercise);
			exercise = lesson.getExercises().get(2);
			exercise.setLesson(lesson);
			exerciseService.save(exercise);
			exercise = lesson.getExercises().get(3);
			exercise.setLesson(lesson);
			exerciseService.save(exercise);
			return new ResponseEntity<>(unit, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/{id}/Images", method = RequestMethod.POST)
	public ResponseEntity<Boolean> addImages(@PathVariable long id, @RequestParam("images") MultipartFile [] images ) {
		Unit unit = unitService.findById(id);
		Lesson lesson = unit.getLessons().get(0);
		Exercise exercise =exerciseService.findByLesson(lesson).get(0);
		// Upload images
		try {
			int aux= 0;
			for (int i=0; i< 3; i++) {
				exerciseService.uploadExerciseImages(exercise, images[aux], images[aux+1], images[aux+2]);
				exerciseService.save(exercise);
				aux=3+(3*i);
				if(i<2) {
					lesson =unit.getLessons().get(i+1);
					exercise =exerciseService.findByLesson(lesson).get(0);
				}
			}

			return new ResponseEntity<>(true, HttpStatus.OK);
		} catch (IOException e) {
			return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	//Put unit
	@JsonView(UnitBassic.class)
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Unit> updateUnit(@PathVariable long id, @RequestBody Unit unitAct) {

		Unit unit = unitService.findById(id);

		if (unit != null) {
			unit.setName(unitAct.getName());
			unitAct.setId(id);
			unitService.save(unit);
			return new ResponseEntity<>(unit, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	//Delete Unit
	@JsonView(UnitBassic.class)
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Unit> deleteUnit(@PathVariable long id) {
			Unit unit = unitService.findById(id);
			if (unit != null) {
				List<Lesson> lessons = lessonService.findByUnit(unit);
				List<Exercise> exercises1 = exerciseService.findByLesson(lessons.get(0));
				List<Exercise> exercises2 = exerciseService.findByLesson(lessons.get(1));
				List<Exercise> exercises3 = exerciseService.findByLesson(lessons.get(2));
				for(int i=0;i<exercises1.size();i++) {
					exerciseService.delete(exercises1.get(i).getId());
					exerciseService.delete(exercises2.get(i).getId());
					exerciseService.delete(exercises3.get(i).getId());
				}
				for(int i=0;i<lessons.size();i++) {
					lessonService.delete(lessons.get(i).getId());
				}
				unitService.delete(unit.getId());
				return new ResponseEntity<>(unit,HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
	}
	/*@RequestMapping(value = "/{idunit}/Completed", method = RequestMethod.GET)
	public ResponseEntity<Boolean> completedLesson(@PathVariable int idunit) {
		User user = userComponent.getLoggedUser();
		
		Unit unit = unitService.findById(idunit);
		List<Lesson> lessons = lessonService.findByUnit(unit);
		// Get all ExerciseCompleted in the lesson and delete them (need to put wrong exercise last)
		int numExercisesCompleted1 = completedExerciseService.numExercisesCompleted(lessons.get(0).getId(), idunit, user);
		int numExercisesCompleted2 = completedExerciseService.numExercisesCompleted(lessons.get(1).getId(), idunit, user);
		int numExercisesCompleted3 = completedExerciseService.numExercisesCompleted(lessons.get(2).getId(), idunit, user);
		
		lessonService.completedLesson(user,(int)lessons.get(0).getId(), idunit, numExercisesCompleted1);
		lessonService.completedLesson(user, (int)lessons.get(1).getId(), idunit, numExercisesCompleted2);
		lessonService.completedLesson(user, (int)lessons.get(2).getId(), idunit, numExercisesCompleted3);
		int numExercisesCompleted = numExercisesCompleted1+numExercisesCompleted2+numExercisesCompleted3;
		//Update user data
		if (numExercisesCompleted == 12) {
			return new ResponseEntity<>(true, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(false, HttpStatus.OK);
		}
	}*/
}

/*
{
	"name": "Unidad Prueba"
}

*/
 
 
 
 /*
{
    "name": "Unidad 3",
    "lessons": [
        {
            "id": 7,
            "name": "Lección 1 Unidad 3",
            "exercises": [
            	{
            		"kind": 1,
            		"statement": "Enunciado 1",
            		"texts": [ "Opcion 1", "Opcion 2","Opcion 3" ],
            		"answer": {
            			"result": "dos"
            		}
            	},
            	{
            		"kind": 2,
            		"statement": "Enunciado 2",
            		"answer": {
            			"result": "Este texto es de prueba"
            		}
            	},
            	{
            		"kind": 5,
            		"statement": "Enunciado 3",
            		"texts": [ "Opcion 1", "Opcion 2", "Opcion 3" ],
            		"answer": {
            			"result": "uno"
            		}
            	},
            	{
            		"kind": 7,
            		"statement": "Enunciado 4",
            		"texts": [ "Opcion 1", "Opcion 2", "Opcion 3" ],
            		"answer": {
            			"result": "tres"
            		}
            	}
            ]
        },
        {
            "id": 8,
            "name": "Lección 2 Unidad 3",
            "exercises": [
            	{
            		"kind": 1,
            		"statement": "Enunciado 1",
            		"texts": [ "Opcion 1", "Opcion 2", "Opcion 3" ],
            		"answer": {
            			"result": "dos"
            		}
            	},
            	{
            		"kind": 2,
            		"statement": "Enunciado 2",
            		"answer": {
            			"result": "Este texto es de prueba"
            		}
            	},
            	{
            		"kind": 5,
            		"statement": "Enunciado 3",
            		"texts": [ "Opcion 1", "Opcion 2", "Opcion 3" ],
            		"answer": {
            			"result": "uno"
            		}
            	},
            	{
            		"kind": 7,
            		"statement": "Enunciado 4",
            		"texts": [ "Opcion 1", "Opcion 2", "Opcion 3" ],
            		"answer": {
            			"result": "tres"
            		}
            	}
            ]
        },
        {
            "id": 9,
            "name": "Lección 3 Unidad 3",
            "exercises": [
            	{
            		"kind": 1,
            		"statement": "Enunciado 1",
            		"texts": [ "Opcion 1", "Opcion 2", "Opcion 3" ],
            		"answer": {
            			"result": "dos"
            		}
            	},
            	{
            		"kind": 2,
            		"statement": "Enunciado 2",
            		"answer": {
            			"result": "Este texto es de prueba"
            		}
            	},
            	{
            		"kind": 5,
            		"statement": "Enunciado 3",
            		"texts": [ "Opcion 1", "Opcion 2", "Opcion 3" ],
            		"answer": {
            			"result": "uno"
            		}
            	},
            	{
            		"kind": 7,
            		"statement": "Enunciado 4",
            		"texts": [ "Opcion 1", "Opcion 2", "Opcion 3" ],
            		"answer": {
            			"result": "tres"
            		}
            	}
            ]
        }
    ]
}


*/

