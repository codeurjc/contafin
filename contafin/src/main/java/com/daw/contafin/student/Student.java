package com.daw.contafin.student;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.daw.contafin.exercise.Exercise;

@Entity
public class Student {
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	
	private long id;
	
	private String name;
	private String email;
	private String password;
	private int level;
	private int points;
	private int streak;
	private int fluency;
	
	@OneToMany (mappedBy="student")
	private List<Exercise> exercises;
	
	
	public Student() {
		
	}
	
	public Student(String name, String email, String password) {
		this.name=name;
		this.email=email;
		this.password= password;
		this.level=1;
		this.points=0;
		this.streak=0;
		this.fluency=0;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getStreak() {
		return streak;
	}

	public void setStreak(int streak) {
		this.streak = streak;
	}

	public int getFluency() {
		return fluency;
	}

	public void setFluency(int fluency) {
		this.fluency = fluency;
	}

	
	

}
