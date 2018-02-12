package com.daw.contafin.student;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface StudentRepository extends JpaRepository <Student, Long>{
	List<Student> findByName (String name);
	Student findByEmail (String email);

}
