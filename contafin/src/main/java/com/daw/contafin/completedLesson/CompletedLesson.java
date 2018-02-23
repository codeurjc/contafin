package com.daw.contafin.completedLesson;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


import com.daw.contafin.lesson.Lesson;
import com.daw.contafin.user.User;


@Entity
public class CompletedLesson {

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne //Unidirecional, 1:N 1 usuario puede estar en muchos ejercicios completados
	private User user;
	
	@ManyToOne //Unidirecional, 1:N 1 ejercicio puede estar en muchos ejercicios completados
	private Lesson lesson;
	
	//private Date date;
	
	public CompletedLesson() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Lesson getLesson() {
		return lesson;
	}

	public void setLesson(Lesson lesson) {
		this.lesson = lesson;
	}

}
