package com.daw.contafin.controller;

import com.daw.contafin.service.CompletedLessonService;
import com.daw.contafin.dto.LessonDto;
import com.daw.contafin.service.LessonService;
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
public class LessonRestControllerTests {

    @InjectMocks
    LessonRestController lessonRestController;

    @Mock
    LessonService lessonService;

    @Mock
    CompletedLessonService completedLessonService;


    @Test
    public void getLesson(){
        //GIVEN
        Long id = 2L;
        LessonDto lesson = new LessonDto();

        //THEN
        when(lessonService.findById(id)).thenReturn(lesson);


        assertEquals(new ResponseEntity<>(lesson, HttpStatus.OK),lessonRestController.getLesson(id));

    }

    @Test
    public void getLessonError(){
        //GIVEN
        Long id = 2L;
        Exception npe = new NullPointerException();

        //THEN
        when(lessonService.findById(id)).thenThrow(npe);

        assertEquals(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR),lessonRestController.getLesson(id));

    }


    @Test
    public void isCompletedLesson(){
        //GIVEN
        Long id = 2L;
        List<Boolean> lessonComplete = new ArrayList<>();

        //THEN
        when(completedLessonService.checkList(id)).thenReturn(lessonComplete);

        assertEquals(new ResponseEntity<>(lessonComplete, HttpStatus.OK),lessonRestController.isCompletedLesson(id));

    }

    @Test
    public void isCompletedLessonError(){
        //GIVEN
        Long id = 2L;
        Exception npe = new NullPointerException();

        //THEN
        when(completedLessonService.checkList(id)).thenThrow(npe);

        assertEquals(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR),lessonRestController.isCompletedLesson(id));

    }




}
