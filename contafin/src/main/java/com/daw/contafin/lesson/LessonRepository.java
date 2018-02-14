package com.daw.contafin.lesson;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daw.contafin.unit.Unit;


public interface LessonRepository extends JpaRepository <Lesson, Long>{
	Lesson findById(long Id);
}
