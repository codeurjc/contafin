package com.daw.contafin.mapper;

import static org.junit.jupiter.api.Assertions.*;

import com.daw.contafin.dto.CompletedLessonDto;
import com.daw.contafin.entity.CompletedLesson;
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
public class CompletedLessonMapperImplTest {

    @Autowired
    private CompletedLessonMapper completedLessonMapper;

    @Test
    public void testCompletedLessonToCompletedLessonDto() {
        CompletedLesson completedLesson = new CompletedLesson();
        completedLesson.setId(1L);
        completedLesson.setDate(new java.sql.Date(System.currentTimeMillis()));
        // Mock other fields if necessary

        CompletedLessonDto completedLessonDto = completedLessonMapper.CompletedLessonToCompletedLessonDto(completedLesson);

        assertNotNull(completedLessonDto);
        assertEquals(completedLesson.getId(), completedLessonDto.getId());
        assertEquals(completedLesson.getDate(), completedLessonDto.getDate());
        // Add assertions for other fields if necessary
    }

    @Test
    public void testCompletedLessonDtoToCompletedLesson() {
        CompletedLessonDto completedLessonDto = new CompletedLessonDto();
        completedLessonDto.setId(1L);
        completedLessonDto.setDate(new java.sql.Date(System.currentTimeMillis()));
        // Mock other fields if necessary

        CompletedLesson completedLesson = completedLessonMapper.CompletedLessonDtoToCompletedLesson(completedLessonDto);

        assertNotNull(completedLesson);
        assertEquals(completedLessonDto.getId(), completedLesson.getId());
        assertEquals(completedLessonDto.getDate(), completedLesson.getDate());
        // Add assertions for other fields if necessary
    }

    @Test
    public void testCompletedLessonsToCompletedLessonsDto() {
        CompletedLesson completedLesson1 = new CompletedLesson();
        completedLesson1.setId(1L);
        CompletedLesson completedLesson2 = new CompletedLesson();
        completedLesson2.setId(2L);
        List<CompletedLesson> completedLessons = Arrays.asList(completedLesson1, completedLesson2);

        List<CompletedLessonDto> completedLessonDtos = completedLessonMapper.CompletedLessonsToCompletedLessonsDto(completedLessons);

        assertNotNull(completedLessonDtos);
        assertEquals(2, completedLessonDtos.size());
        assertEquals(completedLesson1.getId(), completedLessonDtos.get(0).getId());
        assertEquals(completedLesson2.getId(), completedLessonDtos.get(1).getId());
    }
}