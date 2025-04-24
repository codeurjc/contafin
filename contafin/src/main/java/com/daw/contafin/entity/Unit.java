package com.daw.contafin.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Data
public class Unit implements Serializable{


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	private long id;

	private String name;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "unit_id")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Lesson> lessons;

	public Unit() {
	}

	public Unit(String name) {
		this.name = name;
	}

}
