package com.daw.contafin.unit;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.daw.contafin.lesson.Lesson;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

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

	@OneToMany(mappedBy = "unit")
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
