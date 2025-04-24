package com.daw.contafin.controller;

import com.daw.contafin.controller.UnitRestController;
import com.daw.contafin.service.CompletedLessonService;
import com.daw.contafin.dto.UnitDto;
import com.daw.contafin.service.UnitService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UnitRestControllerTests {

    @InjectMocks
    UnitRestController unitRestController;

    @Mock
    UnitService unitService;

    @Mock
    CompletedLessonService completedLessonService;

    @Test
    public void getUnits(){
        //GIVEN
        List<UnitDto> unitDtoList = new ArrayList<>();

        //THEN
        when(unitService.findAll()).thenReturn(unitDtoList);

        assertEquals(new ResponseEntity<>(unitDtoList, HttpStatus.OK),unitRestController.getUnits());

    }

    @Test
    public void getUnitsError(){
        //GIVEN
        Exception npe = new NullPointerException();
        //THEN
        when(unitService.findAll()).thenThrow(npe);

        assertEquals(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR),unitRestController.getUnits());

    }

    @Test
    public void getOneUnit(){
        //GIVEN
        Long id = 2L;
        UnitDto unitDto = new UnitDto();

        //THEN
        when(unitService.findById(id)).thenReturn(unitDto);

        assertEquals(new ResponseEntity<>(unitDto, HttpStatus.OK),unitRestController.getOneUnit(id));

    }

    @Test
    public void getOneUnitError(){
        //GIVEN
        Long id = 2L;
        Exception npe = new NullPointerException();

        //THEN
        when(unitService.findById(id)).thenThrow(npe);

        assertEquals(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR),unitRestController.getOneUnit(id));

    }

    @Test
    public void numberOfCompletedLesson(){
        //GIVEN
        Long id = 2L;
        //THEN
        when(completedLessonService.numberOfCompletedLesson(id)).thenReturn(3);

        assertEquals(new ResponseEntity<>(3, HttpStatus.OK),unitRestController.numberOfCompletedLesson(id));

    }

    @Test
    public void numberOfCompletedLessonError(){
        //GIVEN
        Exception npe = new NullPointerException();
        //THEN
        when(completedLessonService.numberOfCompletedLesson(null)).thenThrow(npe);

        assertEquals(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR),unitRestController.numberOfCompletedLesson(null));

    }




}
