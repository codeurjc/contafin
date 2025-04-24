package com.daw.contafin.entity;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Data
public class Exercise {

	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private int kind;

	private String statement;

	@Lob
	private byte[] image1;

	@Lob
	private byte[] image2;

	@Lob
	private byte[] image3;

	@ElementCollection
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<String> texts;


	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "answer_id")
	@LazyCollection(LazyCollectionOption.FALSE)
	private Answer answer;
}