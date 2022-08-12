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

import javax.transaction.Transactional;


@Service
@Slf4j
@Transactional
public class UnitService {

	@Autowired
	UnitRepository unitRepository;

	@Autowired
	UnitMapper unitMapper;

	
	public List<UnitDto> findAll(){
		List<UnitDto> unitDtos = new ArrayList<>();
		try{
			List<Unit> units = unitRepository.findAll();
			unitDtos = unitMapper.UnitsToUnitsDto(units);
		}catch (Exception e){
			unitDtos = null;
		}
		return unitDtos;
	}
	
	public void save(UnitDto unitDto){
		try{
			Unit unit = unitMapper.UnitDtoToUnit(unitDto);
			unitRepository.save(unit);
		}catch (Exception e){

		}
	}
	
	public UnitDto findById(long Id) {
		UnitDto unitDto = new UnitDto();
		try{
			Unit unit = unitRepository.findById(Id);
			unitDto = unitMapper.UnitToUnitDto(unit);
		}catch (Exception e){
			unitDto = null;
		}
		return unitDto;
	}
	
	public void delete(long Id) {
		try{
			unitRepository.deleteById(Id);
		}catch (Exception e){

		}
	}
	
	public Page<Unit> getUnits(Pageable page){
		try{
			return unitRepository.findAll(page);
		}catch (Exception e){
			return null;
		}
	}
	
	public boolean isValidUnit(UnitDto unitDto) {
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
			return false;
		}

	}
}
