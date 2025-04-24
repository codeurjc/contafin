package com.daw.contafin.config;

import com.daw.contafin.config.jwt.AuthEntryPointJwt;
import com.daw.contafin.config.jwt.AuthTokenFilter;
import com.daw.contafin.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import javax.transaction.Transactional;

@Configuration
@Order(1)
@Transactional
public class RestSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Autowired
	private AuthEntryPointJwt unauthorizedHandler;

	@Bean
	public AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
	authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
	return super.authenticationManagerBean();
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic().disable();
	http.csrf().disable()//NOSONAR not used in secure contexts
	  .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
	  .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);


	    http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        http.authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/**").permitAll();

		http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/login").permitAll();

		http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/logout").permitAll();

		http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/signup").permitAll();

		http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/User/Validation/*").hasAnyRole("ADMIN","USER");

		http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/User/*/Completed/*").hasAnyRole("ADMIN","USER");;

		// URLs that need authentication to access to it
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/User/").hasAnyRole("ADMIN","USER");
		http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/User/*").hasRole("USER");

		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/Admin/UserData").hasRole("ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/Admin/UserData/Excel").hasRole("ADMIN");

		http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/Unit/").hasRole("ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/Unit/").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/Unit/*/Images").hasRole("ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/Unit/*").hasRole("ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/Unit/*").hasRole("ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/Unit/NumberOfUnitsCompleted").hasAnyRole("USER");

		http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/Unit/*/Lesson/*").hasRole("ADMIN");

		http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/Unit/*/Lesson/*/Exercise/*").hasRole("ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/Unit/*/Lesson/*/Exercise/*/Answer").hasRole("ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/Unit/*/Lesson/*/Exercise/*/Answer").hasRole("ADMIN");

		// Other URLs can be accessed without authentication
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/Exercise/*/Solution").permitAll();
		http.authorizeRequests().anyRequest().permitAll();

		http.logout().logoutSuccessHandler((rq, rs, a) -> {	});
	}
}
