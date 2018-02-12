package com.daw.contafin.exercise;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Exercise {

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	
	private long id;
	private String heading;
	private boolean skiped;

	public Exercise(String heading, boolean skiped) {
		this.heading=heading;
		this.skiped=skiped;
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
