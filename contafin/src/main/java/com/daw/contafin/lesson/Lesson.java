package com.daw.contafin.lesson;

import java.util.List;

import javax.persistence.*;

import com.daw.contafin.exercise.Exercise;
import com.daw.contafin.unit.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Data
public class Lesson {
	
	public interface LessonBasic {}

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	
	@JsonView(LessonBasic.class)
	private long id;
	
	@JsonView(LessonBasic.class)
	private String name;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "lesson_id")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Exercise> exercises;
	
	public Lesson() {}
	public Lesson(String name) {
		this.name=name;
	}
	
	
}
