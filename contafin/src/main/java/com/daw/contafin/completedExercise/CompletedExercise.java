package com.daw.contafin.completedExercise;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.daw.contafin.exercise.Exercise;
import com.daw.contafin.user.User;
import lombok.Data;

@Entity
@Data
public class CompletedExercise {

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne //Unidirectional, 1: N 1 user can be in many completed exercises
	private User user;
	
	@ManyToOne //Unidirectional, 1: N 1 exercise can be in many completed exercises
	private Exercise exercise;
	
	private long error;
	
	public CompletedExercise() {
		
	}
	public CompletedExercise(User user,Exercise exercise, long error) {
		this.user=user;
		this.error=error;
		this.exercise=exercise;
	}

}
