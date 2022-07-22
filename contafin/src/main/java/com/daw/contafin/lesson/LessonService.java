package com.daw.contafin.lesson;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

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
	
	
	public Lesson findById (long id) {
		return lessonRepository.findById(id);
	}
	public List<Lesson> findByUnit(Unit Id){
		return lessonRepository.findByUnit(Id);
	}
	public void save(Lesson lesson) {
		lessonRepository.save(lesson);
	}
	
	public List<Lesson> findAll(){
		return lessonRepository.findAll();
	}
	
	public void delete(long Id) {
		lessonRepository.deleteById(Id);
	}
	public Page<Lesson> getLessons(Pageable page) {
		return lessonRepository.findAll(page);
	}
	public void updateUserData(User user, Date sqlDate) {
		user.setFluency(getFluency(user));
		user.setExp(user.getExp() + 10);
		user.upLevel();
		user.updateStreak(user, completedLessonService.getCompletedLessons(user, sqlDate));
		user.setRemainingGoals(userService.getRemainingGoals(user));
		user.setLastLesson(userService.getLesson(user));
		user.setLastUnit(userService.getUnit(user));
		userService.updateUserData(user);
	}

	public void completedLesson(User user, int idlesson, int idunit, int numExercisesCompleted) {
		Lesson lesson = findById(idlesson + (3 * (idunit - 1)));
		Calendar date = Calendar.getInstance();
		Date sqlDate = new Date((date.getTime()).getTime());
		CompletedLesson completedLessonS = completedLessonService.findByUserAndLesson(user, lesson);
		// If lesson is not completed yet and you do all exercise set the Lesson to done
		if ((completedLessonS == null) && (numExercisesCompleted == 4)) {
			CompletedLesson completedLesson = new CompletedLesson(user, lesson, sqlDate);
			completedLessonService.save(completedLesson);
			if (userComponent.isLoggedUser()) {
				user.setFluency(getFluency(user));
				updateUserData(user, sqlDate);
				userComponent.setLoggedUser(user);
			}
		}
	}

	public int getFluency(User user) {
		List<Lesson> lessons = findAll();
		List<CompletedLesson> lessonsCompleted = completedLessonService.findByUser(user);
		int numLessons = lessons.size();
		int numLessonsCompleted = lessonsCompleted.size();
		double percentageD = (double) numLessonsCompleted / numLessons * 100;
		int percentage = (int) percentageD;
		return percentage;
	}
}

