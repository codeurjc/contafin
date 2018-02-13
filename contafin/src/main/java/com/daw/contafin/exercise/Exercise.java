package com.daw.contafin.exercise;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.daw.contafin.lesson.Lesson;


@Entity
public class Exercise {

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	
	private long id;
	private String heading;
	private boolean skiped;
	
	@ManyToOne
	private Lesson lesson;
	
	private long kind;
	
	private String text;
	//private Imagen imagen;
	
	//private Solucion solucion;

	public Exercise(String heading, boolean skiped) {
		this.heading=heading;
		this.skiped=skiped;
	}
	
	public Lesson getLesson() {
		return lesson;
	}

	public void setLesson(Lesson lesson) {
		this.lesson = lesson;
	}

	public long getKind() {
		return kind;
	}

	public void setKind(long kind) {
		this.kind = kind;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getHeading() {
		return heading;
	}

	public void setHeading(String heading) {
		this.heading = heading;
	}

	public boolean isSkiped() {
		return skiped;
	}

	public void setSkiped(boolean skiped) {
		this.skiped = skiped;
	}
	
}
