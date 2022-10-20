package com.daw.contafin.unit;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.transaction.Transactional;

import com.daw.contafin.lesson.Lesson;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Data
public class Unit implements Serializable{

	interface UnitBassic {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	@JsonView(UnitBassic.class)
	private long id;

	@JsonView(UnitBassic.class)
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

	@Override
	public String toString() {
		return "Unit [id=" + id + ", name=" + name + "]";
	}

}
