package com.daw.contafin.security;

import java.util.ArrayList;
import java.util.List;

import com.daw.contafin.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class UserRepositoryAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserComponent userComponent;

	@Autowired
	UserMapper userMapper;

	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {

		String email = auth.getName();
		String password = (String) auth.getCredentials();

		User user = userRepository.findByEmail(email);
		UserDto userDto = userMapper.UserToUserDto(user);

		if (userDto == null) {
			throw new BadCredentialsException("User not found");
		}

		if (!new BCryptPasswordEncoder().matches(password, userDto.getPasswordHash())) {

			throw new BadCredentialsException("Wrong password");
		} else {

			userComponent.setLoggedUser(userDto);

			List<GrantedAuthority> roles = new ArrayList<>();
			for (String role : user.getRoles()) {
				roles.add(new SimpleGrantedAuthority(role));
			}

			return new UsernamePasswordAuthenticationToken(email, password, roles);
		}
	}

	@Override
	public boolean supports(Class<?> authenticationObject) {
		return true;
	}
}
