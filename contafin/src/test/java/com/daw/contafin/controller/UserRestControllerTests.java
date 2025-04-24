package com.daw.contafin.controller;

import com.daw.contafin.dto.UserConfigDto;
import com.daw.contafin.dto.UserDto;
import com.daw.contafin.service.UserService;
import com.daw.contafin.config.jwt.UserDetailsImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserRestControllerTests {

    @InjectMocks
    UserRestController userRestController;

    @Mock
    UserService userService;

    @Test
    public void deleteAccount(){
        //GIVEN
        Long id = 2L;
        UserDto userDto = new UserDto();

        //THEN
        Mockito.doNothing().when(userService).deleteAccount(id);

        assertEquals(new ResponseEntity<>(HttpStatus.OK),userRestController.deleteAccount(id));

    }

    @Test
    public void deleteAccountError(){
        //GIVEN
        Long id = 2L;
        Exception npe = new NullPointerException();

        //THEN
        Mockito.doThrow(npe).when(userService).deleteAccount(id);

        assertEquals(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR),userRestController.deleteAccount(id));

    }


    @Test
    public void profile(){
        //GIVEN
        Long id = 1L;
        UserDto userDto = new UserDto();

        //THEN
        when(userService.getProfile(id)).thenReturn(userDto);

        assertEquals(new ResponseEntity<>(userDto, HttpStatus.OK),userRestController.profile(id));

    }

    @Test
    public void profileError(){
        //GIVEN
        Exception npe = new NullPointerException();
        //THEN
        when(userService.getProfile(null)).thenThrow(npe);


        assertEquals(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR),userRestController.profile(null));

    }

    @Test
    public void updateUserData(){
        //GIVEN
        UserDto userDto = new UserDto();

        //THEN
        when(userService.updateUser(userDto)).thenReturn(userDto);


        assertEquals(new ResponseEntity<>(userDto, HttpStatus.OK),userRestController.updateUserData(userDto));

    }

    @Test
    public void updateUserDataError(){
        //GIVEN
        Exception npe = new NullPointerException();

        //THEN
        when(userService.updateUser(null)).thenThrow(npe);

        assertEquals(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR),userRestController.updateUserData(null));

    }

    @Test
    public void validation(){
        //GIVEN
        Long id = 2L;
        String testString = "1234";
        UserConfigDto userConfigDto = new UserConfigDto();
        UserDto userDto = new UserDto();
        Map<String,Object> responseObjects = new HashMap<>();

        UserDetailsImpl userDetails = new UserDetailsImpl(userDto,id,testString,testString,testString,null);

        responseObjects.put("userDetails",userDetails);
        responseObjects.put("token",testString);
        responseObjects.put("roles",new ArrayList<String>());

        userConfigDto.setEmail(testString);
        userConfigDto.setName(testString);
        userConfigDto.setOldpass(testString);
        userConfigDto.setPass(testString);

        //THEN
        when(userService.checkPass(id,userConfigDto.getOldpass(), userConfigDto.getPass(), userConfigDto.getName(), userConfigDto.getEmail(), userConfigDto.getFile())
        ).thenReturn(userDto);
            when(userService.createAuthentication(userConfigDto.getName(), userConfigDto.getPass())).thenReturn(responseObjects);


        assertEquals(HttpStatus.OK,userRestController.validation(id, userConfigDto).getStatusCode());

    }

    @Test
    public void validationError(){
        //GIVEN
        Exception npe = new NullPointerException();
        Long id = 2L;
        String testString = "1234";
        UserConfigDto userConfigDto = new UserConfigDto();
        userConfigDto.setEmail(testString);
        userConfigDto.setName(testString);
        userConfigDto.setOldpass(testString);
        userConfigDto.setPass(testString);

        //THEN
        when(userService.checkPass(id,userConfigDto.getOldpass(), userConfigDto.getPass(), userConfigDto.getName(), userConfigDto.getEmail(), userConfigDto.getFile())
        ).thenThrow(npe);

        assertEquals(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR),userRestController.validation(id, userConfigDto));

    }

    @Test
    public void progress(){
        //GIVEN
        Long id = 2L;
        int[] i = new int[1];
        //THEN
        when(userService.progress(id)).thenReturn(i);

        assertEquals(new ResponseEntity<>(i, HttpStatus.OK),userRestController.progress(id));

    }

    @Test
    public void progressError(){
        //GIVEN
        Exception npe = new NullPointerException();
        Long id = 2L;
        //THEN
        when(userService.progress(id)).thenThrow(npe);

        assertEquals(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR),userRestController.progress(id));

    }

    @Test
    public void completedLesson(){
        //GIVEN
        Long id = 2L;
        int points = 3;
        UserDto userDto = new UserDto();
        //THEN
        when(userService.lessonComplete(id, points)).thenReturn(userDto);

        assertEquals(new ResponseEntity<>(userDto, HttpStatus.OK),userRestController.completedLesson(id, points));

    }

    @Test
    public void completedLessonError(){
        //GIVEN
        Exception npe = new NullPointerException();
        //THEN
        when(userService.lessonComplete(null, 0)).thenThrow(npe);

        assertEquals(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR),userRestController.completedLesson(null, 0));

    }


}
