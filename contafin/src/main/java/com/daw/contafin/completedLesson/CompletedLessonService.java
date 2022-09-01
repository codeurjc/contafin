package com.daw.contafin.completedLesson;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.daw.contafin.lesson.LessonDto;
import com.daw.contafin.lesson.LessonMapper;
import com.daw.contafin.user.UserDto;
import com.daw.contafin.user.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw.contafin.lesson.Lesson;
import com.daw.contafin.user.User;

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


	public int getCompletedLessons(UserDto userDto, Date date) {
		log.info("Calculo de lecciones completas por usuario en una fecha");
		int toReturn;
		try{
			User user = userMapper.UserDtoToUser(userDto);
			toReturn = completedLessonRepository.findByUserAndDate(user, date).size();
		}catch (Exception e){
			log.info("Error al calcular las lecciones completas");
			toReturn = 0;
		}
		return toReturn;
	}

	public List<CompletedLessonDto> findByUserAndDate(UserDto userDto, Date date){
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
	}
	
	public CompletedLessonDto findByUserAndLesson(UserDto userDto, LessonDto lessonDto) {
		log.info("Busqueda de leccion completa por usuario y leccion");
		CompletedLessonDto completedLessonDto;
		try{
			User user = userMapper.UserDtoToUser(userDto);
			Lesson lesson = lessonMapper.LessonDtoToLesson(lessonDto);
			CompletedLesson completedLesson = completedLessonRepository.findByUserAndLesson(user, lesson);
			completedLessonDto = completedLessonMapper.CompletedLessonToCompletedLessonDto(completedLesson);
		}catch (Exception e){
			log.info("Error al buscar la leccion completa");
			completedLessonDto = null;
		}
		return completedLessonDto;
	}
	
	public List<CompletedLessonDto> findByUser(UserDto userDto){
		log.info("Busqueda de lecciones completas por usuario: {}", userDto);
		List<CompletedLessonDto> completedLessonDtos;
		try{
			User user = userMapper.UserDtoToUser(userDto);
			List<CompletedLesson> completedLessons = completedLessonRepository.findByUser(user);
			completedLessonDtos = completedLessonMapper.CompletedLessonsToCompletedLessonsDto(completedLessons);
		}catch (Exception e){
			log.info("Error al buscar las lecciones completas");
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
			log.info("Error al guardar la leccion completa");
		}
	}

	public void delete(long Id) {
		log.info("Borrado de la leccion completa por el id: {}", Id);
		try{
			completedLessonRepository.deleteById(Id);
		}catch (Exception e){
			log.info("Error al borrar la leccion completa");
		}
	}
}
