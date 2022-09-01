package com.daw.contafin.unit;

import java.util.ArrayList;
import java.util.List;

import com.daw.contafin.exercise.ExerciseDto;
import com.daw.contafin.lesson.LessonDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.daw.contafin.exercise.Exercise;
import com.daw.contafin.lesson.Lesson;

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
}
