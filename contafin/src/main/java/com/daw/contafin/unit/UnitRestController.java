package com.daw.contafin.unit;

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
import com.daw.contafin.answer.AnswerService;
import com.daw.contafin.exercise.Exercise;
import com.daw.contafin.exercise.ExerciseService;
import com.daw.contafin.lesson.Lesson;
import com.daw.contafin.lesson.LessonService;
import com.daw.contafin.unit.Unit.UnitBassic;
import com.daw.contafin.user.UserComponent;
import com.daw.contafin.user.UserService;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping("/api/Unit")
public class UnitRestController{
	
	
	@Autowired
	private UnitService unitService;
	
	@Autowired
	private LessonService lessonService;

	@Autowired
	private ExerciseService exerciseService;
	
	@Autowired
	private AnswerService answerService;
	
	@Autowired
	UserService userService;

	@Autowired
	UserComponent userComponent;

	@Autowired
	ImageService imageService;

	byte[] bytes1;
	byte[] bytes2;
	byte[] bytes3;
	Exercise exercise;

	//Get all unit
	@JsonView(UnitBassic.class)
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<Unit> getUnit() {
		return unitService.findAll();
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
	public Unit createUnit(@RequestBody Unit unit) {
		
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
		
		return unit;
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

