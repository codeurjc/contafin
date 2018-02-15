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

@Entity
public class Lesson {

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	
	private long id;
	private String name;
	
	@OneToMany (mappedBy = "lesson")
	private List<Exercise> exercises;    
	
	
	@ManyToOne
	private Unit unit;
	
	public Lesson() {}
	public Lesson(String name, Unit unit) {
		this.name=name;
		this.setUnit(unit);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}
	
	
}
