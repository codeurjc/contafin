package com.daw.contafin.exercise;

import com.daw.contafin.completedLesson.CompletedLesson;
import com.daw.contafin.completedLesson.CompletedLessonDto;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper( componentModel = "spring")
public interface ExerciseMapper {

    ExerciseDto ExerciseToExerciseDto(Exercise exercise);
    Exercise ExerciseDtoToExercise(ExerciseDto exerciseDto);

    List<ExerciseDto> ExercisesToExercisesDto(Collection<Exercise> exercises);
    List<Exercise> ExercisesDtoToExercises(Collection<ExerciseDto> exercisesDto);
}
