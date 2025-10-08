package com.daw.contafin.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.daw.contafin.dto.AnswerDto;
import com.daw.contafin.dto.UnitDto;
import com.daw.contafin.entity.Unit;
import com.daw.contafin.dto.ExerciseDto;
import com.daw.contafin.dto.LessonDto;
import com.daw.contafin.mapper.UnitMapper;
import com.daw.contafin.repository.UnitRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw.contafin.entity.Exercise;
import com.daw.contafin.entity.Lesson;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.transaction.Transactional;


@Service
@Slf4j
@Transactional
public class UnitService {

	@Autowired
	UnitRepository unitRepository;

	@Resource
	UnitMapper unitMapper;

	@Autowired
	ExerciseService exerciseService;

	@Autowired
	LessonService lessonService;


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

	public List<UnitDto> findAll(){
		log.info("Busqueda de la lista unidades");
		List<UnitDto> unitDtos;
		try{
			List<Unit> units = (List<Unit>) unitRepository.findAll();
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

	public void delete(long id){
		log.info("Eliminar de la unidad con id: {}", id);
		try{
			unitRepository.deleteById(id);
		}catch (Exception e){
			log.warn("Error al eliminar la unidad");
		}
	}

	public UnitDto saveUnitComplete(UnitDto unit) {
		try {
			ArrayList<Long> exerciseCheck = new ArrayList<>();
			ArrayList<Long> lessonCheck = new ArrayList<>();
			
			Unit unitCheck = unitRepository.findById(unit.getId());



			if(unitCheck !=null) {
				for (Lesson lesson : unitCheck.getLessons()) {
					lessonCheck.add(lesson.getId());
					for (Exercise exercise : lesson.getExercises()) {
						exerciseCheck.add(exercise.getId());
					}
				}


				for (LessonDto lesson : unit.getLessons()) {
					if(lesson.getId() != null){
						if (lessonCheck.contains(lesson.getId())) {
							lessonCheck.remove(lesson.getId());
						}
						for (ExerciseDto exercise : lesson.getExercises()) {
							if(exercise.getId() != null){
								if (exerciseCheck.contains(exercise.getId())) {
									exerciseCheck.remove(exercise.getId());
								}
							}
						}
					}
				}

				for (Long id : exerciseCheck) {
					exerciseService.delete(id);
				}

				for (Long id : lessonCheck) {
					lessonService.delete(id);
				}


			}

			save(unit);

		} catch (Exception e) {
			unit = null;
		}
		return unit;
	}

	@PostConstruct
	public void init() throws IOException {

		List<UnitDto> unidades = findAll();
		if(unidades == null || unidades.isEmpty() ) {
			//Unit 1 Lesson 1
			//Exercise 1
			AnswerDto answer1 = new AnswerDto("uno");
			List<String> texts = Arrays.asList("213.Maquinaria", "210.Terrenos y bienes naturales", "218. Elementos de transporte");
			ExerciseDto exercise1 = new ExerciseDto(1, "1.1.1 Seleccione el elemento para maquinaria industrial", texts, answer1);
			// Save the images in the database
			byte []image = Files.readAllBytes(Paths.get("img/machine.jpg"));

			exercise1.setImage1(image);
			image = Files.readAllBytes(Paths.get("img/land.jpg"));
			exercise1.setImage2(image);
			image = Files.readAllBytes(Paths.get("img/truck.jpg"));
			exercise1.setImage3(image);

			//Exercise 2
			AnswerDto answer2 = new AnswerDto("213.Maquinaria");
			ExerciseDto exercise2 = new ExerciseDto(2, "1.1.2 Escribe la denominación de la cuenta que recoge: " + "maquinarias para el proceso productivo de la empresa", null, answer2);

			//Exercise 5
			AnswerDto answer3 = new AnswerDto("tres");
			texts = Arrays.asList("Activo", "Pasivo", "Patrimonio neto");
			ExerciseDto exercise3 = new ExerciseDto(5, "1.1.5 Escoge la respuesta correcta para la cuenta: 210. Terrenos y bienes naturales",
					texts, answer3);

			//Exercise 7
			AnswerDto answer4 = new AnswerDto("dos");
			texts = Arrays.asList(
					" La empresa compra un local por 10, dejándolo a deber a su provedor, al que pagará integramente dentro de 10 años a través de la letra de cambio.",
					"La empresa compra un local por 10, dejándolo a deber a su provedor, al que pagará integramente dentro de 10 años.",
					" La empresa compra un local por 10, dejándolo a deber a su provedor, al que pagará en un plazo no superior a un año.");
			ExerciseDto exercise4 = new ExerciseDto(7, "1.1.7 Escoge el enunciado correcto para el asiento: \"10 211. Construcciones a 174. Provedores de inmovilizado a l/p 10\"",
					texts, answer4);

			texts = Arrays.asList(
					"400.Proveedores:206.Aplicaciones informáticas:213.Maquinaria:300.Mercaderías A:100.Cápital social",
			"Pasivo exigible:Inmovilizado intangible:Inmovilizado material:Existencias:Patrimonio neto");
			AnswerDto answer5 = new AnswerDto("400.Proveedores:Pasivo exigible/" +
					"206.Aplicaciones informáticas:Inmovilizado intangible/" +
					"213.Maquinaria:Inmovilizado material/300.Mercaderías A:" +
					"Existencias/100.Cápital social:Patrimonio neto");
			ExerciseDto exercise5 = new ExerciseDto(3, "Toca los pares: ", texts, answer5);

			AnswerDto answer32 = new AnswerDto("tres");
			texts = Arrays.asList("Activo", "Pasivo", "Patrimonio neto");
			ExerciseDto exercise32 = new ExerciseDto(5, "1.1.5 Escoge la respuesta correcta para la cuenta: 210. Terrenos y bienes naturales",
					texts, answer32);

			List<ExerciseDto> exercises1 = new ArrayList<>();
			exercises1.add(exercise1);
			exercises1.add(exercise2);
			exercises1.add(exercise3);
			exercises1.add(exercise4);

			List<ExerciseDto> exercises12 = new ArrayList<>();
			exercises12.add(exercise32);

			List<ExerciseDto> exercises2 = new ArrayList<>();
			exercises2.add(exercise5);


			LessonDto lesson1 = new LessonDto("Lección 1 Unidad 1", exercises1);
			LessonDto lesson2 = new LessonDto("Lección 1 Unidad 2", exercises2);
			LessonDto lesson12 = new LessonDto("Lección 2 Unidad 1", exercises12);

			List<LessonDto> lessons2 = new ArrayList<>();
			lessons2.add(lesson12);

			List<LessonDto> lessons1 = new ArrayList<>();
			lessons1.add(lesson1);
			lessons1.add(lesson2);


			UnitDto unit1 = new UnitDto();
			unit1.setName("Unidad 1");
			unit1.setLessons(lessons1);
			save(unit1);

			UnitDto unit2 = new UnitDto();
			unit2.setName("Unidad 2");
			unit2.setLessons(lessons2);
			save(unit2);
		}
	}
}
