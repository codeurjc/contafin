package com.daw.contafin.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.OneToMany;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//import com.daw.contafin.completedExercise.CompletedExercise;

@Entity
public class User {
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	
	private long id;
	
	private String name;
	private String email;
	private String passwordHash;
	private int level;
	private int points;
	private int streak;
	private int fluency;
	
	@ElementCollection(fetch = FetchType.EAGER) 
	private List<String> roles;
	
	//@OneToMany (mappedBy="student")
	//private List<CompletedExercise> exercises;
	
	
	public User() {
		
	}
	
	public User(String name, String email, String password, String... roles) {
		this.name=name;
		this.email=email;
		this.passwordHash= new BCryptPasswordEncoder().encode(password);
		this.roles = new ArrayList<>(Arrays.asList(roles));
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

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
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

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	

}
