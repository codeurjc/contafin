package com.daw.contafin.security;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.daw.contafin.user.User;
import com.daw.contafin.user.UserRepository;


@Component
public class DatabaseUsersLoader {
	
	@Autowired
    private UserRepository userRepository;

    @PostConstruct
    private void initDatabase() {
    	
    		userRepository.save(new User("user","email", "pass", "ROLE_USER"));
		userRepository.save(new User("admin","adminemail", "adminpass", "ROLE_ADMIN"));
    }

}
