package com.daw.contafin.mapper;

import static org.junit.jupiter.api.Assertions.*;

import com.daw.contafin.dto.LessonDto;
import com.daw.contafin.entity.Lesson;
import com.daw.contafin.mapper.LessonMapperImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class LessonMapperImplTest {

    @Autowired
    private LessonMapper lessonMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testLessonToLessonDto() {
        Lesson lesson = new Lesson();
        lesson.setId(1L);
        lesson.setName("title");
        // Mock other fields if necessary

        LessonDto lessonDto = lessonMapper.LessonToLessonDto(lesson);

        assertNotNull(lessonDto);
        assertEquals(lesson.getId(), lessonDto.getId());
        assertEquals(lesson.getName(), lessonDto.getName());
        // Add assertions for other fields if necessary
    }

    @Test
    public void testLessonDtoToLesson() {
        LessonDto lessonDto = new LessonDto();
        lessonDto.setId(1L);
        lessonDto.setName("title");
        // Mock other fields if necessary

        Lesson lesson = lessonMapper.LessonDtoToLesson(lessonDto);

        assertNotNull(lesson);
        assertEquals(lessonDto.getId(), lesson.getId());
        assertEquals(lessonDto.getName(), lesson.getName());
        // Add assertions for other fields if necessary
    }

    @Test
    public void testLessonsToLessonsDto() {
        Lesson lesson1 = new Lesson();
        lesson1.setId(1L);
        Lesson lesson2 = new Lesson();
        lesson2.setId(2L);
        List<Lesson> lessons = Arrays.asList(lesson1, lesson2);

        List<LessonDto> lessonDtos = lessonMapper.LessonsToLessonsDto(lessons);

        assertNotNull(lessonDtos);
        assertEquals(2, lessonDtos.size());
        assertEquals(lesson1.getId(), lessonDtos.get(0).getId());
        assertEquals(lesson2.getId(), lessonDtos.get(1).getId());
    }

    @Test
    public void testLessonDtoListToLessonList() {
        LessonDto lessonDto1 = new LessonDto();
        lessonDto1.setId(1L);
        LessonDto lessonDto2 = new LessonDto();
        lessonDto2.setId(2L);
        List<LessonDto> lessonDtos = Arrays.asList(lessonDto1, lessonDto2);

        List<Lesson> lessons = lessonMapper.LessonDtoListToLessonList(lessonDtos);

        assertNotNull(lessons);
        assertEquals(2, lessons.size());
        assertEquals(lessonDto1.getId(), lessons.get(0).getId());
        assertEquals(lessonDto2.getId(), lessons.get(1).getId());
    }
}