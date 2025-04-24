package com.daw.contafin.mapper;

import com.daw.contafin.dto.LessonDto;
import com.daw.contafin.entity.Lesson;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper( componentModel = "spring", builder = @Builder(disableBuilder = true), uses = ExerciseMapper.class)
public interface LessonMapper {

    LessonDto LessonToLessonDto(Lesson lesson);
    Lesson LessonDtoToLesson(LessonDto lessonDto);

    List<LessonDto> LessonsToLessonsDto(Collection<Lesson> lessons);
    List<Lesson> LessonDtoListToLessonList(Collection<LessonDto> lessonDtos);
}
