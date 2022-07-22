package com.daw.contafin.lesson;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.daw.contafin.exercise.Exercise;
import com.daw.contafin.unit.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

@Entity
@Data
public class Lesson {
	
	public interface LessonBasic {}

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	
	@JsonView(LessonBasic.class)
	private long id;
	
	@JsonView(LessonBasic.class)
	private String name;
	
	@OneToMany (mappedBy = "lesson")
	private List<Exercise> exercises;    
	
	@JsonIgnore
	@ManyToOne
	private Unit unit;
	
	public Lesson() {}
	public Lesson(String name, Unit unit) {
		this.name=name;
		this.setUnit(unit);
	}
	
	
}
