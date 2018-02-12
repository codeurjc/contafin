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
	
}
