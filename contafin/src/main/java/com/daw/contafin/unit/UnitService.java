package com.daw.contafin.unit;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.daw.contafin.ImageService;
import com.daw.contafin.answer.Answer;
import com.daw.contafin.answer.AnswerDto;
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
	LessonService lessonService;

	@Autowired
	ImageService imageService;

	@Autowired
	ExerciseService exerciseService;

	@Resource
	UnitMapper unitMapper;

	
	public List<UnitDto> findAll(){
		log.info("Busqueda de la lista unidades");
		List<UnitDto> unitDtos;
		try{
			List<Unit> units = unitRepository.findAll();
			unitDtos = unitMapper.UnitsToUnitsDto(units);
		}catch (Exception e){
			log.info("Error al buscar las unidades");
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
			log.info("Error al guardar la unidad");
		}
	}
	
	public UnitDto findById(long Id) {
		log.info("Busqueda de unidad por id: {}", Id);
		UnitDto unitDto;
		try{
			Unit unit = unitRepository.findById(Id);
			unitDto = unitMapper.UnitToUnitDto(unit);
		}catch (Exception e){
			log.info("Error al buscar la unidad");
			unitDto = null;
		}
		return unitDto;
	}
	
	public void delete(long Id) {
		log.info("Borrado de unidad por id: {}", Id);
		try{
			unitRepository.deleteById(Id);
		}catch (Exception e){
			log.info("Error al borrar la unidad");
		}
	}
	
	public Page<Unit> getUnits(Pageable page){
		log.info("Borrado de las paginas de unidades");
		try{
			return unitRepository.findAll(page);
		}catch (Exception e){
			log.info("Error al buscar las paginas de unidades");
			return null;
		}
	}
	
	public boolean isValidUnit(UnitDto unitDto) {
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

	}

	@PostConstruct
	public void init() throws IOException {

		List<UnitDto> unidades = findAll();
		if(unidades == null || unidades.isEmpty() ) {
			UnitDto unit;
			unit = new UnitDto();
			unit.setName("Unidad 1");
			save(unit);
			unit = new UnitDto();
			unit.setName("Unidad 2");
			save(unit);

			unit = findById(1);
			LessonDto lesson1 = new LessonDto("Lección 1 Unidad 1", unit);
			lessonService.save(lesson1);
			LessonDto lesson2 = new LessonDto("Lección 2 Unidad 1", unit);
			lessonService.save(lesson2);
			LessonDto lesson3 = new LessonDto("Lección 3 Unidad 1", unit);
			lessonService.save(lesson3);

			unit = findById(2);
			LessonDto lesson4 = new LessonDto("Lección 1 Unidad 2", unit);
			lessonService.save(lesson4);
			LessonDto lesson5 = new LessonDto("Lección 2 Unidad 2", unit);
			lessonService.save(lesson5);
			LessonDto lesson6 = new LessonDto("Lección 3 Unidad 2", unit);
			lessonService.save(lesson6);

			//Unit 1 Lesson 1
			LessonDto lesson = lessonService.findById(1);

			//Exercise 1
			AnswerDto answer = new AnswerDto("uno");
			List<String> texts = Arrays.asList("213.Maquinaria", "210.Terrenos y bienes naturales", "218. Elementos de transporte");
			ExerciseDto exercise = new ExerciseDto(1, "1.1.1 Seleccione el asiento", texts, answer, lesson);
			// Save the images in the database
			imageService.saveImages(exercise, Paths.get("img/machine.jpg"),
					Paths.get("img/land.jpg"),
					Paths.get("img/truck.jpg"));

			//Exercise 2
			answer = new AnswerDto("Este es un texto de prueba en el que comprobarlo");
			exerciseService.save(new ExerciseDto(2, "1.1.2 Escribe la denominación de la cuenta que recoge: "
					+ "maquinarias para el proceso productivo de la empresa", null, answer, lesson));


			//Exercise 5
			answer = new AnswerDto("tres");
			texts = Arrays.asList("Activo", "Pasivo", "Patrimonio neto");
			exerciseService.save(
					new ExerciseDto(5, "1.1.5 Escoge la respuesta correcta para la cuenta: 210. Terrenos y bienes naturales",
							texts, answer, lesson));

			////Exercise 7
			answer = new AnswerDto("dos");
			texts = Arrays.asList(
					" La empresa compra un local por 10, dejándolo a deber a su provedor, al que pagará integramente dentro de 10 años a través de la letra de cambio.",
					"La empresa compra un local por 10, dejándolo a deber a su provedor, al que pagará integramente dentro de 10 años.",
					" La empresa compra un local por 10, dejándolo a deber a su provedor, al que pagará en un plazo no superior a un año.");
			exerciseService.save(new ExerciseDto(7,
					"1.1.7 Escoge el enunciado correcto para el asiento: \"10 211. Construcciones a 174. Provedores de inmovilizado a l/p 10\"",
					texts, answer, lesson));

			//Unit 1 Lesson 2
			lesson = lessonService.findById(2);

			//Exercise 1
			answer = new AnswerDto("uno");
			texts = Arrays.asList("213.Maquinaria", "210.Terrenos y bienes naturales", "218. Elementos de transporte");
			exercise = new ExerciseDto(1, "1.1.1 Seleccione el asiento", texts, answer, lesson);
			//Save the images in the database
			imageService.saveImages(exercise, Paths.get("img/machine.jpg"),
					Paths.get("img/land.jpg"),
					Paths.get("img/truck.jpg"));

			//Exercixe 2
			answer = new AnswerDto("Este es un texto de prueba en el que comprobarlo");
			exerciseService.save(new ExerciseDto(2, "1.2.2 Escribe la denominación de la cuenta que recoge: "
					+ "maquinarias para el proceso productivo de la empresa", null, answer, lesson));


			//Exercise 5
			answer = new AnswerDto("uno");
			texts = Arrays.asList("Activo", "Pasivo", "Patrimonio neto");
			exerciseService.save(
					new ExerciseDto(5, "1.2.5 Escoge la respuesta correcta para la cuenta: 210. Terrenos y bienes naturales",
							texts, answer, lesson));


			////Exercise 7
			answer = new AnswerDto("uno");
			texts = Arrays.asList(
					" La empresa compra un local por 10, dejándolo a deber a su provedor, al que pagará integramente dentro de 10 años a través de la letra de cambio.",
					"La empresa compra un local por 10, dejándolo a deber a su provedor, al que pagará integramente dentro de 10 años.",
					" La empresa compra un local por 10, dejándolo a deber a su provedor, al que pagará en un plazo no superior a un año.");
			exerciseService.save(new ExerciseDto(7,
					"1.2.7 Escoge el enunciado correcto para el asiento: \"10 211. Construcciones a 174. Provedores de inmovilizado a l/p 10\"",
					texts, answer, lesson));

			//Unit 1 Lesson 3
			lesson = lessonService.findById(3);

			//Exercise 1
			answer = new AnswerDto("uno");
			texts = Arrays.asList("213.Maquinaria", "210.Terrenos y bienes naturales", "218. Elementos de transporte");
			exercise = new ExerciseDto(1, "1.3.1 Seleccione el asiento",  texts, answer, lesson);
			//Save the images in the database
			imageService.saveImages(exercise, Paths.get("img/machine.jpg"),
					Paths.get("img/land.jpg"),
					Paths.get("img/truck.jpg"));

			//Exercise 2
			answer = new AnswerDto("Este es un texto de prueba en el que comprobarlo");
			exerciseService.save(new ExerciseDto(2, "1.3.2 Escribe la denominación de la cuenta que recoge: "
					+ "maquinarias para el proceso productivo de la empresa", null, answer, lesson));

			//Exercise 5
			answer = new AnswerDto("uno");
			texts = Arrays.asList("Activo", "Pasivo", "Patrimonio neto");
			exerciseService.save(
					new ExerciseDto(5, "1.3.5 Escoge la respuesta correcta para la cuenta: 210. Terrenos y bienes naturales",
							texts, answer, lesson));

			//Exercise 7
			answer = new AnswerDto("uno");
			texts = Arrays.asList(
					" La empresa compra un local por 10, dejándolo a deber a su provedor, al que pagará integramente dentro de 10 años a través de la letra de cambio.",
					"La empresa compra un local por 10, dejándolo a deber a su provedor, al que pagará integramente dentro de 10 años.",
					" La empresa compra un local por 10, dejándolo a deber a su provedor, al que pagará en un plazo no superior a un año.");
			exerciseService.save(new ExerciseDto(7,
					"1.3.7 Escoge el enunciado correcto para el asiento: \"10 211. Construcciones a 174. Provedores de inmovilizado a l/p 10\"",
					texts, answer, lesson));

			//Unit 2 Lesson 1
			lesson = lessonService.findById(4);

			//Exercise 1
			answer = new AnswerDto("uno");
			texts = Arrays.asList("213.Maquinaria", "210.Terrenos y bienes naturales", "218. Elementos de transporte");
			exercise =new ExerciseDto(1, "2.1.1 Seleccione el asiento", texts, answer, lesson);
			//Save the images in the database
			imageService.saveImages(exercise, Paths.get("img/machine.jpg"),
					Paths.get("img/land.jpg"),
					Paths.get("img/truck.jpg"));

			//Exercise 2
			answer = new AnswerDto("Este es un texto de prueba en el que comprobarlo");
			exerciseService.save(new ExerciseDto(2, "2.1.2 Escribe la denominación de la cuenta que recoge: "
					+ "maquinarias para el proceso productivo de la empresa", null, answer, lesson));

			//Exercise 5
			answer = new AnswerDto("uno");
			texts = Arrays.asList("Activo", "Pasivo", "Patrimonio neto");
			exerciseService.save(
					new ExerciseDto(5, "2.1.5 Escoge la respuesta correcta para la cuenta: 210. Terrenos y bienes naturales",
							texts, answer, lesson));

			//Exercise 7
			answer = new AnswerDto("uno");
			texts = Arrays.asList(
					" La empresa compra un local por 10, dejándolo a deber a su provedor, al que pagará integramente dentro de 10 años a través de la letra de cambio.",
					"La empresa compra un local por 10, dejándolo a deber a su provedor, al que pagará integramente dentro de 10 años.",
					" La empresa compra un local por 10, dejándolo a deber a su provedor, al que pagará en un plazo no superior a un año.");
			exerciseService.save(new ExerciseDto(7,
					"2.1.7 Escoge el enunciado correcto para el asiento: \"10 211. Construcciones a 174. Provedores de inmovilizado a l/p 10\"",
					texts, answer, lesson));

			//Unit 2 Lesson 2
			lesson = lessonService.findById(5);

			//Exercise 1
			answer = new AnswerDto("uno");
			texts = Arrays.asList("213.Maquinaria", "210.Terrenos y bienes naturales", "218. Elementos de transporte");
			exercise =new ExerciseDto(1, "2.2.1 Seleccione el asiento", texts, answer, lesson);
			//Save the images in the database
			imageService.saveImages(exercise, Paths.get("img/machine.jpg"),
					Paths.get("img/land.jpg"),
					Paths.get("img/truck.jpg"));

			//Exercise 2
			answer = new AnswerDto("Este es un texto de prueba en el que comprobarlo");
			exerciseService.save(new ExerciseDto(2, "2.2.2 Escribe la denominación de la cuenta que recoge: "
					+ "maquinarias para el proceso productivo de la empresa", null, answer, lesson));


			//Exercise 5
			answer = new AnswerDto("uno");
			texts = Arrays.asList("Activo", "Pasivo", "Patrimonio neto");
			exerciseService.save(
					new ExerciseDto(5, "2.2.5 Escoge la respuesta correcta para la cuenta: 210. Terrenos y bienes naturales",
							texts, answer, lesson));

			//Exercise 7
			answer = new AnswerDto("uno");
			texts = Arrays.asList(
					" La empresa compra un local por 10, dejándolo a deber a su provedor, al que pagará integramente dentro de 10 años a través de la letra de cambio.",
					"La empresa compra un local por 10, dejándolo a deber a su provedor, al que pagará integramente dentro de 10 años.",
					" La empresa compra un local por 10, dejándolo a deber a su provedor, al que pagará en un plazo no superior a un año.");
			exerciseService.save(new ExerciseDto(7,
					"2.2.7 Escoge el enunciado correcto para el asiento: \"10 211. Construcciones a 174. Provedores de inmovilizado a l/p 10\"",
					texts, answer, lesson));

			//Unit 2 Lesson 3
			lesson = lessonService.findById(6);

			//Exercise 1
			answer = new AnswerDto("uno");
			texts = Arrays.asList("213.Maquinaria", "210.Terrenos y bienes naturales", "218. Elementos de transporte");
			exercise = new ExerciseDto(1, "2.3.1 Seleccione el asiento", texts, answer, lesson);
			// Save the images in the database
			imageService.saveImages(exercise, Paths.get("img/machine.jpg"),
					Paths.get("img/land.jpg"),
					Paths.get("img/truck.jpg"));

			//Exercise 2
			answer = new AnswerDto("Este es un texto de prueba en el que comprobarlo");
			exerciseService.save(new ExerciseDto(2, "2.3.2 Escribe la denominación de la cuenta que recoge: "
					+ "maquinarias para el proceso productivo de la empresa", null, answer, lesson));


			//Exercise 5
			answer = new AnswerDto("uno");
			texts = Arrays.asList("Activo", "Pasivo", "Patrimonio neto");
			exerciseService.save(
					new ExerciseDto(5, "2.3.5 Escoge la respuesta correcta para la cuenta: 210. Terrenos y bienes naturales",
							texts, answer, lesson));

			//Exercise 7
			answer = new AnswerDto("uno");
			texts = Arrays.asList(
					" La empresa compra un local por 10, dejándolo a deber a su provedor, al que pagará integramente dentro de 10 años a través de la letra de cambio.",
					"La empresa compra un local por 10, dejándolo a deber a su provedor, al que pagará integramente dentro de 10 años.",
					" La empresa compra un local por 10, dejándolo a deber a su provedor, al que pagará en un plazo no superior a un año.");
			exerciseService.save(new ExerciseDto(7,
					"2.3.7 Escoge el enunciado correcto para el asiento: \"10 211. Construcciones a 174. Provedores de inmovilizado a l/p 10\"",
					texts, answer, lesson));

		}


	}
}
