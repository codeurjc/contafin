package com.daw.contafin.lesson;

import com.daw.contafin.ImageService;
import com.daw.contafin.exercise.*;
import com.daw.contafin.unit.UnitMapper;
import com.daw.contafin.unit.UnitRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class LessonServiceTests {

    @InjectMocks
    LessonService lessonService;

    @Mock
    LessonRepository lessonRepository;

    @Mock
    LessonMapper lessonMapper;


    @Test
    public void findById(){
        //GIVEN
        Long id = 2L;
        LessonDto lessonDto = new LessonDto();
        Lesson lesson = new Lesson();

        //THEN
        when(lessonRepository.findById(id)).thenReturn(Optional.of(lesson));
        when(lessonMapper.LessonToLessonDto(lesson)).thenReturn(lessonDto);

        lessonService.findById(id);

    }

    @Test
    public void findByIdError(){
        //GIVEN
        Long id = 2L;
        Exception npe = new NullPointerException();

        //THEN
        when(lessonRepository.findById(id)).thenReturn(null);
        when(lessonMapper.LessonToLessonDto(null)).thenThrow(npe);

        lessonService.findById(id);
    }

    @Test
    public void findAll(){
        //GIVEN
        List<Lesson> lessons = new ArrayList<>();
        List<LessonDto> lessonDtos = new ArrayList<>();

        //THEN
        when(lessonRepository.findAll()).thenReturn(lessons);
        when(lessonMapper.LessonsToLessonsDto(lessons)).thenReturn(lessonDtos);

        lessonService.findAll();

    }

    @Test
    public void findAllError(){
        //GIVEN
        Exception npe = new NullPointerException();

        //THEN
        when(lessonRepository.findAll()).thenThrow(npe);

        lessonService.findAll();

    }

    @Test
    public void save(){
        //GIVEN
        LessonDto lessonDto = new LessonDto();
        Lesson lesson = new Lesson();

        //THEN
        when(lessonMapper.LessonDtoToLesson(lessonDto)).thenReturn(lesson);
        when(lessonRepository.save(lesson)).thenReturn(lesson);

        lessonService.save(lessonDto);

    }

    @Test
    public void saveError(){
        //GIVEN
        Exception npe = new NullPointerException();
        //THEN
        when(lessonMapper.LessonDtoToLesson(null)).thenThrow(npe);

        lessonService.save(null);

    }




}
