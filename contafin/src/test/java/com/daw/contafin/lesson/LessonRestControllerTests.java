package com.daw.contafin.lesson;

import com.daw.contafin.answer.AnswerDto;
import com.daw.contafin.completedExercise.CompletedExerciseService;
import com.daw.contafin.completedLesson.CompletedLessonService;
import com.daw.contafin.exercise.ExerciseDto;
import com.daw.contafin.exercise.ExerciseRestController;
import com.daw.contafin.exercise.ExerciseService;
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
public class LessonRestControllerTests {

    @InjectMocks
    LessonRestController lessonRestController;

    @Mock
    LessonService lessonService;

    @Mock
    CompletedLessonService completedLessonService;

    @Mock
    CompletedExerciseService completedExerciseService;

    @Test
    public void getLesson(){
        //GIVEN
        Long id = 2L;
        LessonDto lesson = new LessonDto();

        //THEN
        when(lessonService.findById(id)).thenReturn(lesson);

        ResponseEntity response = lessonRestController.getLesson(id);

        assertEquals(new ResponseEntity<>(lesson, HttpStatus.OK),response);

    }

    @Test
    public void getLessonError(){
        //GIVEN
        Long id = 2L;
        Exception npe = new NullPointerException();

        //THEN
        when(lessonService.findById(id)).thenThrow(npe);

        ResponseEntity response = lessonRestController.getLesson(id);

        assertEquals(new ResponseEntity<>(HttpStatus.NOT_FOUND),response);

    }

    @Test
    public void completedLesson(){
        //GIVEN
        Long id = 2L;
        Boolean b = true;

        //THEN
        when(completedExerciseService.checkLessonComplete(id)).thenReturn(b);

        ResponseEntity response = lessonRestController.completedLesson(id);

        assertEquals(new ResponseEntity<>(b, HttpStatus.OK),response);

    }

    @Test
    public void completedLessonError(){
        //GIVEN
        Long id = 2L;
        Exception npe = new NullPointerException();

        //THEN
        when(completedExerciseService.checkLessonComplete(id)).thenThrow(npe);

        ResponseEntity response = lessonRestController.completedLesson(id);

        assertEquals(new ResponseEntity<>(HttpStatus.NOT_FOUND),response);

    }

    @Test
    public void isCompletedLessonB(){
        //GIVEN
        Long id = 2L;
        List<Boolean> lessonComplete = new ArrayList<>();

        //THEN
        when(completedLessonService.checkList(id)).thenReturn(lessonComplete);

        ResponseEntity response = lessonRestController.isCompletedLessonB(id);

        assertEquals(new ResponseEntity<>(lessonComplete, HttpStatus.OK),response);

    }

    @Test
    public void isCompletedLessonBError(){
        //GIVEN
        Long id = 2L;
        Exception npe = new NullPointerException();

        //THEN
        when(completedLessonService.checkList(id)).thenThrow(npe);

        ResponseEntity response = lessonRestController.isCompletedLessonB(id);

        assertEquals(new ResponseEntity<>(HttpStatus.NOT_FOUND),response);

    }




}
