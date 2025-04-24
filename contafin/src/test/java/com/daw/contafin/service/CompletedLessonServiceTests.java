package com.daw.contafin.service;

import com.daw.contafin.SpringSecurityForUserControllerImplTestConfig;
import com.daw.contafin.dto.CompletedLessonDto;
import com.daw.contafin.dto.UserDto;
import com.daw.contafin.entity.CompletedLesson;
import com.daw.contafin.entity.Lesson;
import com.daw.contafin.dto.LessonDto;
import com.daw.contafin.entity.User;
import com.daw.contafin.mapper.CompletedLessonMapper;
import com.daw.contafin.mapper.LessonMapper;
import com.daw.contafin.dto.UnitDto;
import com.daw.contafin.mapper.UserMapper;
import com.daw.contafin.repository.CompletedLessonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SpringSecurityForUserControllerImplTestConfig.class)
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

        assertEquals(completedLessonService.getCompletedLessons(userDto, d), 0);

    }

    @Test
    public void getCompletedLessonsError(){
        //GIVEN
        Exception npe = new NullPointerException();
        Date d = new Date();

        //THEN
        when(userMapper.UserDtoToUser(null)).thenThrow(npe);

        assertEquals(completedLessonService.getCompletedLessons(null, d), 0);
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

        verify(completedLessonRepository).save(same(completedLesson));

    }

    @Test
    public void saveError(){
        //GIVEN
        Exception npe = new NullPointerException();
        //THEN
        Mockito.when(completedLessonMapper.CompletedLessonDtoToCompletedLesson(null)).thenThrow(npe);
        completedLessonService.save(null);
        verifyNoInteractions(completedLessonRepository);

    }

    @Test
    public void existCompletedLesson(){
        //GIVEN
        UserDto userDto =new UserDto();
        User user = new User();
        LessonDto lessonDto = new LessonDto();
        Lesson lesson = new Lesson();
        ArrayList<CompletedLesson> completedLessons = new ArrayList<>();


        //THEN
        when(userMapper.UserDtoToUser(userDto)).thenReturn(user);
        when(lessonMapper.LessonDtoToLesson(lessonDto)).thenReturn(lesson);
        when(completedLessonRepository.findByUserAndLesson(user, lesson)).thenReturn(completedLessons);

        assertEquals(completedLessonService.existCompletedLesson(userDto,lessonDto), false);

        completedLessons.add(new CompletedLesson());
        assertEquals(completedLessonService.existCompletedLesson(userDto,lessonDto), true);

    }

    @Test
    public void existCompletedLessonError(){
        //GIVEN
        Exception npe = new NullPointerException();
        UserDto userDto =new UserDto();
        LessonDto lessonDto = new LessonDto();

        //THEN
        when(userMapper.UserDtoToUser(userDto)).thenThrow(npe);

        assertEquals(completedLessonService.existCompletedLesson(userDto,lessonDto), false);
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

        assertEquals(completedLessonService.findByUser(userDto), completedLessonDtoList);

    }

    @Test
    public void findByUserError(){
        //GIVEN
        Exception npe = new NullPointerException();
        //THEN
        Mockito.when(userMapper.UserDtoToUser(null)).thenThrow(npe);

        assertEquals(completedLessonService.findByUser(null), null);

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

        assertEquals(completedLessonService.findByUserOrderByDateDesc(userDto), completedLessonDtoList);

    }

    @Test
    public void findByUserOrderByDateDescError(){
        //GIVEN
        Exception npe = new NullPointerException();
        //THEN
        Mockito.when(userMapper.UserDtoToUser(null)).thenThrow(npe);

        assertEquals(completedLessonService.findByUserOrderByDateDesc(null), null);

    }

    @Test
    @WithUserDetails(value="customUsername", userDetailsServiceBeanName="userDetailsService")
    public void checkListUser(){
        //GIVEN
        Long idUnit = 1L;
        UserDto userDto = new UserDto();
        LessonDto lessonDto = new LessonDto();
        List<LessonDto> lessonDtoList = new ArrayList<>();
        lessonDtoList.add(lessonDto);
        UnitDto unitDto = new UnitDto();
        unitDto.setLessons(lessonDtoList);
        ArrayList<Boolean> booleanist = new ArrayList<>();
        booleanist.add(true);

        //THEN
        doReturn(true).when(completedLessonService).existCompletedLesson(any(UserDto.class),any(LessonDto.class));
        when(unitService.findById(idUnit)).thenReturn(unitDto);

        assertEquals(completedLessonService.checkList(idUnit), booleanist);

    }

    @Test
    @WithAnonymousUser
    public void checkListAnonymous(){
        //GIVEN
        Long idUnit = 1L;
        LessonDto lessonDto = new LessonDto();
        List<LessonDto> lessonDtoList = new ArrayList<>();
        lessonDtoList.add(lessonDto);
        UnitDto unitDto = new UnitDto();
        unitDto.setLessons(lessonDtoList);
        ArrayList<Boolean> booleanist = new ArrayList<>();
        booleanist.add(false);

        //THEN
        when(unitService.findById(idUnit)).thenReturn(unitDto);

        assertEquals(completedLessonService.checkList(idUnit), booleanist);

    }

    @Test
    public void checkListError(){
        //GIVEN
        Long idUnit = 1L;
        Exception npe = new NullPointerException();

        //THEN
        when(unitService.findById(idUnit)).thenThrow(npe);

        assertEquals(completedLessonService.checkList(idUnit), null);

    }

    @Test
    public void numberOfCompletedLesson(){
        //GIVEN
        Long idUnit = 1L;
        List<Boolean> bList = new ArrayList<>();
        bList.add(true);
        //THEN
        doReturn(bList).when(completedLessonService).checkList(idUnit);

        assertEquals(completedLessonService.numberOfCompletedLesson(idUnit), 1);
    }

    @Test
    public void numberOfCompletedLessonError(){
        //GIVEN
        Exception npe = new NullPointerException();

        //THEN
        doThrow(npe).when(completedLessonService).checkList(null);

        assertEquals(completedLessonService.numberOfCompletedLesson(null), -1);

    }


    @Test
    public void totalNumberOfCompletedLesson(){
        //GIVEN
        Long idUnit = 1L;
        List<Boolean> bList = new ArrayList<>();
        bList.add(true);
        LessonDto lessonDto = new LessonDto();
        List<LessonDto> lessonDtoList = new ArrayList<>();
        lessonDtoList.add(lessonDto);
        UnitDto unitDto = new UnitDto();
        unitDto.setId(idUnit);
        unitDto.setLessons(lessonDtoList);
        ArrayList<UnitDto> unitDtos = new ArrayList<>();
        unitDtos.add(unitDto);
        //THEN

        when(unitService.findAll()).thenReturn(unitDtos);
        doReturn(bList).when(completedLessonService).checkList(idUnit);

        assertEquals(completedLessonService.numberOfCompletedLesson(idUnit), 1);
    }

    @Test
    public void totalNumberOfCompletedLessonError(){
        //GIVEN
        Exception npe = new NullPointerException();

        //THEN
        doThrow(npe).when(unitService).findAll();

        assertEquals(completedLessonService.numberOfCompletedLesson(null), -1);

    }




}
