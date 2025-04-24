package com.daw.contafin.service;

import com.daw.contafin.SpringSecurityForUserControllerImplTestConfig;
import com.daw.contafin.config.jwt.JwtUtils;
import com.daw.contafin.dto.CompletedLessonDto;
import com.daw.contafin.dto.LessonDto;
import com.daw.contafin.dto.UnitDto;
import com.daw.contafin.dto.UserDto;
import com.daw.contafin.entity.User;
import com.daw.contafin.repository.UserRepository;
import com.daw.contafin.mapper.UserMapper;
import com.daw.contafin.config.jwt.UserDetailsImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoInteractions;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SpringSecurityForUserControllerImplTestConfig.class)
@EnableAutoConfiguration
public class UserServiceTests {

    @InjectMocks
    @Spy
    UserService userService;

    @Mock
    UserRepository userRepository;

    @Mock
    CompletedLessonService completedLessonService;

    @Mock
    UserMapper userMapper;

    @Mock
    LessonService lessonService;

    @Mock
    UnitService unitService;

    @Mock
    JwtUtils jwtUtils;

    @Mock
    AuthenticationManager authenticationManager;


    @Test
    public void findById(){
        //GIVEN
        long id = 2L;
        UserDto userDto = new UserDto();
        User user = new User();

        //THEN
        when(userRepository.findById(id)).thenReturn(user);
        when(userMapper.UserToUserDto(user)).thenReturn(userDto);

        assertEquals(userService.findById(id), userDto);

    }

    @Test
    public void findByIdError(){
        //GIVEN
        long id = 2L;
        Exception npe = new NullPointerException();

        //THEN
        when(userRepository.findById(id)).thenReturn(null);
        when(userMapper.UserToUserDto(null)).thenThrow(npe);

        assertEquals(userService.findById(id), null);
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

        assertEquals(userService.findByEmail(s), userDto);

    }

    @Test
    public void findByEmailError(){
        //GIVEN
        String s = "1234";
        Exception npe = new NullPointerException();

        //THEN
        when(userRepository.findByEmail(s)).thenReturn(null);
        when(userMapper.UserToUserDto(null)).thenThrow(npe);

        assertEquals(userService.findByEmail(s), null);
    }

    @Test
    public void getUsers(){
        //GIVEN
        List<User> userList = new ArrayList<>();
        List<UserDto> userDtoList = new ArrayList<>();

        //THEN
        when(userRepository.findAll()).thenReturn(userList);
        when(userMapper.UsersToUsersDto(userList)).thenReturn(userDtoList);

        assertEquals(userService.getUsers(), userDtoList);

    }

    @Test
    public void getUsersError(){
        //GIVEN
        Exception npe = new NullPointerException();

        //THEN
        when(userRepository.findAll()).thenThrow(npe);

        assertEquals(userService.getUsers(), null);

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
        verify(userRepository).save(same(user));
    }

    @Test
    public void saveError(){
        //GIVEN
        Exception npe = new NullPointerException();
        //THEN
        when(userMapper.UserDtoToUser(null)).thenThrow(npe);

        userService.save(null);
        verifyNoInteractions(userRepository);
    }

    @Test
    public void updateUser(){
        //GIVEN
        UserDto userDto = new UserDto();
        userDto.setId(1L);
        User user = new User();

        //THEN
        when(userRepository.findById(userDto.getId())).thenReturn(user);
        when(userMapper.UserDtoToUser(userDto)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);

        assertEquals(userService.updateUser(userDto), userDto);

    }

    @Test
    public void updateUserError(){
        //GIVEN
        UserDto userDto = new UserDto();
        Exception npe = new NullPointerException();
        //THEN
        when(userRepository.findById(userDto.getId())).thenThrow(npe);

        assertEquals(userService.updateUser(userDto), null);

    }

    @Test
    public void checkPass(){
        //GIVEN
        Long id = 1L;
        String genericString = "1234";
        UserDto userDto = new UserDto();
        userDto.setName(genericString);
        userDto.setEmail(genericString);
        userDto.setPasswordHash(new BCryptPasswordEncoder().encode(genericString));
        //THEN
        when(userService.findById(id)).thenReturn(userDto);
        Mockito.doNothing().when(userService).save(Mockito.any());

        assertEquals(userService.checkPass(id,genericString,genericString,genericString,genericString,new byte[2]), userDto);
        assertNull(userService.checkPass(id,"12",genericString,genericString,genericString,new byte[2]));

    }

