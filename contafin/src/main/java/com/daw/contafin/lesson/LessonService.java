package com.daw.contafin.lesson;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.daw.contafin.completedLesson.CompletedLessonDto;
import com.daw.contafin.unit.UnitDto;
import com.daw.contafin.unit.UnitMapper;
import com.daw.contafin.unit.UnitRepository;
import com.daw.contafin.user.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.daw.contafin.completedExercise.CompletedExerciseService;
import com.daw.contafin.completedLesson.CompletedLesson;
import com.daw.contafin.completedLesson.CompletedLessonService;
import com.daw.contafin.exercise.ExerciseService;
import com.daw.contafin.unit.Unit;
import com.daw.contafin.user.User;
import com.daw.contafin.user.UserComponent;
import com.daw.contafin.user.UserService;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
@Slf4j
@Transactional
public class LessonService {
	
	@Autowired
	LessonRepository lessonRepository;

	@Resource
	LessonMapper lessonMapper;
	
	public LessonDto findById (long id) {
		log.info("Busqueda de una leccion por el id: {} ", id);
		LessonDto lessonDto;
		try{
			Lesson lesson = lessonRepository.findById(id);
			lessonDto = lessonMapper.LessonToLessonDto(lesson);
		}catch (Exception e){
			log.warn("Error al buscar la leccion");
			lessonDto = null;
		}
		return lessonDto;
	}

	public void save(LessonDto lessonDto) {
		log.info("Guardado de la leccion: {}", lessonDto);
		try{
			Lesson lesson = lessonMapper.LessonDtoToLesson(lessonDto);
			lessonRepository.save(lesson);
		}catch (Exception e){
			log.warn("Error al guardar la leccion");
		}
	}
	
	public List<LessonDto> findAll(){
		log.info("Busqueda de la lista de lecciones");
		List<LessonDto> lessonDtos;
		try{
			List<Lesson> lessons = lessonRepository.findAll();
			lessonDtos = lessonMapper.LessonsToLessonsDto(lessons);
		}catch (Exception e){
			log.warn("Error al buscar las lecciones");
			lessonDtos = null;
		}
		return lessonDtos;
	}
	
	/*public void delete(long Id) {
		log.info("Borrado de la leccion por id: {}", Id);
		try{
			lessonRepository.deleteById(Id);
		}catch (Exception e){
			log.info("Error al borrar la leccion");
		}
	}
	public Page<Lesson> getLessons(Pageable page) {
		log.info("Busqueda del pageable de todas las lecciones");
		try{
			return lessonRepository.findAll(page);
		}catch (Exception e){
			log.info("Error al buscar las lecciones");
			return null;
		}

	}*/

}

