package com.daw.contafin.completedLesson;

import com.daw.contafin.completedExercise.CompletedExercise;
import com.daw.contafin.completedExercise.CompletedExerciseDto;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper( componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface CompletedLessonMapper {

    CompletedLessonDto CompletedLessonToCompletedLessonDto(CompletedLesson completedLesson);
    CompletedLesson CompletedLessonDtoToCompletedLesson(CompletedLessonDto completedLessonDto);

    List<CompletedLessonDto> CompletedLessonsToCompletedLessonsDto(Collection<CompletedLesson> completedLessons);
    List<CompletedLesson> CompletedLessonsDtoToCompletedLessons(Collection<CompletedLessonDto> completedLessonsDto);
}
