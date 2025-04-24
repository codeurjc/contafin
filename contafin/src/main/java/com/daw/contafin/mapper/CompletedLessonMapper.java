package com.daw.contafin.mapper;

import com.daw.contafin.dto.CompletedLessonDto;
import com.daw.contafin.entity.CompletedLesson;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper( componentModel = "spring", builder = @Builder(disableBuilder = true), uses = {LessonMapper.class, UserMapper.class})
public interface CompletedLessonMapper {

    CompletedLessonDto CompletedLessonToCompletedLessonDto(CompletedLesson completedLesson);
    CompletedLesson CompletedLessonDtoToCompletedLesson(CompletedLessonDto completedLessonDto);

    List<CompletedLessonDto> CompletedLessonsToCompletedLessonsDto(Collection<CompletedLesson> completedLessons);
}













