package com.daw.contafin;

import com.daw.contafin.dto.UserDto;
import com.daw.contafin.config.jwt.UserDetailsImpl;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

@TestConfiguration
public class SpringSecurityForUserControllerImplTestConfig {

    @Bean
    public UserDto testUser() {
        UserDto userDto = new UserDto("username","username@example.com","password","ADMIN", "USER");
        userDto.setId(1L);
        return userDto;
    }

    @Bean
    @Primary
    public UserDetailsService userDetailsService() {
        UserDetailsImpl userDetails = UserDetailsImpl.build(testUser());
        return new UserDetailsManager() {
            private final InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager(userDetails);
            @Override
            public void createUser(UserDetails userDetails) {
                this.inMemoryUserDetailsManager.createUser(userDetails);
            }
            @Override
            public void updateUser(UserDetails userDetails) {
                this.inMemoryUserDetailsManager.updateUser(userDetails);
            }
            @Override
            public void deleteUser(String s) {
                this.inMemoryUserDetailsManager.deleteUser(s);
            }
            @Override
            public void changePassword(String s, String s1) {
                this.inMemoryUserDetailsManager.changePassword(s, s1);
            }
            @Override
            public boolean userExists(String s) {
                return this.inMemoryUserDetailsManager.userExists(s);
            }
            @Override
            public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
                return UserDetailsImpl.build(testUser());
            }
        };
    }
}