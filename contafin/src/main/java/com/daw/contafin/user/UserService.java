package com.daw.contafin.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw.contafin.completedLesson.CompletedLesson;
import com.daw.contafin.completedLesson.CompletedLessonService;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserComponent userComponent;
	
	@Autowired
	CompletedLessonService completedLessonService;
	
	public User findByEmail (String email) {
		return userRepository.findByEmail(email);
	}
	
	public List<User> getUsers () {
		return userRepository.findAll();
	}
	
	public void save(User user) {
		userRepository.save(user);
	}
	
	public User findById(long id) {
		return userRepository.findById(id);
	}
	
	public void updateUserData(User user) {
		userRepository.save(user);
	}
	
	public void deleteAccount(User user) {
		userRepository.delete(user);
	}
	
	public int  getLesson(User user) {
		List <CompletedLesson> completedLesson = completedLessonService.findByUser(user);
		int lesson = completedLesson.size() % 3;
		return lesson;
	}
	
	public int  getUnit(User user) {
		List <CompletedLesson> completedLesson = completedLessonService.findByUser(user);
		int unit = (int) (completedLesson.size()/3) + 1;
		return unit;
	}
	
	
}
