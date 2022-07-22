package com.daw.contafin.completedLesson;


import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.daw.contafin.lesson.Lesson;
import com.daw.contafin.user.User;
import org.springframework.stereotype.Repository;

@Repository
public interface CompletedLessonRepository extends JpaRepository <CompletedLesson, Long>{
	List<CompletedLesson> findByUserAndDate(User user, Date date);
	CompletedLesson findByUserAndLesson(User user, Lesson lesson);
	List<CompletedLesson> findByUser(User user);
}
