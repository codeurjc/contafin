package com.daw.contafin.user;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.daw.contafin.completedLesson.CompletedLessonDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.daw.contafin.completedLesson.CompletedLesson;
import com.daw.contafin.completedLesson.CompletedLessonService;

import javax.transaction.Transactional;

@Service
@Slf4j
@Transactional
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserComponent userComponent;
	
	@Autowired
	CompletedLessonService completedLessonService;

	@Autowired
	UserMapper userMapper;
	
	public UserDto findByEmail (String email) {
		UserDto userDto = new UserDto();
		try{
			User user = userRepository.findByEmail(email);
			userDto = userMapper.UserToUserDto(user);
		}catch (Exception e){
			userDto = null;
		}
		return userDto;
	}
	
	public List<UserDto> getUsers () {
		List<UserDto> userDtos = new ArrayList<>();
		try{
			List<User> user = userRepository.findAll();
			userDtos = userMapper.UsersToUsersDto(user);
		}catch (Exception e){
			userDtos = null;
		}
		return userDtos;
	}
	
	public void save(UserDto userDto) {
		try{
			User user = userMapper.UserDtoToUser(userDto);
			userRepository.save(user);
		}catch (Exception e){

		}
	}
	
	public UserDto findById(long id) {
		UserDto userDto = new UserDto();
		try{
			User user = userRepository.findById(id);
			userDto = userMapper.UserToUserDto(user);
		}catch (Exception e){
			userDto = null;
		}
		return userDto;
	}
	
	public void updateUserData(UserDto userDto) {
		try{
			User user = userMapper.UserDtoToUser(userDto);
			userRepository.save(user);
		}catch (Exception e){

		}
	}
	
	public void deleteAccount(UserDto userDto) {
		try{
			User user = userMapper.UserDtoToUser(userDto);
			userRepository.delete(user);
		}catch (Exception e){

		}
	}
	
	public int  getLesson(UserDto userDto) {
		try{
			List<CompletedLessonDto> completedLessonDtos = completedLessonService.findByUser(userDto);
			return completedLessonDtos.size() % 3;
		}catch (Exception e){
			return -1;
		}
	}
	
	public int  getUnit(UserDto userDto) {
		try{
			List <CompletedLessonDto> completedLessonDtos = completedLessonService.findByUser(userDto);
			return (completedLessonDtos.size()/3) + 1;
		}catch (Exception e){
			return -1;
		}

	}
	
	public int [] progress(UserDto userDto) {
		//Create an array for weekly progress
		int [] progress = new int[7];
		try{
			//User user = userComponent.getLoggedUser();
			//Get the current date and set first day of week Monday
			Calendar calendar = Calendar.getInstance();
			calendar.setFirstDayOfWeek(Calendar.MONDAY);
			//Convert java.util.Date to java.sql.Date
			Date date = calendar.getTime();
			java.sql.Date sqlDate = new java.sql.Date(date.getTime());
			//Get the current day
			int day = calendar.get(Calendar.DAY_OF_WEEK);
			//Store weekly progress
			progress[day-1] = completedLessonService.getCompletedLessons(userDto, sqlDate);
			for (int i = day-1; i< 0; i-- ) {
				calendar.add(Calendar.DATE, -1);
				date = calendar.getTime();
				sqlDate = new java.sql.Date(date.getTime());
				progress[i-1]=completedLessonService.getCompletedLessons(userDto, sqlDate);
			}
		}catch (Exception e){
			progress = null;
		}
		return progress;
	}
	
	public Page<User> getUsers(Pageable page) {
		try{
			return userRepository.findAll(page);
		}catch (Exception e){
			return null;
		}
	}
	
	public int getRemainingGoals(UserDto userDto) {
			Calendar date = Calendar.getInstance();
			java.sql.Date sqlDate =  new java.sql.Date((date.getTime()).getTime());
			if (completedLessonService.getCompletedLessons(userDto, sqlDate) >= userDto.getDailyGoal()) {
				return 0;
			} else {
				return (userDto.getDailyGoal() - completedLessonService.getCompletedLessons(userDto, sqlDate));
			}
	}
	
	public UserDto updateUser(UserDto userDto, UserDto updatedUserDto) {
		try{
			//Change goal
			if(updatedUserDto.getDailyGoal() !=0) {
				userDto.setDailyGoal(updatedUserDto.getDailyGoal());
				userDto.setRemainingGoals(updatedUserDto.getDailyGoal());
				updateUserData(userDto);
				userComponent.setLoggedUser(userDto);
				return userDto;
			}
			//Change name, email or password
			if (updatedUserDto.getName().isEmpty() && updatedUserDto.getEmail().isEmpty() && updatedUserDto.getPasswordHash().isEmpty()) {
				return null;
			} else {
				if (!updatedUserDto.getName().isEmpty()) {
					userDto.setName(updatedUserDto.getName());
				}
				if (!updatedUserDto.getEmail().isEmpty()) {
					userDto.setEmail(updatedUserDto.getEmail());
				}
				if(!updatedUserDto.getPasswordHash().isEmpty()) {
					String passwordHash = new BCryptPasswordEncoder().encode(updatedUserDto.getPasswordHash());
					userDto.setPasswordHash(passwordHash);
				}
				updateUserData(userDto);
				userComponent.setLoggedUser(userDto);
				return userDto;
			}
		}catch (Exception e){
			return userDto;
		}
	}
	
}
