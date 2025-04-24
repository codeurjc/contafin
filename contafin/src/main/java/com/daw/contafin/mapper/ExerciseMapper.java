package com.daw.contafin.mapper;

import com.daw.contafin.dto.ExerciseDto;
import com.daw.contafin.entity.Exercise;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper( componentModel = "spring", builder = @Builder(disableBuilder = true), uses = AnswerMapper.class)
public interface ExerciseMapper {

    ExerciseDto ExerciseToExerciseDto(Exercise exercise);
    Exercise ExerciseDtoToExercise(ExerciseDto exerciseDto);

    List<ExerciseDto> ExercisesToExercisesDto(Collection<Exercise> exercises);
    List<Exercise> ExercisesDtoToExercises(Collection<ExerciseDto> exerciseDtos);
}
