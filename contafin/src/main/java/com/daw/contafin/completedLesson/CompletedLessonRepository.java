package com.daw.contafin.completedLesson;


import org.springframework.data.jpa.repository.JpaRepository;


import com.daw.contafin.user.User;


public interface CompletedLessonRepository extends JpaRepository <CompletedLesson, Long>{
	//<List> CompletedLesson findByUserAndExercise(User user, Date date);
}
