package com.daw.contafin.security;

import javax.annotation.PostConstruct;

import com.daw.contafin.user.UserDto;
import com.daw.contafin.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.daw.contafin.user.User;
import com.daw.contafin.user.UserRepository;

import java.util.List;

@Component
public class DatabaseUsersLoader {

	@Autowired
	private UserService userService;

	@PostConstruct
	private void initDatabase() {

		List<UserDto> usuarios = userService.getUsers();
		if(usuarios == null || usuarios.isEmpty() ){
			userService.save(new UserDto("user", "email@hotmail.es", "pass", "ROLE_USER"));
			userService.save(new UserDto("admin", "adminemail@hotmail.es", "adminpass", "ROLE_ADMIN","ROLE_USER"));
			userService.save(new UserDto("Sergio", "sergio@hotmail.es", "pass", 2, 50, 7, 3, "ROLE_USER"));

			// Add test users.
			for (int i = 0; i <= 40; i++) {
				userService.save(new UserDto("user " + i, "email" + i + "@hotmail.es", "pass", "ROLE_USER"));
			}
		}
	}

}
