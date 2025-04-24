package com.daw.contafin.repository;

import java.util.List;
import com.daw.contafin.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository <User, Long>{
	User findByName (String name);
	User findByEmail (String email);
	User findById(long id);
	List <User> findAll();
}
