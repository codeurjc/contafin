package com.daw.contafin.user;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository <User, Long>{
	User findByName (String name);
	User findByEmail (String email);
	User findById(long id);
	List <User> findAll();
	Page <User> findAll(Pageable page);
	
}
