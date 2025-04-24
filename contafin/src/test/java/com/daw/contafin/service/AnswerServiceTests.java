package com.daw.contafin.service;

import com.daw.contafin.dto.AnswerDto;
import com.daw.contafin.entity.Answer;
import com.daw.contafin.mapper.AnswerMapper;
import com.daw.contafin.repository.AnswerRepository;
import com.daw.contafin.service.AnswerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AnswerServiceTests {

    @InjectMocks
    AnswerService answerService;

    @Mock
    AnswerRepository answerRepository;

    @Mock
    AnswerMapper answerMapper;

    @Test
    public void findById(){
        //GIVEN
        long id = 2L;
        AnswerDto answerDto = new AnswerDto();
        Answer answer = new Answer();

        //THEN
        when(answerRepository.findById(id)).thenReturn(answer);
        when(answerMapper.AnswerToAnswerDto(answer)).thenReturn(answerDto);

        assertEquals(answerService.findById(id),answerDto);

    }

    @Test
    public void findByIdError(){
        //GIVEN
        long id = 2L;
        Exception npe = new NullPointerException();

        //THEN
        when(answerRepository.findById(id)).thenReturn(null);
        when(answerMapper.AnswerToAnswerDto(null)).thenThrow(npe);

        assertEquals(answerService.findById(id),null);
    }

    @Test
    public void save(){
        //GIVEN
        AnswerDto answerDto = new AnswerDto();
        Answer answer = new Answer();

        //THEN
        when(answerMapper.AnswerDtoToAnswer(answerDto)).thenReturn(answer);
        when(answerRepository.save(answer)).thenReturn(answer);
        when(answerMapper.AnswerToAnswerDto(answer)).thenReturn(answerDto);

        assertEquals(answerService.save(answerDto), answerDto);

    }

    @Test
    public void saveError(){
        //GIVEN
        Exception npe = new NullPointerException();
        //THEN
        when(answerMapper.AnswerToAnswerDto(null)).thenThrow(npe);

        assertEquals(answerService.save(null), null);

    }




}
