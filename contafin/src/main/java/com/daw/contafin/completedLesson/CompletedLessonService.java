package com.daw.contafin.completedLesson;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw.contafin.user.User;

@Service
public class CompletedLessonService {
	
	@Autowired
	CompletedLessonRepository completedLessonRepository;
	
	public int getCompletedLessons(User user, Date date) {
		return completedLessonRepository.findByUserAndDate(user, date).size();
	}

}
