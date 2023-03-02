package com.daw.contafin.admin;

import com.daw.contafin.ExcelService;
import com.daw.contafin.answer.AnswerDto;
import com.daw.contafin.completedExercise.CompletedExerciseService;
import com.daw.contafin.exercise.ExerciseDto;
import com.daw.contafin.exercise.ExerciseRestController;
import com.daw.contafin.exercise.ExerciseService;
import com.daw.contafin.user.UserComponent;
import com.daw.contafin.user.UserDto;
import com.daw.contafin.user.UserService;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AdminRestControllerTests {

    @InjectMocks
    AdminRestController adminRestController;

    @Mock
    UserComponent userComponent;

    @Mock
    UserService userService;

    @Mock
    ExcelService excelService;

    @Test
    public void userData(){
        //GIVEN
        List<UserDto> userDtoList = new ArrayList<>();
        //THEN
        when(userComponent.isLoggedUser()).thenReturn(true);
        when(userService.getUsers()).thenReturn(userDtoList);

        ResponseEntity response = adminRestController.userData();

        assertEquals(new ResponseEntity<>(userDtoList, HttpStatus.OK),response);

        when(userComponent.isLoggedUser()).thenReturn(false);
        response = adminRestController.userData();

        assertEquals(new ResponseEntity<>(HttpStatus.UNAUTHORIZED),response);
    }

    @Test
    public void userDataError(){
        //GIVEN
        Exception npe = new NullPointerException();
        //THEN
        when(userComponent.isLoggedUser()).thenThrow(npe);
        ResponseEntity response = adminRestController.userData();

        assertEquals(new ResponseEntity<>(HttpStatus.NOT_FOUND),response);

    }

    @Test
    public void userListReport(){
        //GIVEN
        XSSFWorkbook workbook = new XSSFWorkbook();
        //THEN

        when(excelService.generateExcel()).thenReturn(workbook);

        adminRestController.userListReport();
    }

    @Test
    public void userListReportError(){
        //GIVEN
        Exception npe = new NullPointerException();
        //THEN
        when(excelService.generateExcel()).thenThrow(npe);
        ResponseEntity response = adminRestController.userListReport();

        assertEquals(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR),response);

    }


}
