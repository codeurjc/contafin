package com.daw.contafin.unit;

import java.util.List;


import javax.transaction.Transactional;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import com.daw.contafin.completedLesson.CompletedLessonService;

@RestController
@RequestMapping("/api/Unit")
@CrossOrigin(maxAge =3600)
@Slf4j
@Transactional
public class UnitRestController{
	
	
	@Autowired
	UnitService unitService;

	@Autowired
	CompletedLessonService completedLessonService;

	//Get all unit
	@GetMapping(path = "/")
	@ResponseBody
	public ResponseEntity<List<UnitDto>> getUnits() {
		log.info("Se ha recibido una solicitud para listar las unidades");
		ResponseEntity<List<UnitDto>> response;
		try{
			List<UnitDto> unitDtoList = unitService.findAll();
			response = new ResponseEntity<>(unitDtoList, HttpStatus.OK);
		}catch (Exception e){
			String error = "No se ha podido listar las unidades";
			log.warn(error,e);
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return response;
	}
	
	//Get 1 unit
	@GetMapping(value = "/{id}")
	@ResponseBody
	public ResponseEntity<UnitDto> getOneUnit(@PathVariable long id) {
		log.info("Se ha recibido una peticion para buscar la unidad con el id: {}",id);
		ResponseEntity<UnitDto> response;
		try{
			UnitDto unitDto = unitService.findById(id);
			response = new ResponseEntity<>(unitDto, HttpStatus.OK);
		}catch (Exception e){
			String error = "No se ha podido encontrar la unidad";
			log.warn(error,e);
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return response;
	}
		
	//Post Unit
	@PostMapping(value = "/")
	@ResponseBody
	public ResponseEntity<UnitDto> createUnit(@RequestBody UnitDto unit) {
		log.info("Se ha recibido una peticion para crear una nueva unidad");
		ResponseEntity<UnitDto> response;
		try{
			UnitDto unitDto = unitService.saveUnitComplete(unit);
			response = new ResponseEntity<>(unitDto, HttpStatus.OK);
		}catch (Exception e){
			String error = "No se ha podido crear la unidad";
			log.warn(error,e);
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return response;
	}

	@GetMapping(value = "/{id}/numberOfCompletedLessons")
	@ResponseBody
	public ResponseEntity<Integer> numberOfCompletedLesson(@PathVariable Long id) {
		log.info("Se ha recibido una peticion calcular el numero de lecciones completas de la unidad con id: {}", id);
		ResponseEntity<Integer> response;
		try{
			int n = completedLessonService.numberOfCompletedLesson(id);
			response = new ResponseEntity<>(n, HttpStatus.OK);
		}catch (Exception e){
			String error = "No se ha calcular";
			log.warn(error,e);
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return response;
	}

	/*@GetMapping(value = "/{idunit}/isCompleted")
	@ResponseBody
	public ResponseEntity<Boolean> completedLesson(@PathVariable int idunit) {
		UserDto user = userComponent.getLoggedUser();

		UnitDto unit = unitService.findById(idunit);
		List<LessonDto> lessons = unit.getLessons();
		int count = 0;
		for(int i=0; i<lessons.size(); i++) {
			Boolean b = completedLessonService.existCompletedLesson(user, lessons.get(i));
			if(b) {
				count++;
			}
		}

		if (count == 3) {
			return new ResponseEntity<>(true, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(false, HttpStatus.OK);
		}
	}*/
	
	/*@PostMapping(value = "/Exercise/{idExercise}/{nImage}")
	@ResponseBody
	public ResponseEntity<Boolean> profilePhoto(@PathVariable long idExercise, @PathVariable long nImage, @RequestParam("file") MultipartFile file) {
		if (!file.isEmpty()) {
			// Upload image
			byte[] bytes;
			try {
				bytes = imageService.uploadImage(file);
				ExerciseDto exercise = exerciseService.findById(idExercise);
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
	}*/
	
	//Show images
	/*@GetMapping(value = "/Exercise/{idExercise}/{nImage}")
	@ResponseBody
	public void sowImage(@PathVariable long idExercise, @PathVariable long nImage, HttpServletRequest request, HttpServletResponse response) throws IOException {
		ExerciseDto exercise = exerciseService.findById(idExercise);
		imageService.showImageExercise(exercise, nImage, request, response);
	}*/
	
	/*@PostMapping(value = "/{id}/Images")
	@ResponseBody
	public ResponseEntity<Boolean> addImages(@PathVariable long id, @RequestParam("images") MultipartFile [] images ) {
		UnitDto unit = unitService.findById(id);
		LessonDto lesson = unit.getLessons().get(0);
		ExerciseDto exercise = lesson.getExercises().get(0);
		// Upload images
		try {
			int aux= 0;
			for (int i=0; i< 3; i++) {
				exerciseService.uploadExerciseImages(exercise, images[aux], images[aux+1], images[aux+2]);
				exerciseService.save(exercise);
				aux=3+(3*i);
				if(i<2) {
					lesson =unit.getLessons().get(i+1);
					exercise = lesson.getExercises().get(0);
				}
			}

			return new ResponseEntity<>(true, HttpStatus.OK);
		} catch (IOException e) {
			return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}*/
	
	//Put unit
	/*@JsonView(UnitBassic.class)
	@PutMapping(value = "/{id}")
	@ResponseBody
	public ResponseEntity<UnitDto> updateUnit(@PathVariable long id, @RequestBody UnitDto unitAct) {

		UnitDto unit = unitService.findById(id);

		if (unit != null) {
			unit.setName(unitAct.getName());
			unitAct.setId(id);
			unitService.save(unit);
			return new ResponseEntity<>(unit, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}*/

	//Delete Unit
	/*@JsonView(UnitBassic.class)
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<UnitDto> deleteUnit(@PathVariable long id) {
			UnitDto unit = unitService.findById(id);
			if (unit != null) {
				List<LessonDto> lessons = lessonService.findByUnit(unit);
				List<ExerciseDto> exercises1 = lessons.get(0).getExercises();
				List<ExerciseDto> exercises2 = lessons.get(1).getExercises();
				List<ExerciseDto> exercises3 = lessons.get(2).getExercises();
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
	}*/
		
}