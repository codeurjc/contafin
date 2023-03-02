package com.daw.contafin.completedExercise;

import com.daw.contafin.answer.*;
import com.daw.contafin.completedLesson.CompletedLessonService;
import com.daw.contafin.exercise.Exercise;
import com.daw.contafin.exercise.ExerciseDto;
import com.daw.contafin.exercise.ExerciseMapper;
import com.daw.contafin.exercise.ExerciseService;
import com.daw.contafin.lesson.LessonDto;
import com.daw.contafin.lesson.LessonService;
import com.daw.contafin.user.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CompletedExerciseServiceTests {

    @InjectMocks
    @Spy
    CompletedExerciseService completedExerciseService;

    @Mock
    CompletedExerciseRepository completedExerciseRepository;

    @Mock
    LessonService lessonService;

    @Mock
    ExerciseService exerciseService;

    @Mock
    CompletedExerciseMapper completedExerciseMapper;

    @Mock
    UserMapper userMapper;

    @Mock
    ExerciseMapper exerciseMapper;

    @Mock
    UserComponent userComponent;

    @Mock
    UserService userService;

    @Mock
    CompletedLessonService completedLessonService;

    @Test
    public void numExercisesCompleted(){
        //GIVEN
        Long idLesson = 1L;
        UserDto userDto = new UserDto();
        User user = new User();
        LessonDto lessonDto = new LessonDto();
        ExerciseDto exerciseDto = new ExerciseDto();
        Exercise exercise = new Exercise();
        CompletedExercise completedExercise = new CompletedExercise();
        List<ExerciseDto> exerciseDtoList = new ArrayList<>();
        exerciseDtoList.add(exerciseDto);
        lessonDto.setExercises(exerciseDtoList);


        //THEN
        when(userMapper.UserDtoToUser(userDto)).thenReturn(user);
        when(lessonService.findById(idLesson)).thenReturn(lessonDto);
        when(exerciseMapper.ExerciseDtoToExercise(exerciseDto)).thenReturn(exercise);
        when(completedExerciseRepository.findByUserAndExercise(user,exercise)).thenReturn(completedExercise);
        doNothing().when(completedExerciseRepository).delete(completedExercise);

        completedExerciseService.numExercisesCompleted(idLesson,userDto);

    }

    @Test
    public void numExercisesCompletedError(){
        //GIVEN
        Exception npe = new NullPointerException();
        Long idLesson = 1L;

        //THEN
        when(userMapper.UserDtoToUser(null)).thenThrow(npe);

        completedExerciseService.numExercisesCompleted(idLesson,null);
    }

    @Test
    public void save(){
        //GIVEN
        CompletedExerciseDto completedExerciseDto = new CompletedExerciseDto();
        CompletedExercise completedExercise = new CompletedExercise();

        //THEN
        when(completedExerciseMapper.CompletedExerciseDtoToCompletedExercise(completedExerciseDto)).thenReturn(completedExercise);
        when(completedExerciseRepository.save(completedExercise)).thenReturn(completedExercise);

        completedExerciseService.save(completedExerciseDto);

    }

    @Test
    public void saveError(){
        //GIVEN
        Exception npe = new NullPointerException();
        //THEN
        when(completedExerciseMapper.CompletedExerciseDtoToCompletedExercise(null)).thenThrow(npe);


        completedExerciseService.save(null);

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
        when(userComponent.getLoggedUser()).thenReturn(user);
        when(exerciseService.findById(id)).thenReturn(exercise);
        when(exerciseService.findById(id2)).thenReturn(exercise2);
        Mockito.doReturn(new CompletedExerciseDto()).when(completedExerciseService).save(new CompletedExerciseDto(user, exercise, 0));
        when(userComponent.isLoggedUser()).thenReturn(true);
        doNothing().when(userService).save(user);

        completedExerciseService.checkAnswer(id,answerAct);

        completedExerciseService.checkAnswer(id2,answerAct);

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

        completedExerciseService.checkAnswer(id,answerAct);
    }


    @Test
    public void checkLessonComplete(){
        //GIVEN
        Long idLesson = 1L;
        LessonDto lessonDto = new LessonDto();
        ExerciseDto exercise = new ExerciseDto();
        List<ExerciseDto> exerciseDtoList = new ArrayList<>();
        exerciseDtoList.add(exercise);
        lessonDto.setExercises(exerciseDtoList);
        UserDto user = new UserDto();


        //THEN
        when(userComponent.getLoggedUser()).thenReturn(user);
        when(lessonService.findById(idLesson)).thenReturn(lessonDto);
        when(completedLessonService.existCompletedLesson(user, lessonDto)).thenReturn(false);
        doNothing().when(userService).completedLesson(user, idLesson);
        Mockito.doReturn(1).when(completedExerciseService).numExercisesCompleted(idLesson, user);

        completedExerciseService.checkLessonComplete(idLesson);


    }

    @Test
    public void checkLessonCompleteError(){
        //GIVEN
        Exception npe = new NullPointerException();
        Long idLesson = 1L;

        //THEN
        when(lessonService.findById(idLesson)).thenThrow(npe);

        completedExerciseService.checkLessonComplete(idLesson);
    }




}
