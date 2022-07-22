package com.daw.contafin.completedExercise;

import com.daw.contafin.answer.Answer;
import com.daw.contafin.answer.AnswerDto;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper( componentModel = "spring")
public interface CompletedExerciseMapper {

    CompletedExerciseDto CompletedExerciseDtoToCompletedExercise(CompletedExercise completedExercise);
    CompletedExercise CompletedExerciseToCompletedExerciseDto(CompletedExerciseDto completedExerciseDto);

    List<CompletedExerciseDto> CompletedExercisesDtoToCompletedExercises(Collection<CompletedExercise> completedExercises);
    List<CompletedExercise> CompletedExercisesToCompletedExercisesDto(Collection<CompletedExerciseDto> completedExercisesDto);
}
