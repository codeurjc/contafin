package com.daw.contafin.exercise;

import com.daw.contafin.answer.AnswerDto;
import com.daw.contafin.completedExercise.CompletedExerciseService;
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
    @Mock
    CompletedExerciseService completedExerciseService;

    @Test
    public void getOneExercise(){
        //GIVEN
        Long idExercise = 2L;
        ExerciseDto exercise = new ExerciseDto();

        //THEN
        when(exerciseService.findById(idExercise)).thenReturn(exercise);

        ResponseEntity response = exerciseRestController.getOneExercise(idExercise);

        assertEquals(new ResponseEntity<>(exercise, HttpStatus.OK),response);

    }

    @Test
    public void getOneExerciseError(){
        //GIVEN
        Long idExercise = 2L;
        Exception npe = new NullPointerException();

        //THEN
        when(exerciseService.findById(idExercise)).thenThrow(npe);

        ResponseEntity response = exerciseRestController.getOneExercise(idExercise);

        assertEquals(new ResponseEntity<>(HttpStatus.NOT_FOUND),response);

    }

    @Test
    public void checkExercise(){
        //GIVEN
        Long idExercise = 2L;
        Boolean b = true;
        AnswerDto answerAct = new AnswerDto();

        //THEN
        when(completedExerciseService.checkAnswer(idExercise,answerAct)).thenReturn(b);

        ResponseEntity response = exerciseRestController.checkExercise(idExercise,answerAct);

        assertEquals(new ResponseEntity<>(b, HttpStatus.OK),response);

    }

    @Test
    public void checkExerciseError(){
        //GIVEN
        Long idExercise = 2L;
        Exception npe = new NullPointerException();
        AnswerDto answerAct = new AnswerDto();

        //THEN
        when(completedExerciseService.checkAnswer(idExercise,answerAct)).thenThrow(npe);

        ResponseEntity response = exerciseRestController.checkExercise(idExercise,answerAct);

        assertEquals(new ResponseEntity<>(HttpStatus.NOT_FOUND),response);

    }




}
