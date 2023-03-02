package com.daw.contafin.user;

import com.daw.contafin.ImageService;
import com.daw.contafin.completedLesson.CompletedLessonService;
import com.daw.contafin.unit.UnitDto;
import com.daw.contafin.unit.UnitRestController;
import com.daw.contafin.unit.UnitService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.ArrayList;
import java.util.List;

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
    public void profile(){
        //GIVEN
        Long id = 1L;
        UserDto userDto = new UserDto();

        //THEN
        when(userService.getProfile(id)).thenReturn(userDto);

        ResponseEntity response = userRestController.profile(id);

        assertEquals(new ResponseEntity<>(userDto, HttpStatus.OK),response);

    }

    @Test
    public void profileError(){
        //GIVEN
        Exception npe = new NullPointerException();
        //THEN
        when(userService.getProfile(null)).thenThrow(npe);

        ResponseEntity response = userRestController.profile(null);

        assertEquals(new ResponseEntity<>(HttpStatus.NOT_FOUND),response);

    }

    @Test
    public void deleteAccount(){
        //GIVEN
        Long id = 2L;
        UserDto userDto = new UserDto();

        //THEN
        Mockito.doNothing().when(userService).deleteAccount(id);

        ResponseEntity response = userRestController.deleteAccount(id);

        assertEquals(new ResponseEntity<>(HttpStatus.OK),response);

    }

    @Test
    public void deleteAccountError(){
        //GIVEN
        Long id = 2L;
        Exception npe = new NullPointerException();

        //THEN
        Mockito.doThrow(npe).when(userService).deleteAccount(id);

        ResponseEntity response = userRestController.deleteAccount(id);

        assertEquals(new ResponseEntity<>(HttpStatus.NOT_FOUND),response);

    }

    @Test
    public void updateUserData(){
        //GIVEN
        UserDto userDto = new UserDto();

        //THEN
        when(userService.updateUser(userDto)).thenReturn(userDto);

        ResponseEntity response = userRestController.updateUserData(userDto);

        assertEquals(new ResponseEntity<>(userDto, HttpStatus.OK),response);

    }

    @Test
    public void updateUserDataError(){
        //GIVEN
        Exception npe = new NullPointerException();

        //THEN
        when(userService.updateUser(null)).thenThrow(npe);

        ResponseEntity response = userRestController.updateUserData(null);

        assertEquals(new ResponseEntity<>(HttpStatus.NOT_FOUND),response);

    }

    @Test
    public void validation(){
        //GIVEN
        Long id = 2L;
        String pass = "1234";
        //THEN
        when(userService.checkPass(id,pass)).thenReturn(true);

        ResponseEntity response = userRestController.validation(id, pass);

        assertEquals(new ResponseEntity<>(true, HttpStatus.OK),response);

    }

    @Test
    public void validationError(){
        //GIVEN
        Exception npe = new NullPointerException();
        Long id = 2L;
        String pass = "1234";
        //THEN
        when(userService.checkPass(id,pass)).thenThrow(npe);

        ResponseEntity response = userRestController.validation(id, pass);

        assertEquals(new ResponseEntity<>(HttpStatus.NOT_FOUND),response);

    }

    @Test
    public void progress(){
        //GIVEN
        Long id = 2L;
        int[] i = new int[1];
        //THEN
        when(userService.progress(id)).thenReturn(i);

        ResponseEntity response = userRestController.progress(id);

        assertEquals(new ResponseEntity<>(i, HttpStatus.OK),response);

    }

    @Test
    public void progressError(){
        //GIVEN
        Exception npe = new NullPointerException();
        Long id = 2L;
        //THEN
        when(userService.progress(id)).thenThrow(npe);

        ResponseEntity response = userRestController.progress(id);

        assertEquals(new ResponseEntity<>(HttpStatus.NOT_FOUND),response);

    }


    @Test
    public void profilePhoto(){
        //GIVEN
        Long id = 2L;
        MultipartFile file = null;
        //THEN
        when(userService.updatePhoto(id, file)).thenReturn(true);

        ResponseEntity response = userRestController.profilePhoto(id, file);

        assertEquals(new ResponseEntity<>(true, HttpStatus.OK),response);

    }

    @Test
    public void profilePhotoError(){
        //GIVEN
        Exception npe = new NullPointerException();
        Long id = 2L;
        MultipartFile file = null;
        //THEN
        when(userService.updatePhoto(id, file)).thenThrow(npe);

        ResponseEntity response = userRestController.profilePhoto(id, file);

        assertEquals(new ResponseEntity<>(HttpStatus.NOT_FOUND),response);

    }


}
