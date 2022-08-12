package com.daw.contafin.lesson;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.daw.contafin.completedLesson.CompletedLessonDto;
import com.daw.contafin.unit.UnitDto;
import com.daw.contafin.unit.UnitMapper;
import com.daw.contafin.user.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.daw.contafin.completedExercise.CompletedExerciseService;
import com.daw.contafin.completedLesson.CompletedLesson;
import com.daw.contafin.completedLesson.CompletedLessonService;
import com.daw.contafin.exercise.ExerciseService;
import com.daw.contafin.unit.Unit;
import com.daw.contafin.user.User;
import com.daw.contafin.user.UserComponent;
import com.daw.contafin.user.UserService;

import javax.transaction.Transactional;

@Service
@Slf4j
@Transactional
public class LessonService {
	
	@Autowired
	LessonRepository lessonRepository;
	
	@Autowired
	ExerciseService exerciseService;
	
	@Autowired
	CompletedLessonService completedLessonService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserComponent userComponent;

	@Autowired
	LessonMapper lessonMapper;

	@Autowired
	UnitMapper unitMapper;
	
	public LessonDto findById (long id) {
		LessonDto lessonDto = new LessonDto();
		try{
			Lesson lesson = lessonRepository.findById(id);
			lessonDto = lessonMapper.LessonToLessonDto(lesson);
		}catch (Exception e){
			lessonDto = null;
		}
		return lessonDto;
	}
	public List<LessonDto> findByUnit(UnitDto unitDto){
		List<LessonDto> lessonDtos = new ArrayList<>();
		try{
			Unit unit = unitMapper.UnitDtoToUnit(unitDto);
			List<Lesson> lessons = lessonRepository.findByUnit(unit);
			lessonDtos = lessonMapper.LessonsToLessonsDto(lessons);
		}catch (Exception e){
			lessonDtos = null;
		}
		return lessonDtos;
	}
	public void save(LessonDto lessonDto) {
		try{
			Lesson lesson = lessonMapper.LessonDtoToLesson(lessonDto);
			lessonRepository.save(lesson);
		}catch (Exception e){

		}
	}
	
	public List<LessonDto> findAll(){
		List<LessonDto> lessonDtos = new ArrayList<>();
		try{
			List<Lesson> lessons = lessonRepository.findAll();
			lessonDtos = lessonMapper.LessonsToLessonsDto(lessons);
		}catch (Exception e){
			lessonDtos = null;
		}
		return lessonDtos;
	}
	
	public void delete(long Id) {
		try{
			lessonRepository.deleteById(Id);
		}catch (Exception e){

		}
	}
	public Page<Lesson> getLessons(Pageable page) {
		try{
			return lessonRepository.findAll(page);
		}catch (Exception e){
			return null;
		}

	}
	public void updateUserData(UserDto userDto, Date date) {
		try{
			userDto.setFluency(getFluency(userDto));
			userDto.setExp(userDto.getExp() + 10);
			userDto.upLevel();
			userDto.updateStreak(userDto, completedLessonService.getCompletedLessons(userDto, date));
			userDto.setRemainingGoals(userService.getRemainingGoals(userDto));
			userDto.setLastLesson(userService.getLesson(userDto));
			userDto.setLastUnit(userService.getUnit(userDto));
			userService.updateUserData(userDto);
		}catch (Exception e){

		}
	}

	public void completedLesson(UserDto userDto, int idlesson, int idunit, int numExercisesCompleted) {
		try{
			LessonDto lessonDto = findById(idlesson + (3 * (idunit - 1)));
			Calendar dateC = Calendar.getInstance();
			Date date = new Date((dateC.getTime()).getTime());
			CompletedLessonDto completedLessonDto = completedLessonService.findByUserAndLesson(userDto, lessonDto);
			// If lesson is not completed yet and you do all exercise set the Lesson to done
			if ((completedLessonDto == null) && (numExercisesCompleted == 4)) {
				CompletedLessonDto completedLessonDtoNew = new CompletedLessonDto(userDto, lessonDto, date);
				completedLessonService.save(completedLessonDtoNew);
				if (userComponent.isLoggedUser()) {
					userDto.setFluency(getFluency(userDto));
					updateUserData(userDto, date);
					userComponent.setLoggedUser(userDto);
				}
			}
		}catch (Exception e){

		}
	}

	public int getFluency(UserDto userDto) {
		int percentage = 0;
		try{
			List<LessonDto> lessonDtos = findAll();
			List<CompletedLessonDto> lessonCompletedDtos = completedLessonService.findByUser(userDto);
			percentage = lessonCompletedDtos.size() / lessonDtos.size() * 100;
		}catch (Exception e){
			percentage = -1;
		}

		return percentage;
	}
}

