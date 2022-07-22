package com.daw.contafin.unit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.daw.contafin.ImageService;
import com.daw.contafin.completedLesson.CompletedLesson;
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
@Slf4j
@Transactional
public class UnitRestController{
	
	
	@Autowired
	UnitService unitService;
	
	@Autowired
	LessonService lessonService;

	@Autowired
	ExerciseService exerciseService;
	
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
	@GetMapping(path = "/")
	@ResponseBody
	public ResponseEntity<List<Unit>> getUnit() {
		return new ResponseEntity<>(unitService.findAll(), HttpStatus.OK);
	}
	
	//Get 1 unit
	@JsonView(UnitBassic.class)
	@GetMapping(value = "/{id}")
	@ResponseBody
	public ResponseEntity<Unit> getOneUnit(@PathVariable long id) {
		Unit unit = unitService.findById(id);
		if (unit != null) {
			return new ResponseEntity<>(unit, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
		
	//Post Unit
	@PostMapping(value = "/")
	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Unit> createUnit(@RequestBody Unit unit) {
		
		if (unitService.isValidUnit(unit)) {
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
	
	@PostMapping(value = "/Exercise/{idExercise}/{nImage}")
	@ResponseBody
	public ResponseEntity<Boolean> profilePhoto(@PathVariable long idExercise, @PathVariable long nImage, @RequestParam("file") MultipartFile file) {
		if (!file.isEmpty()) {
			// Upload image
			byte[] bytes;
			try {
				bytes = imageService.uploadImage(file);
				Exercise exercise = exerciseService.findById(idExercise);
				if (nImage == 1) {
					exercise.setImage1(bytes);
				}
				if (nImage == 2) {
					exercise.setImage2(bytes);
				}
				if (nImage == 3) {
					exercise.setImage3(bytes);
				}
				exerciseService.save(exercise);
				return new ResponseEntity<>(true, HttpStatus.OK);
			} catch (IOException e) {
				return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} 
		else {
			return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
		}
	}
	
	//Show images
	@GetMapping(value = "/Exercise/{idExercise}/{nImage}")
	@ResponseBody
	public void sowImage(@PathVariable long idExercise, @PathVariable long nImage, HttpServletRequest request, HttpServletResponse response) throws IOException {
		Exercise exercise = exerciseService.findById(idExercise);
		imageService.showImageExercise(exercise, nImage, request, response);
	}
	
	@PostMapping(value = "/{id}/Images")
	@ResponseBody
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
	@PutMapping(value = "/{id}")
	@ResponseBody
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
	@GetMapping(value = "/{idunit}/isCompleted")
	@ResponseBody
	public ResponseEntity<Boolean> completedLesson(@PathVariable int idunit) {
		User user = userComponent.getLoggedUser();
		
		Unit unit = unitService.findById(idunit);
		List<Lesson> lessons = lessonService.findByUnit(unit);
		// Get all ExerciseCompleted in the lesson and delete them (need to put wrong exercise last)
		int count = 0;
		for(int i=0; i<lessons.size(); i++) {
			CompletedLesson completedLesson = completedLessonService.findByUserAndLesson(user, lessons.get(i));
			if(completedLesson != null) {
				count++;
			}
		}
				
		if (count == 3) {
			return new ResponseEntity<>(true, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(false, HttpStatus.OK);
		}
	}
	
	@GetMapping(value = "/{idunit}/numberOfCompletedLessons")
	@ResponseBody
	public ResponseEntity<Integer> numberOfCompletedLesson(@PathVariable int idunit) {
		User user = userComponent.getLoggedUser();
		
		Unit unit = unitService.findById(idunit);
		List<Lesson> lessons = lessonService.findByUnit(unit);
		// Get all ExerciseCompleted in the lesson and delete them (need to put wrong exercise last)
		int count = 0;
		for(int i=0; i<lessons.size(); i++) {
			CompletedLesson completedLesson = completedLessonService.findByUserAndLesson(user, lessons.get(i));
			if(completedLesson != null) {
				count++;
			}
		}
				
		return new ResponseEntity<>(count, HttpStatus.OK);
	}
	
	@GetMapping(value = "/numberOfCompletedLessons")
	@ResponseBody
	public ResponseEntity<ArrayList<Integer> > numberOfCompletedLessons() {
		User user = userComponent.getLoggedUser();
		
		List<Unit> units = unitService.findAll();
		List<Lesson> lessons;
		ArrayList<Integer> number = new ArrayList<Integer>(); 
		for (int j=0 ; j< units.size() ; j++) {
			lessons = lessonService.findByUnit(units.get(j));
			// Get all ExerciseCompleted in the lesson and delete them (need to put wrong exercise last)
			int count = 0;
			for(int i=0; i<lessons.size(); i++) {
				CompletedLesson completedLesson = completedLessonService.findByUserAndLesson(user, lessons.get(i));
				if(completedLesson != null) {
					count++;
				}
			}
			number.add(count);
		}
				
		return new ResponseEntity<>(number, HttpStatus.OK);
	}
	
	@GetMapping(value = "/NumberOfUnitsCompleted")
	@ResponseBody
	public ResponseEntity<List<Boolean>> Unitscompleted() {
		User user = userComponent.getLoggedUser();
		List<Boolean> booleanos = new ArrayList<Boolean>();
		List<Unit> units = unitService.findAll();
		for (int j = 0 ; j<units.size(); j++) {
			List<Lesson> lessons = lessonService.findByUnit(units.get(j));
			// Get all ExerciseCompleted in the lesson and delete them (need to put wrong exercise last)
			int count = 0;
			for(int i=0; i<lessons.size(); i++) {
				CompletedLesson completedLesson = completedLessonService.findByUserAndLesson(user, lessons.get(i));
				if(completedLesson != null) {
					count++;
				}
			}
					
			if (count == 3) {
				booleanos.add(true);
			} else {
				booleanos.add(false);
			}
		}
			return new ResponseEntity<>(booleanos, HttpStatus.OK);
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

