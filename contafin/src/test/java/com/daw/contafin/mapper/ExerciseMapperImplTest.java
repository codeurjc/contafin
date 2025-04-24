package com.daw.contafin.mapper;

import com.daw.contafin.dto.ExerciseDto;
import com.daw.contafin.dto.UserDto;
import com.daw.contafin.entity.Exercise;
import com.daw.contafin.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ExerciseMapperImplTest {

    @Autowired
    private ExerciseMapperImpl exerciseMapper;

    @BeforeEach
    public void setUp() {

    }

    @Test
    public void testExerciseToExerciseDto() {
        Exercise exercise = new Exercise();
        exercise.setId(1L);
        exercise.setKind(1);
        exercise.setStatement("statement");
        exercise.setImage1(new byte[]{1, 2, 3});
        exercise.setImage2(new byte[]{4, 5, 6});
        exercise.setImage3(new byte[]{7, 8, 9});
        exercise.setTexts(Arrays.asList("text1", "text2"));
        // Mock the answerMapper behavior if necessar

        ExerciseDto exerciseDto = exerciseMapper.ExerciseToExerciseDto(exercise);

        assertNotNull(exerciseDto);
        assertEquals(exercise.getId(), exerciseDto.getId());
        assertEquals(exercise.getKind(), exerciseDto.getKind());
        assertEquals(exercise.getStatement(), exerciseDto.getStatement());
        assertArrayEquals(exercise.getImage1(), exerciseDto.getImage1());
        assertArrayEquals(exercise.getImage2(), exerciseDto.getImage2());
        assertArrayEquals(exercise.getImage3(), exerciseDto.getImage3());
        assertEquals(exercise.getTexts(), exerciseDto.getTexts());
        // Add assertions for answerMapper if necessary
    }

    @Test
    public void testExerciseDtoToExercise() {
        ExerciseDto exerciseDto = new ExerciseDto();
        exerciseDto.setId(1L);
        exerciseDto.setKind(1);
        exerciseDto.setStatement("statement");
        exerciseDto.setImage1(new byte[]{1, 2, 3});
        exerciseDto.setImage2(new byte[]{4, 5, 6});
        exerciseDto.setImage3(new byte[]{7, 8, 9});
        exerciseDto.setTexts(Arrays.asList("text1", "text2"));
        // Mock the answerMapper behavior if necessary

        Exercise exercise = exerciseMapper.ExerciseDtoToExercise(exerciseDto);

        assertNotNull(exercise);
        assertEquals(exerciseDto.getId(), exercise.getId());
        assertEquals(exerciseDto.getKind(), exercise.getKind());
        assertEquals(exerciseDto.getStatement(), exercise.getStatement());
        assertArrayEquals(exerciseDto.getImage1(), exercise.getImage1());
        assertArrayEquals(exerciseDto.getImage2(), exercise.getImage2());
        assertArrayEquals(exerciseDto.getImage3(), exercise.getImage3());
        assertEquals(exerciseDto.getTexts(), exercise.getTexts());
        // Add assertions for answerMapper if necessary
    }

    @Test
    public void testExercisesToExercisesDto() {
        Exercise exercise1 = new Exercise();
        exercise1.setId(1L);
        Exercise exercise2 = new Exercise();
        exercise2.setId(2L);
        List<Exercise> exercises = Arrays.asList(exercise1, exercise2);

        List<ExerciseDto> exerciseDtos = exerciseMapper.ExercisesToExercisesDto(exercises);

        assertNotNull(exerciseDtos);
        assertEquals(2, exerciseDtos.size());
        assertEquals(exercise1.getId(), exerciseDtos.get(0).getId());
        assertEquals(exercise2.getId(), exerciseDtos.get(1).getId());
    }

    @Test
    public void testExerciseDtoToExerciseWithUser() {
        ExerciseDto exerciseDto = new ExerciseDto();
        exerciseDto.setId(1L);
        exerciseDto.setKind(1);
        exerciseDto.setStatement("statement");
        exerciseDto.setImage1(new byte[]{1, 2, 3});
        exerciseDto.setImage2(new byte[]{4, 5, 6});
        exerciseDto.setImage3(new byte[]{7, 8, 9});
        exerciseDto.setTexts(Arrays.asList("text1", "text2"));

        Exercise exercise = exerciseMapper.ExerciseDtoToExercise(exerciseDto);

        assertNotNull(exercise);
        assertEquals(exerciseDto.getId(), exercise.getId());
        assertEquals(exerciseDto.getKind(), exercise.getKind());
        assertEquals(exerciseDto.getStatement(), exercise.getStatement());
        assertArrayEquals(exerciseDto.getImage1(), exercise.getImage1());
        assertArrayEquals(exerciseDto.getImage2(), exercise.getImage2());
        assertArrayEquals(exerciseDto.getImage3(), exercise.getImage3());
        assertEquals(exerciseDto.getTexts(), exercise.getTexts());
    }
}