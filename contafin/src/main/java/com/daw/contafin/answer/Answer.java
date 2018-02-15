package com.daw.contafin.answer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.daw.contafin.exercise.Exercise;

@Entity
public class Answer {

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private long id;
	
	@OneToOne(mappedBy="answer")
	private Exercise exercise;
	
	public Answer() {}
}
