package com.daw.contafin.controller;

import com.daw.contafin.SpringSecurityForUserControllerImplTestConfig;
import com.daw.contafin.dto.UserDto;
import com.daw.contafin.service.EmailService;
import com.daw.contafin.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SpringSecurityForUserControllerImplTestConfig.class)
public class LoginRestControllerTests {

    @InjectMocks
    LoginRestController loginRestController;

    @Mock
    UserService userService;

    @Mock
    EmailService emailService;




    @Test
    @WithUserDetails(value="customUsername", userDetailsServiceBeanName="userDetailsService")
    public void logIn(){
        //GIVEN
        Map<String,String> userData = new HashMap<>();
        userData.put("name", "customUsername");
        userData.put("pass", "password");
        List<String> list = new ArrayList<>();
        list.add("ADMIN");
        list.add("USER");

        Map<String,Object> result = new HashMap<>();
        result.put("token","2");
        result.put("userDetails",SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        result.put("roles",list);
        //THEN
        when(userService.createAuthentication(userData.get("name"), userData.get("pass"))).thenReturn(result);

        assertEquals(HttpStatus.OK,loginRestController.logIn(userData).getStatusCode());
    }

    @Test
    public void logInError(){
        //GIVEN
        Exception npe = new NullPointerException();
        Map<String,String> userData = new HashMap<>();
        userData.put("name", "customUsername");
        userData.put("pass", "password");
        //THEN
        when(userService.createAuthentication(any(String.class),any(String.class))).thenThrow(npe);

        assertEquals(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR),loginRestController.logIn(userData));

    }

    @Test
    public void signupBadRequestNoData(){
        //GIVEN
        Map<String,String> userData = new HashMap<>();
        userData.put("name", "customUsername");
        userData.put("pass", "password");

        assertEquals(new ResponseEntity<>(HttpStatus.BAD_REQUEST),loginRestController.signup(userData));
    }

    @Test
    public void signupBadRequestFind(){
        //GIVEN
        Map<String,String> userData = new HashMap<>();
        userData.put("name", "customUsername");
        userData.put("pass", "password");
        userData.put("email", "username@example.com");

        when(userService.findByEmail("username@example.com")).thenReturn(new UserDto());

        assertEquals(new ResponseEntity<>(HttpStatus.BAD_REQUEST),loginRestController.signup(userData));
    }

    @Test
    @WithUserDetails(value="customUsername", userDetailsServiceBeanName="userDetailsService")
    public void signup(){
        //GIVEN
        Map<String,String> userData = new HashMap<>();
        userData.put("name", "customUsername");
        userData.put("pass", "password");
        userData.put("email", "username@example.com");

        List<String> list = new ArrayList<>();
        list.add("ADMIN");
        list.add("USER");

        Map<String,Object> result = new HashMap<>();
        result.put("token","2");
        result.put("userDetails",SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        result.put("roles",list);
        //THEN
        when(userService.findByEmail("username@example.com")).thenReturn(null);
        doNothing().when(userService).save(any(UserDto.class));
        when(userService.createAuthentication(userData.get("name"), userData.get("pass"))).thenReturn(result);
        try{
            doNothing().when(emailService).sendSimpleMessage(any(UserDto.class));
        }catch (Exception e){
            fail();
        }
        assertEquals(HttpStatus.OK,loginRestController.signup(userData).getStatusCode());
    }

    @Test
    public void signupError(){
        //GIVEN
        Exception npe = new NullPointerException();
        Map<String,String> userData = new HashMap<>();
        userData.put("name", "customUsername");
        userData.put("pass", "password");
        userData.put("email", "username@example.com");
        //THEN
        when(userService.findByEmail("username@example.com")).thenThrow(npe);

        assertEquals(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR),loginRestController.signup(userData));

    }


}
