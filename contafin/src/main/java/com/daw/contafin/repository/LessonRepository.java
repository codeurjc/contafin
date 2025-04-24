package com.daw.contafin.repository;

import java.util.List;

import com.daw.contafin.entity.Lesson;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRepository extends CrudRepository<Lesson, Long> {
	Lesson findById(long Id);
	List<Lesson> findAll();
}
