package com.daw.contafin.anonymous;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Anonymous {

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	
	private long id;
	
}
