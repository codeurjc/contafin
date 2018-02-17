package com.daw.contafin.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserComponent userComponent;
	
	public User findByEmail (String email) {
		return userRepository.findByEmail(email);
	}
	
	public void save(User user) {
		userRepository.save(user);
	}
	

}
