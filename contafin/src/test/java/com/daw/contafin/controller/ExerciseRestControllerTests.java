package com.daw.contafin.controller;

import com.daw.contafin.controller.ExerciseRestController;
import com.daw.contafin.dto.AnswerDto;
import com.daw.contafin.dto.ExerciseDto;
import com.daw.contafin.service.ExerciseService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ExerciseRestControllerTests {

    @InjectMocks
    ExerciseRestController exerciseRestController;

    @Mock
    ExerciseService exerciseService;

    @Test
    public void getOneExercise(){
        //GIVEN
        Long idExercise = 2L;
        ExerciseDto exercise = new ExerciseDto();

        //THEN
        when(exerciseService.findById(idExercise)).thenReturn(exercise);

        assertEquals(new ResponseEntity<>(exercise, HttpStatus.OK),exerciseRestController.getOneExercise(idExercise));

    }

    @Test
    public void getOneExerciseError(){
        //GIVEN
        Long idExercise = 2L;
        Exception npe = new NullPointerException();

        //THEN
        when(exerciseService.findById(idExercise)).thenThrow(npe);

        assertEquals(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR),exerciseRestController.getOneExercise(idExercise));

    }

    @Test
    public void checkExercise(){
        //GIVEN
        Long idExercise = 2L;
        Boolean b = true;
        AnswerDto answerAct = new AnswerDto();

        //THEN
        when(exerciseService.checkAnswer(idExercise,answerAct)).thenReturn(b);

        assertEquals(new ResponseEntity<>(b, HttpStatus.OK),exerciseRestController.checkExercise(idExercise,answerAct));

    }

    @Test
    public void checkExerciseError(){
        //GIVEN
        Long idExercise = 2L;
        Exception npe = new NullPointerException();
        AnswerDto answerAct = new AnswerDto();

        //THEN
        when(exerciseService.checkAnswer(idExercise,answerAct)).thenThrow(npe);

        assertEquals(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR),exerciseRestController.checkExercise(idExercise,answerAct));

    }




}
