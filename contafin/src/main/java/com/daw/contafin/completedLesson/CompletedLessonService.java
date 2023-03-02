package com.daw.contafin.completedLesson;

import java.util.Calendar;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import com.daw.contafin.completedExercise.CompletedExerciseService;
import com.daw.contafin.lesson.LessonDto;
import com.daw.contafin.lesson.LessonMapper;
import com.daw.contafin.lesson.LessonService;
import com.daw.contafin.unit.UnitService;
import com.daw.contafin.user.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.daw.contafin.lesson.Lesson;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
@Slf4j
@Transactional
public class CompletedLessonService {
	
	@Autowired
	CompletedLessonRepository completedLessonRepository;

	@Resource
	UserMapper userMapper;

	@Resource
	CompletedLessonMapper completedLessonMapper;

	@Resource
	LessonMapper lessonMapper;

	@Autowired
	UserComponent userComponent;

	@Autowired
	UnitService unitService;

	@Autowired
	LessonService lessonService;



	public int getCompletedLessons(UserDto userDto, Date date) {
		log.info("Calculo de lecciones completas por usuario en una fecha");
		int toReturn;
		try{
			User user = userMapper.UserDtoToUser(userDto);
			toReturn = completedLessonRepository.findByUserAndDate(user, date).size();
		}catch (Exception e){
			log.warn("Error al calcular las lecciones completas");
			toReturn = 0;
		}
		return toReturn;
	}

	/*public List<CompletedLessonDto> findByUserAndDate(UserDto userDto, Date date){
		log.info("Busqueda de lecciones completas por usuario y fecha");
		List<CompletedLessonDto> completedLessonDtos;
		try{
			User user = userMapper.UserDtoToUser(userDto);
			List<CompletedLesson> completedLesson = completedLessonRepository.findByUserAndDate(user, date);
			completedLessonDtos = completedLessonMapper.CompletedLessonsToCompletedLessonsDto(completedLesson);
		}catch (Exception e){
			log.info("Error al buscar las lecciones completas");
			completedLessonDtos = null;
		}
		return completedLessonDtos;
	}*/
	
	public Boolean existCompletedLesson(UserDto userDto, LessonDto lessonDto) {
		log.info("Busqueda de leccion completa por usuario y leccion");
		CompletedLesson completedLesson;
		try{
			User user = userMapper.UserDtoToUser(userDto);
			Lesson lesson = lessonMapper.LessonDtoToLesson(lessonDto);
			completedLesson = completedLessonRepository.findByUserAndLesson(user, lesson);
		}catch (Exception e){
			log.warn("Error al buscar la leccion completa");
			completedLesson = null;
		}
		return completedLesson != null;
	}
	
	public List<CompletedLessonDto> findByUser(UserDto userDto){
		log.info("Busqueda de lecciones completas por usuario: {}", userDto);
		List<CompletedLessonDto> completedLessonDtos;
		try{
			User user = userMapper.UserDtoToUser(userDto);
			List<CompletedLesson> completedLessons = completedLessonRepository.findByUser(user);
			completedLessonDtos = completedLessonMapper.CompletedLessonsToCompletedLessonsDto(completedLessons);
		}catch (Exception e){
			log.warn("Error al buscar las lecciones completas");
			completedLessonDtos = null;
		}
		return completedLessonDtos;
	}

	//Hacer test
	public List<CompletedLessonDto> findByUserOrderByDateDesc(UserDto userDto){
		log.info("Busqueda de lecciones completas por usuario y ordenadas por fecha en orden descendente: {}", userDto);
		List<CompletedLessonDto> completedLessonDtos;
		try{
			User user = userMapper.UserDtoToUser(userDto);
			List<CompletedLesson> completedLessons = completedLessonRepository.findByUserOrderByDateDesc(user);
			completedLessonDtos = completedLessonMapper.CompletedLessonsToCompletedLessonsDto(completedLessons);
		}catch (Exception e){
			log.warn("Error al buscar las lecciones completas");
			completedLessonDtos = null;
		}
		return completedLessonDtos;
	}

	public void save(CompletedLessonDto completedLessonDto) {
		log.info("Guardado de la leccion completa: {}", completedLessonDto);
		try{
			CompletedLesson completedLesson = completedLessonMapper.CompletedLessonDtoToCompletedLesson(completedLessonDto);
			completedLessonRepository.save(completedLesson);
		}catch (Exception e){
			log.warn("Error al guardar la leccion completa");
		}
	}

	public List<Boolean> checkList(Long idUnit){
		log.info("Comprobar las lecciones completas de la unidad con id: {}", idUnit);
		List<Boolean> booleans = new ArrayList<>();
		try{
			UserDto user = userComponent.getLoggedUser();
			List<LessonDto> lessons = unitService.findById(idUnit).getLessons();
			for (LessonDto lesson : lessons) {
				booleans.add(existCompletedLesson(user, lesson));
			}
		}catch (Exception e){
			log.warn("Error al buscar las lecciones completas");
			booleans = null;
		}
		return booleans;
	}

	public int numberOfCompletedLesson(Long idUnit){
		log.info("Numero de lecciones completas de la unidad con id: {}", idUnit);
		List<Boolean> booleans;
		int cont = 0;
		try{
			booleans = checkList(idUnit);
			for (Boolean aBoolean : booleans) {
				if(aBoolean){
					cont = cont +1;
				}
			}
		}catch (Exception e){
			log.warn("Error al calcular el numero de lecciones completas");
			cont = -1;
		}
		return cont;
	}

	/*public void delete(long Id) {
		log.info("Borrado de la leccion completa por el id: {}", Id);
		try{
			completedLessonRepository.deleteById(Id);
		}catch (Exception e){
			log.info("Error al borrar la leccion completa");
		}
	}*/
}
