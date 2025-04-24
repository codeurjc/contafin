package com.daw.contafin.user;

import javax.annotation.PostConstruct;

import com.daw.contafin.dto.UserDto;
import com.daw.contafin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Component
public class DatabaseUsersLoader {

	@Autowired
	private UserService userService;

	@PostConstruct
	private void initDatabase() throws IOException {

		List<UserDto> usuarios = userService.getUsers();
		if(usuarios == null || usuarios.isEmpty() ){
			UserDto user = new UserDto("user", "email@hotmail.es", "pass", "ROLE_USER");
			user.setImage(Files.readAllBytes(Paths.get("img/profile.png")));
			userService.save(user);
			user = new UserDto("admin", "adminemail@hotmail.es", "adminpass", "ROLE_ADMIN","ROLE_USER");
			user.setImage(Files.readAllBytes(Paths.get("img/profile.png")));
			userService.save(user);
			user = new UserDto("Sergio", "sergio@hotmail.es", "pass", "ROLE_USER");
			user.setImage(Files.readAllBytes(Paths.get("img/profile.png")));
			userService.save(user);
		}
	}

}
