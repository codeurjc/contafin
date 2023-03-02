package com.daw.contafin.user;

import com.daw.contafin.ImageService;
import com.daw.contafin.answer.AnswerService;
import com.daw.contafin.completedLesson.CompletedLessonDto;
import com.daw.contafin.completedLesson.CompletedLessonService;
import com.daw.contafin.exercise.ExerciseDto;
import com.daw.contafin.lesson.Lesson;
import com.daw.contafin.lesson.LessonDto;
import com.daw.contafin.lesson.LessonService;
import com.daw.contafin.unit.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserServiceTests {

    @InjectMocks
    @Spy
    UserService userService;

    @Mock
    UserRepository userRepository;

    @Mock
    UserComponent userComponent;

    @Mock
    CompletedLessonService completedLessonService;

    @Mock
    UserMapper userMapper;

    @Mock
    LessonService lessonService;

    @Mock
    ImageService imageService;

    @Mock
    UnitService unitService;


    @Test
    public void findById(){
        //GIVEN
        Long id = 2L;
        UserDto userDto = new UserDto();
        User user = new User();

        //THEN
        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        when(userMapper.UserToUserDto(user)).thenReturn(userDto);

        userService.findById(id);

    }

    @Test
    public void findByIdError(){
        //GIVEN
        Long id = 2L;
        Exception npe = new NullPointerException();

        //THEN
        when(userRepository.findById(id)).thenReturn(null);
        when(userMapper.UserToUserDto(null)).thenThrow(npe);

        userService.findById(id);
    }

    @Test
    public void findByEmail(){
        //GIVEN
        String s = "1234";
        UserDto userDto = new UserDto();
        User user = new User();

        //THEN
        when(userRepository.findByEmail(s)).thenReturn(user);
        when(userMapper.UserToUserDto(user)).thenReturn(userDto);

        userService.findByEmail(s);

    }

    @Test
    public void findByEmailError(){
        //GIVEN
        String s = "1234";
        Exception npe = new NullPointerException();

        //THEN
        when(userRepository.findByEmail(s)).thenReturn(null);
        when(userMapper.UserToUserDto(null)).thenThrow(npe);

        userService.findByEmail(s);
    }

    @Test
    public void getUsers(){
        //GIVEN
        List<User> userList = new ArrayList<>();
        List<UserDto> userDtoList = new ArrayList<>();

        //THEN
        when(userRepository.findAll()).thenReturn(userList);
        when(userMapper.UsersToUsersDto(userList)).thenReturn(userDtoList);

        userService.getUsers();

    }

    @Test
    public void getUsersError(){
        //GIVEN
        Exception npe = new NullPointerException();

        //THEN
        when(userRepository.findAll()).thenThrow(npe);

        userService.getUsers();

    }

    @Test
    public void save(){
        //GIVEN
        UserDto userDto = new UserDto();
        User user = new User();

        //THEN
        when(userMapper.UserDtoToUser(userDto)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);

        userService.save(userDto);

    }

    @Test
    public void saveError(){
        //GIVEN
        Exception npe = new NullPointerException();
        //THEN
        when(userMapper.UserDtoToUser(null)).thenThrow(npe);

        userService.save(null);

    }

    @Test
    public void updateUser(){
        //GIVEN
        UserDto userDto = new UserDto();
        User user = new User();

        //THEN
        when(userRepository.findById(userDto.getId())).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);

        userService.updateUser(userDto);

    }

    @Test
    public void updateUserError(){
        //GIVEN
        UserDto userDto = new UserDto();
        Exception npe = new NullPointerException();
        //THEN
        when(userRepository.findById(userDto.getId())).thenThrow(npe);

        userService.updateUser(userDto);

    }

    @Test
    public void checkPass(){
        //GIVEN
        Long id = 1L;
        UserDto userDto = new UserDto();
        userDto.setPasswordHash(new BCryptPasswordEncoder().encode("1234"));
        //THEN
        when(userService.findById(id)).thenReturn(userDto);

        userService.checkPass(id,"1234");
        userService.checkPass(id,"1");

    }

    @Test
    public void checkPassError(){
        //GIVEN
        Long id = 1L;
        Exception npe = new RuntimeException();
        //THEN
        Mockito.doThrow(npe).when(userService).findById(id);

        userService.checkPass(id,"1234");

    }

    @Test
    public void deleteAccount(){
        //GIVEN
        Long id = 1L;
        UserDto userDto = new UserDto();
        User user = new User();
        //THEN
        when(userService.findById(id)).thenReturn(userDto);
        when(userMapper.UserDtoToUser(userDto)).thenReturn(user);
        Mockito.doNothing().when(userRepository).delete(user);

        userService.deleteAccount(id);

    }

    @Test
    public void deleteAccountError(){
        //GIVEN
        Long id = 1L;
        Exception npe = new NullPointerException();
        //THEN
        Mockito.doThrow(npe).when(userService).findById(id);

        userService.deleteAccount(id);

    }


    @Test
    public void getProfile(){
        //GIVEN
        Long id = 1L;
        UserDto userDto = new UserDto();
        User user = new User();
        //THEN
        when(userService.findById(id)).thenReturn(userDto);
        when(userMapper.UserDtoToUser(userDto)).thenReturn(user);
        Mockito.doNothing().when(userRepository).delete(user);

        userService.getProfile(id);

    }

    @Test
    public void getProfileError(){
        //GIVEN
        Long id = 1L;
        Exception npe = new NullPointerException();
        //THEN
        Mockito.doThrow(npe).when(userService).findById(id);

        userService.getProfile(id);

    }

    @Test
    public void updatePhoto() throws IOException {
        //GIVEN
        Long id = 1L;
        byte[] bytes = new byte[1];
        MultipartFile file = new MockMultipartFile("Hola", bytes);


        UserDto userDto = new UserDto();
        User user = new User();
        //THEN
        Mockito.doReturn(userDto).when(userService).findById(id);
        when(imageService.uploadImage(file)).thenReturn(bytes);
        Mockito.doReturn(userDto).when(userService).updateUser(userDto);

        userService.updatePhoto(id, file);

    }

    @Test
    public void updatePhotoError(){
        //GIVEN
        Long id = 1L;
        byte[] bytes = new byte[1];
        MultipartFile file = new MockMultipartFile("Hola", bytes);
        Exception npe = new NullPointerException();
        //THEN
        Mockito.doThrow(npe).when(userService).findById(id);

        userService.updatePhoto(id, file);
    }

    @Test
    public void progress(){
        //GIVEN
        Long id = 1L;

        UserDto userDto = new UserDto();
        //THEN
        Mockito.doReturn(userDto).when(userService).findById(id);
        when(completedLessonService.getCompletedLessons(Mockito.any(), Mockito.any())).thenReturn(4);
        Mockito.doReturn(userDto).when(userService).updateUser(Mockito.any());

        userService.progress(id);

    }

    @Test
    public void progressError(){
        //GIVEN
        Long id = 1L;
        Exception npe = new NullPointerException();
        //THEN
        Mockito.doThrow(npe).when(userService).findById(id);

        userService.progress(id);
    }

    @Test
    public void getFluency(){
        //GIVEN
        UserDto userDto = new UserDto();
        List<LessonDto> lessonDtos = new ArrayList<>();
        LessonDto lessonDto = new LessonDto();
        lessonDtos.add(lessonDto);
        List<CompletedLessonDto> lessonCompletedDtos = new ArrayList<>();
        CompletedLessonDto completedLessonDto = new CompletedLessonDto();
        lessonCompletedDtos.add(completedLessonDto);
        //THEN
        when(lessonService.findAll()).thenReturn(lessonDtos);
        when(completedLessonService.findByUser(userDto)).thenReturn(lessonCompletedDtos);

        userService.getFluency(userDto);
    }

    @Test
    public void getFluencyError(){
        //GIVEN
        UserDto userDto = new UserDto();
        Exception npe = new NullPointerException();
        //THEN
        Mockito.doThrow(npe).when(lessonService).findAll();

        userService.getFluency(userDto);
    }

    @Test
    public void getRemainingGoals(){
        //GIVEN
        UserDto userDto = new UserDto();
        userDto.setDailyGoal(7);
        UserDto userDto2 = new UserDto();
        userDto2.setDailyGoal(1);

        //THEN
        when(completedLessonService.getCompletedLessons(Mockito.any(),Mockito.any())).thenReturn(4);

        userService.getRemainingGoals(userDto);
        userService.getRemainingGoals(userDto2);
    }

    @Test
    public void getRemainingGoalsError(){
        //GIVEN
        UserDto userDto = new UserDto();
        Exception npe = new NullPointerException();
        //THEN
        Mockito.doThrow(npe).when(completedLessonService).getCompletedLessons(Mockito.any(),Mockito.any());

        userService.getRemainingGoals(userDto);
    }


    @Test
    public void completedLesson(){
        //GIVEN
        Long id = 1L;
        LessonDto lessonDto = new LessonDto();
        CompletedLessonDto completedLessonDto = new CompletedLessonDto();
        UserDto userDto = new UserDto();

        //THEN
        when(lessonService.findById(id)).thenReturn(lessonDto);
        Mockito.doNothing().when(completedLessonService).save(completedLessonDto);
        when(userComponent.isLoggedUser()).thenReturn(true);
        Mockito.doNothing().when(userService).updateUserData(Mockito.any(), Mockito.any());

        userService.completedLesson(userDto,id);
    }

    @Test
    public void completedLessonError(){
        //GIVEN
        Long id = 1L;
        UserDto userDto = new UserDto();
        Exception npe = new NullPointerException();
        //THEN
        Mockito.doThrow(npe).when(lessonService).findById(id);

        userService.completedLesson(userDto,id);
    }

    @Test
    public void updateUserData(){
        //GIVEN
        Long id = 1L;
        LessonDto lessonDto = new LessonDto();
        List<CompletedLessonDto> completedLessonDtoList = new ArrayList<>();
        CompletedLessonDto completedLessonDto = new CompletedLessonDto();
        completedLessonDto.setLesson(lessonDto);
        completedLessonDtoList.add(completedLessonDto);
        UserDto userDto = new UserDto();
        UnitDto unitDto = new UnitDto();
        unitDto.setId(1L);


        //THEN
        Mockito.doReturn(completedLessonDtoList).when(completedLessonService).findByUserOrderByDateDesc(userDto);
        Mockito.doReturn(4).when(userService).getFluency(userDto);
        Mockito.doReturn(4).when(completedLessonService).getCompletedLessons(Mockito.any(), Mockito.any());
        Mockito.doReturn(4).when(userService).getRemainingGoals(userDto);
        Mockito.doReturn(unitDto).when(unitService).findByLessonsId(completedLessonDto.getLesson().getId());
        Mockito.doReturn(userDto).when(userService).updateUser(userDto);


        userService.updateUserData(userDto, new Date());

    }

    @Test
    public void updateUserDataError(){
        //GIVEN
        UserDto userDto = new UserDto();
        Exception npe = new NullPointerException();
        //THEN
        Mockito.doThrow(npe).when(completedLessonService).findByUserOrderByDateDesc(userDto);

        userService.updateUserData(userDto,new Date());
    }
}
