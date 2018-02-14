package com.daw.contafin.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;



@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
    public UserRepositoryAuthenticationProvider authenticationProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
    			// Public pages
    			http.authorizeRequests().antMatchers("/login").permitAll();
    			http.authorizeRequests().antMatchers("/").permitAll();
    			// Private pages
    			
    			http.authorizeRequests().antMatchers("/profile").hasAnyRole("USER");

    			// Acces Denied redirect
    			http.exceptionHandling().accessDeniedPage("/decideDenied");

    			// Login form
    			http.formLogin().loginPage("/login");
    			http.formLogin().usernameParameter("username");
    			http.formLogin().passwordParameter("password");
    			http.formLogin().defaultSuccessUrl("/decideLogin");
    			http.formLogin().failureUrl("/loginError");

    			// Logout
    			http.logout().logoutUrl("/logOut").deleteCookies("JSESSIONID", "remember-me");
    			//http.logout().logoutUrl("/logOut");
    			http.logout().logoutSuccessUrl("/login");

    			// http.csrf().disable();

    			
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        // Database authentication provider
        auth.authenticationProvider(authenticationProvider);
    }

}

