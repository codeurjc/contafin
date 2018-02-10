package com.daw.contafin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import com.daw.contafin.domain.Student;
import com.daw.contafin.repositories.StudentRepository;

@Controller
public class StudentController implements CommandLineRunner{
	@Autowired
	private StudentRepository repository;
	
	@Override
	public void run (String... args) throws Exception{
		repository.save(new Student("Ramón", "ramon@hotmail.es", "lalala"));
		repository.save(new Student("Julián", "juli@hotmail.es", "lelele"));
		repository.save(new Student("Luna", "luna@hotmail.es", "lilili"));
	}
	


}
