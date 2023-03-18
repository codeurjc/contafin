package com.daw.contafin;

import com.daw.contafin.completedLesson.CompletedLessonDto;
import com.daw.contafin.completedLesson.CompletedLessonService;
import com.daw.contafin.lesson.LessonDto;
import com.daw.contafin.lesson.LessonService;
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
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ExcelServiceTests {

    @InjectMocks
    @Spy
    ExcelService excelService;

    @Mock
    UserService userService;


    @Test
    public void generateExcel(){
        //GIVEN
        UserDto userDto = new UserDto();
        List<UserDto> userDtoList = new ArrayList<>();
        userDtoList.add(userDto);

        //THEN
        when(userService.getUsers()).thenReturn(userDtoList);

        excelService.generateExcel();

    }

}
