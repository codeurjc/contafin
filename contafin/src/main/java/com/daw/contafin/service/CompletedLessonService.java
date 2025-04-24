package com.daw.contafin.service;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import com.daw.contafin.dto.*;
import com.daw.contafin.entity.CompletedLesson;
import com.daw.contafin.entity.Unit;
import com.daw.contafin.entity.User;
import com.daw.contafin.mapper.CompletedLessonMapper;
import com.daw.contafin.mapper.LessonMapper;
import com.daw.contafin.repository.CompletedLessonRepository;
import com.daw.contafin.mapper.UserMapper;
import com.daw.contafin.config.jwt.UserDetailsImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.daw.contafin.entity.Lesson;

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
	UnitService unitService;



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

	public void save(CompletedLessonDto completedLessonDto) {
		log.info("Guardado de la leccion completa: {}", completedLessonDto);
		try{
			CompletedLesson completedLesson = completedLessonMapper.CompletedLessonDtoToCompletedLesson(completedLessonDto);
			completedLessonRepository.save(completedLesson);
		}catch (Exception e){
			log.warn("Error al guardar la leccion completa");
		}
	}

	public Boolean existCompletedLesson(UserDto userDto, LessonDto lessonDto) {
		log.info("Busqueda de leccion completa por usuario y leccion");
		List<CompletedLesson> completedLesson;
		try{
			Lesson lesson = lessonMapper.LessonDtoToLesson(lessonDto);
			User user = userMapper.UserDtoToUser(userDto);
			completedLesson = completedLessonRepository.findByUserAndLesson(user, lesson);
		}catch (Exception e){
			log.warn("Error al buscar la leccion completa");
			return false;
		}
		return completedLesson != null && !completedLesson.isEmpty();
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

	public List<Boolean> checkList(Long idUnit){
		log.info("Comprobar las lecciones completas de la unidad con id: {}", idUnit);
		List<Boolean> booleans = new ArrayList<>();
		try{
			UserDto user = null;
			List<LessonDto> lessons = unitService.findById(idUnit).getLessons();
			if(!SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString().contains("ROLE_ANONYMOUS")) {
				user = ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
			}
			for (LessonDto lesson : lessons) {
				if(user != null){
					booleans.add(existCompletedLesson(user, lesson));
				}else{
					booleans.add(false);
				}
			}
		}catch (Exception e){
			log.warn("Error al buscar las lecciones completas");
			booleans = null;
		}
		return booleans;
	}

	public UnitHomeDto topLesson(){
		log.info("Busqueda de la lista unidades");
		Long idUnit = 0L;
		int nCompleted = 0;
		List<Boolean> booleans;
		UnitHomeDto unitDtos = new UnitHomeDto();
		try{
			List<UnitDto> units = unitService.findAll();
			for (UnitDto unit : units) {
				nCompleted = 0;
				booleans = checkList(unit.getId());
					idUnit = unit.getId();
					for (Boolean aBoolean : booleans) {
						if(aBoolean){
							nCompleted++;
						}
					}
				if(booleans.contains(Boolean.FALSE)){
					break;
				}
			}
			unitDtos.setUnitId(idUnit);
			unitDtos.setNCompleted(nCompleted);
		}catch (Exception e){
			log.warn("Error al buscar las unidades");
			unitDtos = null;
		}
		return unitDtos;
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

	public int totalNumberOfCompletedLesson(){
		log.info("Numero de lecciones completas");
		List<Boolean> booleans;
		int cont = 0;
		try{
			for (UnitDto unitDto : unitService.findAll()) {
				booleans = checkList(unitDto.getId());
				for (Boolean aBoolean : booleans) {
					if(aBoolean){
						cont = cont +1;
					}
				}
			}
		}catch (Exception e){
			log.warn("Error al calcular el numero de lecciones completas");
			cont = -1;
		}
		return cont;
	}
}
