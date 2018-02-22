package com.daw.contafin.completedExercise;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.daw.contafin.exercise.Exercise;
//import com.daw.contafin.student.Student;

@Entity
public class CompletedExercise {

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private long id;
	
	/*@ManyToOne //Unidirecional, 1:N 1 usuario puede estar en muchos ejercicios completados
	private User user;*/
	
	@ManyToOne //Unidirecional, 1:N 1 ejercicio puede estar en muchos ejercicios completados
	private Exercise exercise;
	
	private long errores;
	
	public CompletedExercise() {
		
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

	public long getErrores() {
		return errores;
	}

	public void setErrores(long errores) {
		this.errores = errores;
	}

}
