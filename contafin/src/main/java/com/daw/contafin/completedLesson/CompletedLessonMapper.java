package com.daw.contafin.completedLesson;

import com.daw.contafin.completedExercise.CompletedExercise;
import com.daw.contafin.completedExercise.CompletedExerciseDto;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper( componentModel = "spring")
public interface CompletedLessonMapper {

    CompletedLessonDto CompletedLessonDtoToCompletedLesson(CompletedLesson completedLesson);
    CompletedLesson CompletedLessonToCompletedLessonDto(CompletedLessonDto completedLessonDto);

    List<CompletedLessonDto> CompletedLessonsDtoToCompletedLessons(Collection<CompletedLesson> completedLessons);
    List<CompletedLesson> CompletedLessonsToCompletedLessonsDto(Collection<CompletedLessonDto> completedLessonsDto);
}