    @Test
    public void checkPassError(){
        //GIVEN
        Long id = 1L;
        Exception npe = new RuntimeException();
        String genericString = "1234";
        //THEN
        Mockito.doThrow(npe).when(userService).findById(id);

        assertEquals(userService.checkPass(id,genericString,genericString,genericString,genericString,new byte[2]), null);

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

        verify(userRepository).delete(same(user));

    }

    @Test
    public void deleteAccountError(){
        //GIVEN
        Long id = 1L;
        Exception npe = new NullPointerException();
        //THEN
        Mockito.doThrow(npe).when(userService).findById(id);

        userService.deleteAccount(id);
        verifyNoInteractions(userRepository);

    }


    @Test
    public void getProfile(){
        //GIVEN
        Long id = 1L;
        UserDto userDto = new UserDto();
        User user = new User();
        //THEN
        when(userService.findById(id)).thenReturn(userDto);

        assertEquals(userService.getProfile(id), userDto);

    }

    @Test
    public void getProfileError(){
        //GIVEN
        long id = 1L;
        Exception npe = new NullPointerException();
        //THEN
        Mockito.doThrow(npe).when(userService).findById(id);

        assertEquals(userService.getProfile(id), null);

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

        assertEquals(userService.progress(id), null);
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
        when(completedLessonService.totalNumberOfCompletedLesson()).thenReturn(1);

        assertEquals(userService.getFluency(),100);
    }

    @Test
    public void getFluencyError(){
        //GIVEN
        UserDto userDto = new UserDto();
        Exception npe = new NullPointerException();
        //THEN
        Mockito.doThrow(npe).when(lessonService).findAll();

        assertEquals(userService.getFluency(), -1);
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

        assertEquals(userService.getRemainingGoals(userDto),3);
        assertEquals(userService.getRemainingGoals(userDto2), 0);
    }

    @Test
    public void getRemainingGoalsError(){
        //GIVEN
        UserDto userDto = new UserDto();
        Exception npe = new NullPointerException();
        //THEN
        Mockito.doThrow(npe).when(completedLessonService).getCompletedLessons(Mockito.any(),Mockito.any());

        assertEquals(userService.getRemainingGoals(userDto), -1);
    }


    @Test
    @WithMockUser(username = "admin", authorities = { "ADMIN", "USER" })
    public void completedLesson(){
        //GIVEN
        long id = 1L;
        LessonDto lessonDto = new LessonDto();
        CompletedLessonDto completedLessonDto = new CompletedLessonDto();
        UserDto userDto = new UserDto();

        //THEN
        Mockito.doReturn(userDto).when(userService).findById(id);
        when(lessonService.findById(id)).thenReturn(lessonDto);
        Mockito.doNothing().when(completedLessonService).save(Mockito.any());
        Mockito.doNothing().when(userService).updateUserData(userDto, true, 10);

        userService.completedLesson(userDto,id,10);
        verify(completedLessonService).save(Mockito.any());
    }

    @Test
    @WithAnonymousUser
    public void completedLessonAnonymous(){
        //GIVEN
        long id = 1L;
        UserDto userDto = new UserDto();
        Exception npe = new NullPointerException();
        //THEN
        Mockito.doThrow(npe).when(lessonService).findById(id);

        userService.completedLesson(userDto,id,10);
        verifyNoInteractions(completedLessonService);
    }

    @Test
    public void completedLessonError(){
        //GIVEN
        long id = 1L;
        UserDto userDto = new UserDto();
        Exception npe = new NullPointerException();
        //THEN
        Mockito.doThrow(npe).when(lessonService).findById(id);

        userService.completedLesson(userDto,id,10);
        verifyNoInteractions(completedLessonService);
    }

