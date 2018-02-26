package com.daw.contafin.exercise;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
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
	
	private String statement;
	
	@ElementCollection
	private List<String> ruteImages;
	
	@ElementCollection
	private List<String> texts;
	
	@OneToMany (mappedBy = "exercise")
	private List<CompletedExercise> completedExercises;
	
	@OneToOne (cascade=CascadeType.ALL)
	private Answer answer;

	public Exercise() {
		
	}
	
	public Exercise(int kind,String statement , List<String> ruteImages,  List<String> texts, Answer answer,Lesson lesson) {
		this.kind=kind;
		this.statement = statement;
		this.texts = texts;
		this.setLesson(lesson);

		if(kind == 1 || kind == 2) {
			this.ruteImages = ruteImages;
		}
		else if(kind == 3 || kind == 4 || kind == 5 || kind == 6 || kind == 7) {
			this.ruteImages = null;
		}
		else {
			System.out.println("This kind of exercise dont exist");
		}
		this.answer=answer;
	}

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

	public String getStatement() {
		return statement;
	}

	public void setStatement(String statement) {
		this.statement = statement;
	}

	public List<String> getRuteImages() {
		return ruteImages;
	}

	public void setRuteImages(List<String> ruteImages) {
		this.ruteImages = ruteImages;
	}

	public List<String> getTexts() {
		return texts;
	}

	public void setTexts(List<String> texts) {
		this.texts = texts;
	}

	public List<CompletedExercise> getCompletedExercises() {
		return completedExercises;
	}

	public void setCompletedExercises(List<CompletedExercise> completedExercises) {
		this.completedExercises = completedExercises;
	}

	public Answer getAnswer() {
		return answer;
	}

	public void setAnswer(Answer answer) {
		this.answer = answer;
	}
	
	
}
