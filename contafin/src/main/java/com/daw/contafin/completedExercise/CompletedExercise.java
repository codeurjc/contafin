package com.daw.contafin.completedExercise;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.daw.contafin.exercise.Exercise;
import com.daw.contafin.user.User;
//import com.daw.contafin.student.Student;

@Entity
public class CompletedExercise {

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne //Unidirecional, 1:N 1 usuario puede estar en muchos ejercicios completados
	private User user;
	
	@ManyToOne //Unidirecional, 1:N 1 ejercicio puede estar en muchos ejercicios completados
	private Exercise exercise;
	
	private long error;
	
	public CompletedExercise() {
		
	}
	public CompletedExercise(User user,Exercise exercise, long error) {
		this.user=user;
		this.error=error;
		this.exercise=exercise;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	/*public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}*/

	public Exercise getExercise() {
		return exercise;
	}

	public void setExercise(Exercise exercise) {
		this.exercise = exercise;
	}
	public long getError() {
		return error;
	}

	public void setErrores(long error) {
		this.error = error;
	}

}
