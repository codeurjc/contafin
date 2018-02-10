package com.daw.contafin.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daw.contafin.domain.Student;


public interface StudentRepository extends JpaRepository <Student, Long>{
	List<Student> findByName (String name);
	Student findByEmail (String email);

}
