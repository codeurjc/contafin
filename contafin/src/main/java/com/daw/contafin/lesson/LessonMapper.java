package com.daw.contafin.lesson;

import com.daw.contafin.exercise.Exercise;
import com.daw.contafin.exercise.ExerciseDto;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper( componentModel = "spring")
public interface LessonMapper {

    LessonDto LessonToLessonDto(Lesson lesson);
    Lesson LessonDtoToLesson(LessonDto lessonDto);

    List<LessonDto> LessonsToLessonsDto(Collection<Lesson> lessons);
    List<Lesson> LessonsDtoToLessons(Collection<LessonDto> lessonsDto);
}
