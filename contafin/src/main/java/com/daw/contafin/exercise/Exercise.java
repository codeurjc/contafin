package com.daw.contafin.exercise;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.daw.contafin.answer.Answer;
import com.daw.contafin.completedExercise.CompletedExercise;
import com.daw.contafin.lesson.Lesson;


@Entity
public class Exercise {

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne
	private Lesson lesson;
	
	private int kind;
	private ClassExercise exercise;
	
	@OneToMany (mappedBy = "exercise")
	private List<CompletedExercise> completedExercises;
	
	@OneToOne (cascade=CascadeType.ALL)
	private Answer answer;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Lesson getLesson() {
		return lesson;
	}

	public void setLesson(Lesson lesson) {
		this.lesson = lesson;
	}

	public int getKind() {
		return kind;
	}

	public void setKind(int kind) {
		this.kind = kind;
	}

	public ClassExercise getExercise() {
		return exercise;
	}

	public void setExercise(ClassExercise exercise) {
		this.exercise = exercise;
	}

	public Answer getAnswer() {
		return answer;
	}

	public void setAnswer(Answer answer) {
		this.answer = answer;
	}

	public Exercise(String heading, boolean skiped) {
		
	}
	
}