    @Test
    public void updateUserData(){
        //GIVEN
        Long id = 1L;
        LessonDto lessonDto = new LessonDto();
        lessonDto.setId(id);
        List<CompletedLessonDto> completedLessonDtoList = new ArrayList<>();
        CompletedLessonDto completedLessonDto = new CompletedLessonDto();
        completedLessonDto.setLesson(lessonDto);
        completedLessonDtoList.add(completedLessonDto);
        UserDto userDto = new UserDto();
        userDto.setPoints(10);
        UnitDto unitDto = new UnitDto();
        unitDto.setId(1L);


        //THEN
        Mockito.doReturn(completedLessonDtoList).when(completedLessonService).findByUserOrderByDateDesc(Mockito.any());
        Mockito.doReturn(4).when(userService).getFluency();
        Mockito.doReturn(new int[2]).when(userService).progress(Mockito.any());
        Mockito.doReturn(4).when(completedLessonService).getCompletedLessons(Mockito.any(UserDto.class), Mockito.any());
        Mockito.doReturn(4).when(userService).getRemainingGoals(Mockito.any(UserDto.class));
        Mockito.doReturn(unitDto).when(unitService).findByLessonsId(id);
        Mockito.doReturn(userDto).when(userService).updateUser(Mockito.any(UserDto.class));
        Mockito.doNothing().when(userService).updateStreak(Mockito.any(UserDto.class),Mockito.anyInt());
        Mockito.doReturn(20).when(userService).updatePoints(10,10);

        userService.updateUserData(userDto, true, 10);

        verify(userService).updateUser(Mockito.any());

    }

    @Test
    public void updateUserDataError(){
        //GIVEN
        UserDto userDto = new UserDto();
        Exception npe = new NullPointerException();
        //THEN
        Mockito.doThrow(npe).when(completedLessonService).findByUserOrderByDateDesc(Mockito.any());

        userService.updateUserData(userDto, true, 10);
        verifyNoInteractions(unitService);
    }

    @Test
    public void updatePoints(){
        //THEN
        assertEquals(userService.updatePoints(10,-7),3);
        assertEquals(userService.updatePoints(3,-7),0);
    }

    @Test
    @WithUserDetails("username")
    public void lessonComplete(){
        //GIVEN
        UserDto userDto = new UserDto();
        userDto.setId(1L);
        //THEN
        Mockito.doReturn(userDto).when(userService).findById(1L);
        Mockito.doNothing().when(userService).completedLesson(userDto,1L,10);

        assertEquals(userService.lessonComplete(1L,10),userDto);
    }

    @Test
    public void lessonCompleteError(){
        //GIVEN
        Exception npe = new NullPointerException();
        //THEN
        Mockito.doThrow(npe).when(userService).findById(1L);

        assertEquals(userService.lessonComplete(1L,10), null);
    }

    @Test
    public void updateStreak(){
        //GIVEN
        UserDto userDto = new UserDto();
        userDto.setId(1L);
        userDto.setDailyGoal(3);

        //THEN

        userService.updateStreak(userDto,10);
        verify(completedLessonService).getCompletedLessons(any(UserDto.class),any(Date.class));
    }

    @Test
    public void updateStreakError(){
        //GIVEN
        Exception npe = new NullPointerException();
        UserDto userDto = new UserDto();
        userDto.setId(1L);
        userDto.setDailyGoal(3);
        //THEN

        Mockito.doThrow(npe).when(completedLessonService).getCompletedLessons(any(UserDto.class),any(Date.class));

        try{
            userService.updateStreak(userDto,10);
            fail();
        }catch (Exception e){

        }
    }

    @Test
    @WithUserDetails(value="customUsername", userDetailsServiceBeanName="userDetailsService")
    public void createAuthentication(){
        //GIVEN

        List<String> list = new ArrayList<>();
        list.add("ADMIN");
        list.add("USER");

        when(jwtUtils.generateJwtToken(any(Authentication.class))).thenReturn("2");
        when(authenticationManager.authenticate(any(Authentication.class))).thenReturn(SecurityContextHolder.getContext().getAuthentication());


        Map<String,Object> map = userService.createAuthentication("username@example.com","password");

        assertEquals(map.get("token"),"2");
        assertEquals(map.get("userDetails"),(UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        assertEquals(map.get("roles"),list);
    }

    @Test
    public void createAuthenticationError(){
        //GIVEN
        Exception npe = new NullPointerException();
        UserDto userDto = new UserDto();
        userDto.setId(1L);
        userDto.setDailyGoal(3);
        //THEN

        Mockito.doThrow(npe).when(jwtUtils).generateJwtToken(any(Authentication.class));

        try{
            userService.createAuthentication("username@example.com","password");
            fail();
        }catch (Exception e){

        }
    }

}
