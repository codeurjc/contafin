package com.daw.contafin.exercise;

import com.daw.contafin.ImageService;
import com.daw.contafin.answer.AnswerDto;
import com.daw.contafin.completedExercise.CompletedExerciseService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ExerciseServiceTests {

    @InjectMocks
    ExerciseService exerciseService;

    @Mock
    ExerciseRepository exerciseRepository;

    @Mock
    ImageService imageService;

    @Mock
    ExerciseMapper exerciseMapper;

    @Test
    public void findById(){
        //GIVEN
        Long id = 2L;
        ExerciseDto exerciseDto = new ExerciseDto();
        Exercise exercise = new Exercise();

        //THEN
        when(exerciseRepository.findById(id)).thenReturn(Optional.of(exercise));
        when(exerciseMapper.ExerciseToExerciseDto(exercise)).thenReturn(exerciseDto);

        exerciseService.findById(id);

    }

    @Test
    public void findByIdError(){
        //GIVEN
        Long id = 2L;
        Exception npe = new NullPointerException();

        //THEN
        when(exerciseRepository.findById(id)).thenReturn(null);
        when(exerciseMapper.ExerciseToExerciseDto(null)).thenThrow(npe);

        exerciseService.findById(id);
    }

    @Test
    public void findAll(){
        //GIVEN
        List<Exercise> exercises = new ArrayList<>();
        List<ExerciseDto> exercisesDto = new ArrayList<>();

        //THEN
        when(exerciseRepository.findAll()).thenReturn(exercises);
        when(exerciseMapper.ExercisesToExercisesDto(exercises)).thenReturn(exercisesDto);

        exerciseService.findAll();

    }

    @Test
    public void findAllError(){
        //GIVEN
        Exception npe = new NullPointerException();

        //THEN
        when(exerciseRepository.findAll()).thenThrow(npe);

        exerciseService.findAll();

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

    }

    @Test
    public void saveError(){
        //GIVEN
        Exception npe = new NullPointerException();
        //THEN
        when(exerciseMapper.ExerciseDtoToExercise(null)).thenThrow(npe);

        exerciseService.save(null);

    }




}
