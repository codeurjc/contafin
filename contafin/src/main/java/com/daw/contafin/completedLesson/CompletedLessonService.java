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

import javax.transaction.Transactional;

@Service
@Slf4j
@Transactional
public class CompletedLessonService {
	
	@Autowired
	CompletedLessonRepository completedLessonRepository;

	@Autowired
	UserMapper userMapper;

	@Autowired
	CompletedLessonMapper completedLessonMapper;

	@Autowired
	LessonMapper lessonMapper;


	public int getCompletedLessons(UserDto userDto, Date date) {
		int toReturn = 0;
		try{
			User user = userMapper.UserDtoToUser(userDto);
			toReturn = completedLessonRepository.findByUserAndDate(user, date).size();
		}catch (Exception e){
			toReturn = 0;
		}
		return toReturn;
	}

	public List<CompletedLessonDto> findByUserAndDate(UserDto userDto, Date date){
		List<CompletedLessonDto> completedLessonDtos = new ArrayList<>();
		try{
			User user = userMapper.UserDtoToUser(userDto);
			List<CompletedLesson> completedLesson = completedLessonRepository.findByUserAndDate(user, date);
			completedLessonDtos = completedLessonMapper.CompletedLessonsToCompletedLessonsDto(completedLesson);
		}catch (Exception e){
			completedLessonDtos = null;
		}
		return completedLessonDtos;
	}
	
	public CompletedLessonDto findByUserAndLesson(UserDto userDto, LessonDto lessonDto) {
		CompletedLessonDto completedLessonDto = new CompletedLessonDto();
		try{
			User user = userMapper.UserDtoToUser(userDto);
			Lesson lesson = lessonMapper.LessonDtoToLesson(lessonDto);
			CompletedLesson completedLesson = completedLessonRepository.findByUserAndLesson(user, lesson);
			completedLessonDto = completedLessonMapper.CompletedLessonToCompletedLessonDto(completedLesson);
		}catch (Exception e){
			completedLessonDto = null;
		}
		return completedLessonDto;
	}
	
	public List<CompletedLessonDto> findByUser(UserDto userDto){
		List<CompletedLessonDto> completedLessonDtos = new ArrayList<>();
		try{
			User user = userMapper.UserDtoToUser(userDto);
			List<CompletedLesson> completedLessons = completedLessonRepository.findByUser(user);
			completedLessonDtos = completedLessonMapper.CompletedLessonsToCompletedLessonsDto(completedLessons);
		}catch (Exception e){
			completedLessonDtos = null;
		}
		return completedLessonDtos;
	}

	public void save(CompletedLessonDto completedLessonDto) {
		try{
			CompletedLesson completedLesson = completedLessonMapper.CompletedLessonDtoToCompletedLesson(completedLessonDto);
			completedLessonRepository.save(completedLesson);
		}catch (Exception e){

		}
	}

	public void delete(long Id) {
		try{
			completedLessonRepository.deleteById(Id);
		}catch (Exception e){

		}
	}
}
