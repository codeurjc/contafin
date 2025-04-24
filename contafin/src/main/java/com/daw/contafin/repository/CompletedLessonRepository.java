package com.daw.contafin.repository;


import java.util.Date;
import java.util.List;

import com.daw.contafin.entity.CompletedLesson;

import com.daw.contafin.entity.Lesson;
import com.daw.contafin.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompletedLessonRepository extends CrudRepository<CompletedLesson, Long> {
	List<CompletedLesson> findByUserAndDate(User user, Date date);
	List<CompletedLesson> findByUserAndLesson(User user, Lesson lesson);
	List<CompletedLesson> findByUser(User user);
	List<CompletedLesson> findByUserOrderByDateDesc(User user);

}
