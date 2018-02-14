package com.daw.contafin.user;

import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository <User, Long>{
	User findByName (String name);
	User findByEmail (String email);
	User findById(long id);
	
}
