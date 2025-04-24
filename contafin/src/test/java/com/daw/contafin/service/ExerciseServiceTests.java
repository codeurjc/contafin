package com.daw.contafin.service;

import com.daw.contafin.dto.AnswerDto;
import com.daw.contafin.dto.UserDto;
import com.daw.contafin.dto.ExerciseDto;
import com.daw.contafin.entity.Exercise;
import com.daw.contafin.mapper.ExerciseMapper;
import com.daw.contafin.repository.ExerciseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ExerciseServiceTests {

    @InjectMocks
    ExerciseService exerciseService;

    @Mock
    ExerciseRepository exerciseRepository;

    @Mock
    ExerciseMapper exerciseMapper;

    @Test
    public void findById(){
        //GIVEN
        long id = 2L;
        ExerciseDto exerciseDto = new ExerciseDto();
        Exercise exercise = new Exercise();

        //THEN
        when(exerciseRepository.findById(id)).thenReturn(exercise);
        when(exerciseMapper.ExerciseToExerciseDto(exercise)).thenReturn(exerciseDto);

        assertEquals(exerciseService.findById(id), exerciseDto);

    }

    @Test
    public void findByIdError(){
        //GIVEN
        long id = 2L;
        Exception npe = new NullPointerException();

        //THEN
        when(exerciseRepository.findById(id)).thenReturn(null);
        when(exerciseMapper.ExerciseToExerciseDto(null)).thenThrow(npe);

        assertEquals(exerciseService.findById(id), null);
    }

    @Test
    public void findAll(){
        //GIVEN
        List<Exercise> exercises = new ArrayList<>();
        List<ExerciseDto> exercisesDto = new ArrayList<>();

        //THEN
        when(exerciseRepository.findAll()).thenReturn(exercises);
        when(exerciseMapper.ExercisesToExercisesDto(exercises)).thenReturn(exercisesDto);

        assertEquals(exerciseService.findAll(), exercisesDto);

    }

    @Test
    public void findAllError(){
        //GIVEN
        Exception npe = new NullPointerException();

        //THEN
        when(exerciseRepository.findAll()).thenThrow(npe);

        assertEquals(exerciseService.findAll(), null);

    }

    @Test
    public void save(){
        //GIVEN
        ExerciseDto exerciseDto = new ExerciseDto();
        Exercise exercise = new Exercise();

        //THEN
        when(exerciseMapper.ExerciseDtoToExercise(exerciseDto)).thenReturn(exercise);
        when(exerciseRepository.save(exercise)).thenReturn(exercise);
        when(exerciseMapper.ExerciseToExerciseDto(exercise)).thenReturn(exerciseDto);

        exerciseService.save(exerciseDto);

        verify(exerciseRepository).save(same(exercise));

    }

    @Test
    public void saveError(){
        //GIVEN
        Exception npe = new NullPointerException();
        //THEN
        when(exerciseMapper.ExerciseDtoToExercise(null)).thenThrow(npe);

       exerciseService.save(null);

        verifyNoInteractions(exerciseRepository);

    }

    @Test
    public void delete(){
        //GIVEN
        Long id = 1L;

        //THEN
        Mockito.doNothing().when(exerciseRepository).deleteById(id);

        exerciseService.delete(id);
        verify(exerciseRepository).deleteById(same(id));

    }

    @Test
    public void deleteError(){
        //GIVEN
        Long id = 1L;
        Exception npe = new NullPointerException();
        //THEN
        Mockito.doThrow(npe).when(exerciseRepository).deleteById(id);

        exerciseService.delete(id);

    }

    @Test
    public void checkAnswer(){
        //GIVEN
        Long id = 1L;
        Long id2 = 2L;
        AnswerDto answerAct = new AnswerDto();
        answerAct.setResult("1");
        UserDto user = new UserDto();
        user.setPoints(6);
        ExerciseDto exercise = new ExerciseDto();
        exercise.setAnswer(answerAct);
        exercise.setKind(2);
        ExerciseDto exercise2 = new ExerciseDto();
        exercise2.setAnswer(new AnswerDto("2"));
        exercise2.setKind(1);


        //THEN
        when(exerciseService.findById(id)).thenReturn(exercise);
        when(exerciseService.findById(id2)).thenReturn(exercise2);


        exerciseService.checkAnswer(id,answerAct);

        exerciseService.checkAnswer(id2,answerAct);

    }

    @Test
    public void checkAnswerError(){
        //GIVEN
        Exception npe = new NullPointerException();
        Long id = 1L;
        AnswerDto answerAct = new AnswerDto();
        answerAct.setResult("1");

        //THEN
        when(exerciseService.findById(id)).thenThrow(npe);

        assertEquals( exerciseService.checkAnswer(id,answerAct), false);

       ;
    }




}
