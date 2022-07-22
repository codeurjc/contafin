package com.daw.contafin.completedLesson;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


import com.daw.contafin.lesson.Lesson;
import com.daw.contafin.user.User;
import lombok.Data;


@Entity
@Data
public class CompletedLesson {

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne //Unidirectional, 1: N 1 user can be in many completed exercises.
	private User user;
	
	@ManyToOne //Unidirectional, 1: N 1 exercise can be in many completed exercises
	private Lesson lesson;
	
	private Date date;
	
	public CompletedLesson() {
		
	}
	public CompletedLesson(User user, Lesson lesson, Date date) {
		this.user = user;
		this.lesson = lesson;
		this.date = date;
	}

}
