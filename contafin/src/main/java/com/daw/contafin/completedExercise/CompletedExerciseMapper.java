package com.daw.contafin.completedExercise;

import com.daw.contafin.answer.Answer;
import com.daw.contafin.answer.AnswerDto;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper( componentModel = "spring")
public interface CompletedExerciseMapper {

    CompletedExerciseDto CompletedExerciseToCompletedExerciseDto(CompletedExercise completedExercise);
    CompletedExercise CompletedExerciseDtoToCompletedExercise(CompletedExerciseDto completedExerciseDto);

    List<CompletedExerciseDto> CompletedExercisesToCompletedExercisesDto(Collection<CompletedExercise> completedExercises);
    List<CompletedExercise> CompletedExercisesDtoToCompletedExercises(Collection<CompletedExerciseDto> completedExercisesDto);
}
