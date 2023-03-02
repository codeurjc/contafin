package com.daw.contafin.unit;

import com.daw.contafin.completedExercise.CompletedExerciseService;
import com.daw.contafin.completedLesson.CompletedLessonService;
import com.daw.contafin.lesson.LessonDto;
import com.daw.contafin.lesson.LessonRestController;
import com.daw.contafin.lesson.LessonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
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

        ResponseEntity response = unitRestController.getUnits();

        assertEquals(new ResponseEntity<>(unitDtoList, HttpStatus.OK),response);

    }

    @Test
    public void getUnitsError(){
        //GIVEN
        Exception npe = new NullPointerException();
        //THEN
        when(unitService.findAll()).thenThrow(npe);

        ResponseEntity response = unitRestController.getUnits();

        assertEquals(new ResponseEntity<>(HttpStatus.NOT_FOUND),response);

    }

    @Test
    public void getOneUnit(){
        //GIVEN
        Long id = 2L;
        UnitDto unitDto = new UnitDto();

        //THEN
        when(unitService.findById(id)).thenReturn(unitDto);

        ResponseEntity response = unitRestController.getOneUnit(id);

        assertEquals(new ResponseEntity<>(unitDto, HttpStatus.OK),response);

    }

    @Test
    public void getOneUnitError(){
        //GIVEN
        Long id = 2L;
        Exception npe = new NullPointerException();

        //THEN
        when(unitService.findById(id)).thenThrow(npe);

        ResponseEntity response = unitRestController.getOneUnit(id);

        assertEquals(new ResponseEntity<>(HttpStatus.NOT_FOUND),response);

    }

    @Test
    public void createUnit(){
        //GIVEN
        UnitDto unitDto = new UnitDto();

        //THEN
        when(unitService.saveUnitComplete(unitDto)).thenReturn(unitDto);

        ResponseEntity response = unitRestController.createUnit(unitDto);

        assertEquals(new ResponseEntity<>(unitDto, HttpStatus.OK),response);

    }

    @Test
    public void createUnitError(){
        //GIVEN
        Exception npe = new NullPointerException();

        //THEN
        when(unitService.saveUnitComplete(null)).thenThrow(npe);

        ResponseEntity response = unitRestController.createUnit(null);

        assertEquals(new ResponseEntity<>(HttpStatus.NOT_FOUND),response);

    }

    @Test
    public void numberOfCompletedLesson(){
        //GIVEN
        Long id = 2L;
        //THEN
        when(completedLessonService.numberOfCompletedLesson(id)).thenReturn(3);

        ResponseEntity response = unitRestController.numberOfCompletedLesson(id);

        assertEquals(new ResponseEntity<>(3, HttpStatus.OK),response);

    }

    @Test
    public void numberOfCompletedLessonError(){
        //GIVEN
        Exception npe = new NullPointerException();
        //THEN
        when(completedLessonService.numberOfCompletedLesson(null)).thenThrow(npe);

        ResponseEntity response = unitRestController.numberOfCompletedLesson(null);

        assertEquals(new ResponseEntity<>(HttpStatus.NOT_FOUND),response);

    }




}
