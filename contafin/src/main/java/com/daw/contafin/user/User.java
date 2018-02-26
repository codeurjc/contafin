package com.daw.contafin.user;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.OneToMany;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.daw.contafin.completedExercise.CompletedExercise;

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
	private int dailyGoal;
	private String lastConnection;
	
	//Atributo experiencia para aumentarsela cuando acabe una lección y exp necesaria para subir de nivel.
	private int exp = 0;
	private int needexp = 10;
	//También he incluido el método upLevel para aumentar el nivel cuando se alcance la exp necesaria.
	//Creo que deberiamos pasar el id del usuario en las url para poder aumentarle level, racha...
	
	@Lob
	private byte[] image;
	
	@ElementCollection(fetch = FetchType.EAGER) 
	private List<String> roles;
	
	@OneToMany (mappedBy="user")
	private List<CompletedExercise> exercises;
	
	
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
		this.lastConnection= "-";
	}
	
	public User(String name, String email, String password,int level, int points, int streak, int dailyGoal, String... roles) {
		this.name=name;
		this.email=email;
		this.passwordHash= new BCryptPasswordEncoder().encode(password);
		this.roles = new ArrayList<>(Arrays.asList(roles));
		this.level=level;
		this.points=points;
		this.streak=streak;
		this.dailyGoal= dailyGoal;
		this.lastConnection= "-";
	}

	public void upLevel() {
		if(exp >= needexp) {
			level = level +1;
			this.exp = exp - needexp;
			this.needexp = needexp +20;
		}
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

	public int getDailyGoal() {
		return dailyGoal;
	}

	public void setDailyGoal(int dailyGoal) {
		this.dailyGoal = dailyGoal;
	}

	public int getNeedexp() {
		return needexp;
	}

	public void setNeedexp(int needexp) {
		this.needexp = needexp;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getLastConnection() {
		return lastConnection;
	}

	public void setLastConnection(String lastConnection) {
		this.lastConnection = lastConnection;
	}
	
	public String newConnection() {
		Date today = Calendar.getInstance().getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd/HH:mm:ss");
		String newConecction = formatter.format(today);
		return newConecction;
	}
	
	public void updateStreak(User user, int completedLessons) {
		if(completedLessons == user.getDailyGoal()) {
			user.setStreak(user.getStreak()+1);
		}
	}

}
