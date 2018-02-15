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
    	
    			//At the moment all the URLs are public
    			http.authorizeRequests().anyRequest().permitAll();
    			
    			// Login form
    	        http.formLogin().loginPage("/");
    	        http.formLogin().usernameParameter("email");
    	        http.formLogin().passwordParameter("pass");
    	        http.formLogin().defaultSuccessUrl("/home");
    	        http.formLogin().failureUrl("/loginerror");
    	        
    	        http.csrf().disable();
    	        
    	     // Use Http Basic Authentication
    	        http.httpBasic();
    			

    			
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        // Database authentication provider
        auth.authenticationProvider(authenticationProvider);
    }

}

