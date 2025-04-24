package com.daw.contafin.service;

import com.daw.contafin.dto.UserDto;
import com.daw.contafin.service.ExcelService;
import com.daw.contafin.service.UserService;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

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

        assertTrue(SXSSFWorkbook.class.isInstance(excelService.generateExcel()));
    }

}
