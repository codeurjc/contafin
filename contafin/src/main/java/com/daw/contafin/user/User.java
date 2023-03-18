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
import javax.persistence.Lob;
import javax.persistence.OneToMany;

import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.daw.contafin.completedExercise.CompletedExercise;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;


@Entity
@Data
public class User {
	
	public interface UserBassic {
	}
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	@JsonView(UserBassic.class)
	private long id;
	
	@JsonView(UserBassic.class)
	private String name;
	
	@JsonView(UserBassic.class)
	private String email;
	
	private String passwordHash;
	
	@JsonView(UserBassic.class)
	private int level;
	
	@JsonView(UserBassic.class)
	private int points;
	
	@JsonView(UserBassic.class)
	private int streak;
	
	@JsonView(UserBassic.class)
	private int fluency;
	
	@JsonView(UserBassic.class)
	private int dailyGoal;
	
	@JsonView(UserBassic.class)
	private String lastConnection;
	
	@JsonView(UserBassic.class)
	private int lastUnit;
	
	@JsonView(UserBassic.class)
	private int lastLesson;
	
	@JsonView(UserBassic.class)//Revisar este atributo
	private int[] progress;
	
	@JsonView(UserBassic.class)
	private int remainingGoals;
	
	@JsonView(UserBassic.class)
	private int exp = 0;
	
	@JsonView(UserBassic.class)
	private int needexp = 10;
	
	@JsonIgnore
	@Lob
	private byte[] image;


	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> roles;
	
	@OneToMany (mappedBy="user")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<CompletedExercise> exercises;
	
	
	public User() {
		
	}
}
