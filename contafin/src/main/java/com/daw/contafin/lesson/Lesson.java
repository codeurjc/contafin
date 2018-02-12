package com.daw.contafin.lesson;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Lesson {

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	
	private long id;
	private String name;
	private int num;
	private int experience;
	
	public Lesson(String name, int num, int experience) {
		this.name=name;
		this.num=num;
		this.experience=experience;
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

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}
	
	
	
}
