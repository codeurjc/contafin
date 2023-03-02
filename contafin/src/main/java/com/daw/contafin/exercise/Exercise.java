package com.daw.contafin.exercise;

import java.util.Arrays;
import java.util.List;

import javax.persistence.*;

import com.daw.contafin.answer.Answer;
import com.daw.contafin.completedExercise.CompletedExercise;
import com.daw.contafin.lesson.Lesson;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Data
public class Exercise {


	interface ExerciseBassic {}
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonView(ExerciseBassic.class)
	private long id;

	@JsonView(ExerciseBassic.class)
	private int kind;

	@JsonView(ExerciseBassic.class)
	private String statement;

	@JsonIgnore
	@Lob
	private byte[] image1;

	@JsonIgnore
	@Lob
	private byte[] image2;

	@JsonIgnore
	@Lob
	private byte[] image3;
	
	@JsonView(ExerciseBassic.class)
	@ElementCollection
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<String> texts;


	@JsonIgnore
	@OneToOne()
	@JoinColumn(name = "answer_id")
	@LazyCollection(LazyCollectionOption.FALSE)
	private Answer answer;


	@Override
	public String toString() {
		return "ExerciseDto [id=" + id + ", kind=" + kind + ", statement=" + statement + ", texts=" + texts + ", answer=" + answer + "]";
	}
}