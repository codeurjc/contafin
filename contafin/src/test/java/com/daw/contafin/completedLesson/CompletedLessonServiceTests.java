package com.daw.contafin.completedLesson;

import com.daw.contafin.lesson.Lesson;
import com.daw.contafin.lesson.LessonDto;
import com.daw.contafin.lesson.LessonMapper;
import com.daw.contafin.unit.UnitDto;
import com.daw.contafin.unit.UnitService;
import com.daw.contafin.user.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CompletedLessonServiceTests {

    @InjectMocks
    @Spy
    CompletedLessonService completedLessonService;

    @Mock
    CompletedLessonRepository completedLessonRepository;

    @Mock
    UserMapper userMapper;

    @Mock
    CompletedLessonMapper completedLessonMapper;

    @Mock
    LessonMapper lessonMapper;

    @Mock
    UserComponent userComponent;

    @Mock
    UnitService unitService;

    @Test
    public void getCompletedLessons(){
        //GIVEN
        UserDto userDto =new UserDto();
        User user = new User();
        Date d = new Date();

        //THEN
        when(userMapper.UserDtoToUser(userDto)).thenReturn(user);
        when(completedLessonRepository.findByUserAndDate(user, d)).thenReturn(new ArrayList<>());

        completedLessonService.getCompletedLessons(userDto, d);

    }

    @Test
    public void getCompletedLessonsError(){
        //GIVEN
        Exception npe = new NullPointerException();
        Date d = new Date();

        //THEN
        when(userMapper.UserDtoToUser(null)).thenThrow(npe);

        completedLessonService.getCompletedLessons(null, d);
    }

    @Test
    public void save(){
        //GIVEN
        CompletedLessonDto completedLessonDto = new CompletedLessonDto();
        CompletedLesson completedLesson = new CompletedLesson();

        //THEN
        when(completedLessonMapper.CompletedLessonDtoToCompletedLesson(completedLessonDto)).thenReturn(completedLesson);
        when(completedLessonRepository.save(completedLesson)).thenReturn(completedLesson);

        completedLessonService.save(completedLessonDto);

    }

    @Test
    public void saveError(){
        //GIVEN
        Exception npe = new NullPointerException();
        //THEN
        Mockito.when(completedLessonMapper.CompletedLessonDtoToCompletedLesson(null)).thenThrow(npe);


        completedLessonService.save(null);

    }

    @Test
    public void existCompletedLesson(){
        //GIVEN
        UserDto userDto =new UserDto();
        User user = new User();
        LessonDto lessonDto = new LessonDto();
        Lesson lesson = new Lesson();
        CompletedLesson completedLesson = new CompletedLesson();

        //THEN
        when(userMapper.UserDtoToUser(userDto)).thenReturn(user);
        when(lessonMapper.LessonDtoToLesson(lessonDto)).thenReturn(lesson);
        when(completedLessonRepository.findByUserAndLesson(user, lesson)).thenReturn(completedLesson);

        completedLessonService.existCompletedLesson(userDto,lessonDto);

    }

    @Test
    public void existCompletedLessonError(){
        //GIVEN
        Exception npe = new NullPointerException();
        UserDto userDto =new UserDto();
        LessonDto lessonDto = new LessonDto();

        //THEN
        when(userMapper.UserDtoToUser(userDto)).thenThrow(npe);

        completedLessonService.existCompletedLesson(userDto,lessonDto);
    }


    @Test
    public void findByUser(){
        //GIVEN
        List<CompletedLessonDto> completedLessonDtoList = new ArrayList<>();
        List<CompletedLesson> completedLessonList = new ArrayList<>();
        UserDto userDto =new UserDto();
        User user = new User();

        //THEN
        when(userMapper.UserDtoToUser(userDto)).thenReturn(user);
        when(completedLessonRepository.findByUser(user)).thenReturn(completedLessonList);
        when(completedLessonMapper.CompletedLessonsToCompletedLessonsDto(completedLessonList)).thenReturn(completedLessonDtoList);

        completedLessonService.findByUser(userDto);

    }

    @Test
    public void findByUserError(){
        //GIVEN
        Exception npe = new NullPointerException();
        //THEN
        Mockito.when(userMapper.UserDtoToUser(null)).thenThrow(npe);

        completedLessonService.findByUser(null);;

    }

    @Test
    public void findByUserOrderByDateDesc(){
        //GIVEN
        List<CompletedLessonDto> completedLessonDtoList = new ArrayList<>();
        List<CompletedLesson> completedLessonList = new ArrayList<>();
        UserDto userDto =new UserDto();
        User user = new User();

        //THEN
        when(userMapper.UserDtoToUser(userDto)).thenReturn(user);
        when(completedLessonRepository.findByUserOrderByDateDesc(user)).thenReturn(completedLessonList);
        when(completedLessonMapper.CompletedLessonsToCompletedLessonsDto(completedLessonList)).thenReturn(completedLessonDtoList);

        completedLessonService.findByUserOrderByDateDesc(userDto);

    }

    @Test
    public void findByUserOrderByDateDescError(){
        //GIVEN
        Exception npe = new NullPointerException();
        //THEN
        Mockito.when(userMapper.UserDtoToUser(null)).thenThrow(npe);

        completedLessonService.findByUserOrderByDateDesc(null);;

    }

    @Test
    public void checkList(){
        //GIVEN
        Long idUnit = 1L;
        UserDto userDto = new UserDto();
        LessonDto lessonDto = new LessonDto();
        List<LessonDto> lessonDtoList = new ArrayList<>();
        lessonDtoList.add(lessonDto);
        UnitDto unitDto = new UnitDto();
        unitDto.setLessons(lessonDtoList);

        //THEN
        when(userComponent.getLoggedUser()).thenReturn(userDto);
        doReturn(true).when(completedLessonService).existCompletedLesson(userDto,lessonDto);
        when(unitService.findById(idUnit)).thenReturn(unitDto);

        completedLessonService.checkList(idUnit);

    }

    @Test
    public void checkListError(){
        //GIVEN
        Exception npe = new NullPointerException();

        //THEN
        when(userComponent.getLoggedUser()).thenThrow(npe);

        completedLessonService.checkList(null);

    }

    @Test
    public void numberOfCompletedLesson(){
        //GIVEN
        Long idUnit = 1L;
        List<Boolean> bList = new ArrayList<>();
        bList.add(true);
        //THEN
        doReturn(bList).when(completedLessonService).checkList(idUnit);

        completedLessonService.numberOfCompletedLesson(idUnit);
    }

    @Test
    public void numberOfCompletedLessonError(){
        //GIVEN
        Exception npe = new NullPointerException();

        //THEN
        doThrow(npe).when(completedLessonService).checkList(null);

        completedLessonService.numberOfCompletedLesson(null);

    }





}
