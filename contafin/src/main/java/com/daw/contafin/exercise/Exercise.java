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
	
}
