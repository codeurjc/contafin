package com.daw.contafin.service;

import java.util.List;
import com.daw.contafin.dto.LessonDto;
import com.daw.contafin.entity.Lesson;
import com.daw.contafin.repository.LessonRepository;
import com.daw.contafin.mapper.LessonMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public void delete(long id){
		log.info("Eliminar de la leccion con id: {}", id);
		try{
			lessonRepository.deleteById(id);
		}catch (Exception e){
			log.warn("Error al eliminar la leccion");
		}
	}

}

