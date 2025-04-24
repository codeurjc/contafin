package com.daw.contafin.controller;

import com.daw.contafin.service.ExcelService;
import com.daw.contafin.dto.UserDto;
import com.daw.contafin.service.UserService;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ContextConfiguration
@WithMockUser(username = "admin", authorities = { "ADMIN", "USER" })
public class AdminRestControllerTests {

    @InjectMocks
    AdminRestController adminRestController;

    @Mock
    UserService userService;

    @Mock
    ExcelService excelService;

    @Test
    public void userDataUser(){
        //GIVEN
        List<UserDto> userDtoList = new ArrayList<>();
        //THEN
        when(userService.getUsers()).thenReturn(userDtoList);

        assertEquals(new ResponseEntity<>(userDtoList, HttpStatus.OK),adminRestController.userData());
    }

    @Test
    @WithAnonymousUser
    public void userDataAnonymous(){
        //THEN
        assertEquals(new ResponseEntity<>(HttpStatus.UNAUTHORIZED),adminRestController.userData());
    }

    @Test
    public void userDataError(){
        //GIVEN
        Exception npe = new NullPointerException();
        //THEN
        when(userService.getUsers()).thenThrow(npe);

        assertEquals(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR),adminRestController.userData());

    }

    @Test
    public void userListReportUser(){
        //GIVEN
        SXSSFWorkbook workbook = new SXSSFWorkbook();
        //THEN

        when(excelService.generateExcel()).thenReturn(workbook);

        ResponseEntity<ByteArrayResource> response = adminRestController.userListReport();

        assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    @WithAnonymousUser
    public void userListReportAnonymous(){
        //THEN
        assertEquals(new ResponseEntity<>(HttpStatus.UNAUTHORIZED),adminRestController.userListReport());
    }

    @Test
    public void userListReportError(){
        //GIVEN
        Exception npe = new NullPointerException();
        //THEN
        when(excelService.generateExcel()).thenThrow(npe);

        assertEquals(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR),adminRestController.userListReport());

    }


}
