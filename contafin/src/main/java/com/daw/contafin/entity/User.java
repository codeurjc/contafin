package com.daw.contafin.entity;


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



@Entity
@Data
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

	private int lastUnit;

	private int lastLesson;

	private int[] progress;

	private int remainingGoals;

	private int exp = 0;

	private int needexp = 10;

	@Lob
	private byte[] image;


	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> roles;
}
