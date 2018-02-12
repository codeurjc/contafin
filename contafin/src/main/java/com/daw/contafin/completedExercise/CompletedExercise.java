package com.daw.contafin.completedExercise;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CompletedExercise {

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	
	private long id;
	
}
