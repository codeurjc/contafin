package com.daw.contafin.entity;

import java.util.List;

import javax.persistence.*;

import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Data
public class Lesson {

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private long id;

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
