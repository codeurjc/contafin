package com.daw.contafin.lesson;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.daw.contafin.unit.Unit;


public interface LessonRepository extends JpaRepository <Lesson, Long>{
	Lesson findById(long Id);
	List<Lesson> findByUnit(Unit Id);
	List<Lesson> findAll();
	Page<Lesson> findAll(Pageable page);
}
