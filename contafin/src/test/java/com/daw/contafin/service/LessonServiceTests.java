package com.daw.contafin.service;

import com.daw.contafin.dto.LessonDto;
import com.daw.contafin.entity.Lesson;
import com.daw.contafin.mapper.LessonMapper;
import com.daw.contafin.repository.LessonRepository;
import com.daw.contafin.service.LessonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.*;

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
        long id = 2L;
        LessonDto lessonDto = new LessonDto();
        Lesson lesson = new Lesson();

        //THEN
        when(lessonRepository.findById(id)).thenReturn(lesson);
        when(lessonMapper.LessonToLessonDto(lesson)).thenReturn(lessonDto);

        assertEquals(lessonService.findById(id), lessonDto);

    }

    @Test
    public void findByIdError(){
        //GIVEN
        long id = 2L;
        Exception npe = new NullPointerException();

        //THEN
        when(lessonRepository.findById(id)).thenReturn(null);
        when(lessonMapper.LessonToLessonDto(null)).thenThrow(npe);

        assertEquals(lessonService.findById(id), null);
    }

    @Test
    public void findAll(){
        //GIVEN
        List<Lesson> lessons = new ArrayList<>();
        List<LessonDto> lessonDtos = new ArrayList<>();

        //THEN
        when(lessonRepository.findAll()).thenReturn(lessons);
        when(lessonMapper.LessonsToLessonsDto(lessons)).thenReturn(lessonDtos);

        assertEquals(lessonService.findAll(), lessonDtos);

    }

    @Test
    public void findAllError(){
        //GIVEN
        Exception npe = new NullPointerException();

        //THEN
        when(lessonRepository.findAll()).thenThrow(npe);

        assertEquals(lessonService.findAll(), null);

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
        verify(lessonRepository).save(same(lesson));

    }

    @Test
    public void saveError(){
        //GIVEN
        Exception npe = new NullPointerException();
        //THEN
        when(lessonMapper.LessonDtoToLesson(null)).thenThrow(npe);

        lessonService.save(null);
        verifyNoInteractions(lessonRepository);

    }

    @Test
    public void delete(){
        //GIVEN
        Long id = 1L;

        //THEN
        Mockito.doNothing().when(lessonRepository).deleteById(id);

        lessonService.delete(id);
        verify(lessonRepository).deleteById(same(id));

    }

    @Test
    public void deleteError(){
        //GIVEN
        Long id = 1L;
        Exception npe = new NullPointerException();
        //THEN
        Mockito.doThrow(npe).when(lessonRepository).deleteById(id);

        lessonService.delete(id);

    }


}
