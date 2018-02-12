package com.daw.contafin.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

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
