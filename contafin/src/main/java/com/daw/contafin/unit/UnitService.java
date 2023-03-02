package com.daw.contafin.unit;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.daw.contafin.ImageService;
import com.daw.contafin.answer.Answer;
import com.daw.contafin.answer.AnswerDto;
import com.daw.contafin.answer.AnswerService;
import com.daw.contafin.exercise.ExerciseDto;
import com.daw.contafin.exercise.ExerciseService;
import com.daw.contafin.lesson.LessonDto;
import com.daw.contafin.lesson.LessonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.daw.contafin.exercise.Exercise;
import com.daw.contafin.lesson.Lesson;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.transaction.Transactional;


@Service
@Slf4j
@Transactional
public class UnitService {

	@Autowired
	UnitRepository unitRepository;

	@Autowired
	AnswerService answerService;

	@Autowired
	ImageService imageService;

	@Resource
	UnitMapper unitMapper;

	
	public List<UnitDto> findAll(){
		log.info("Busqueda de la lista unidades");
		List<UnitDto> unitDtos;
		try{
			List<Unit> units = unitRepository.findAll();
			unitDtos = unitMapper.UnitsToUnitsDto(units);
		}catch (Exception e){
			log.warn("Error al buscar las unidades");
			unitDtos = null;
		}
		return unitDtos;
	}
	
	public void save(UnitDto unitDto){
		log.info("Guardado de la unidad: {}", unitDto);
		try{
			Unit unit = unitMapper.UnitDtoToUnit(unitDto);
			unitRepository.save(unit);
		}catch (Exception e){
			log.warn("Error al guardar la unidad");
		}
	}
	
	public UnitDto findById(long Id) {
		log.info("Busqueda de unidad por id: {}", Id);
		UnitDto unitDto;
		try{
			Unit unit = unitRepository.findById(Id);
			unitDto = unitMapper.UnitToUnitDto(unit);
		}catch (Exception e){
			log.warn("Error al buscar la unidad");
			unitDto = null;
		}
		return unitDto;
	}

	public UnitDto findByLessonsId(long Id) {
		log.info("Busqueda de unidad por id de leccion: {}", Id);
		UnitDto unitDto;
		try{
			Unit unit = unitRepository.findByLessonsId(Id);
			unitDto = unitMapper.UnitToUnitDto(unit);
		}catch (Exception e){
			log.warn("Error al buscar la unidad");
			unitDto = null;
		}
		return unitDto;
	}

	public UnitDto saveUnitComplete(UnitDto unit) {
		try {
			for (LessonDto lesson : unit.getLessons()) {
				for (ExerciseDto exercise : lesson.getExercises()) {
					exercise.setAnswer(answerService.save(exercise.getAnswer()));
				}
			}
			save(unit);
		} catch (Exception e) {
			unit = null;
		}
		return unit;
	}

	/*public void delete(long Id) {
		log.info("Borrado de unidad por id: {}", Id);
		try{
			unitRepository.deleteById(Id);
		}catch (Exception e){
			log.info("Error al borrar la unidad");
		}
	}*/
	
	/*public boolean isValidUnit(UnitDto unitDto) {
		log.info("Comprobar que la unidad es valida");
		try{
			List<LessonDto> lessonDtos = unitDto.getLessons();

			if(unitDto.getName().isEmpty()) {
				return false;
			}

			List<ExerciseDto> exerciseDtos = unitDto.getLessons().get(0).getExercises();
			List<String> texts = unitDto.getLessons().get(0).getExercises().get(0).getTexts();
			for (int i=0; i<3; i++) {
				if(lessonDtos.get(i).getName().isEmpty()) {
					return false;
				}
				for (int j=0; j<4; j++) {
					if(exerciseDtos.get(j).getStatement().isEmpty()) {
						return false;
					}
					if(exerciseDtos.get(j).getAnswer().getResult().isEmpty()) {
						return false;
					}
					for (int k=0; k<3; j++) {
						if(texts.get(k).isEmpty()) {
							return false;
						}
					}
					texts = unitDto.getLessons().get(i+1).getExercises().get(j+1).getTexts();
				}
				exerciseDtos = unitDto.getLessons().get(i+1).getExercises();
			}
			return true;

		}catch (Exception e){
			log.info("Error al comprobar la unidad");
			return false;
		}

	}*/

	@PostConstruct
	public void init() throws IOException {

		List<UnitDto> unidades = findAll();
		if(unidades == null || unidades.isEmpty() ) {
			//Unit 1 Lesson 1
			//Exercise 1
			AnswerDto answer1 = new AnswerDto("uno");
			answer1 = answerService.save(answer1);
			List<String> texts = Arrays.asList("213.Maquinaria", "210.Terrenos y bienes naturales", "218. Elementos de transporte");
			ExerciseDto exercise1 = new ExerciseDto(1, "1.1.1 Seleccione el asiento", texts, answer1);
			// Save the images in the database
			imageService.saveImages(exercise1, Paths.get("img/machine.jpg"),
					Paths.get("img/land.jpg"),
					Paths.get("img/truck.jpg"));

			//Exercise 2
			AnswerDto answer2 = new AnswerDto("222.Respuesta");
			answer2 = answerService.save(answer2);
			ExerciseDto exercise2 = new ExerciseDto(2, "1.1.2 Escribe la denominación de la cuenta que recoge: " + "maquinarias para el proceso productivo de la empresa", null, answer2);

			//Exercise 5
			AnswerDto answer3 = new AnswerDto("tres");
			answer3 = answerService.save(answer3);
			texts = Arrays.asList("Activo", "Pasivo", "Patrimonio neto");
			ExerciseDto exercise3 = new ExerciseDto(5, "1.1.5 Escoge la respuesta correcta para la cuenta: 210. Terrenos y bienes naturales",
					texts, answer3);

			//Exercise 7
			AnswerDto answer4 = new AnswerDto("dos");
			answer4 = answerService.save(answer4);
			texts = Arrays.asList(
					" La empresa compra un local por 10, dejándolo a deber a su provedor, al que pagará integramente dentro de 10 años a través de la letra de cambio.",
					"La empresa compra un local por 10, dejándolo a deber a su provedor, al que pagará integramente dentro de 10 años.",
					" La empresa compra un local por 10, dejándolo a deber a su provedor, al que pagará en un plazo no superior a un año.");
			ExerciseDto exercise4 = new ExerciseDto(7, "1.1.7 Escoge el enunciado correcto para el asiento: \"10 211. Construcciones a 174. Provedores de inmovilizado a l/p 10\"",
					texts, answer4);


			List<ExerciseDto> exercises1 = new ArrayList<>();
			exercises1.add(exercise1);
			exercises1.add(exercise2);
			exercises1.add(exercise3);
			exercises1.add(exercise4);

			LessonDto lesson1 = new LessonDto("Lección 1 Unidad 1", exercises1);

			List<LessonDto> lessons1 = new ArrayList<>();
			lessons1.add(lesson1);

			UnitDto unit1 = new UnitDto();
			unit1.setName("Unidad 1");
			unit1.setLessons(lessons1);
			save(unit1);


		}
	}
}
