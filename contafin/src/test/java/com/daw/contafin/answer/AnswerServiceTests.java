package com.daw.contafin.answer;

import com.daw.contafin.ImageService;
import com.daw.contafin.exercise.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

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
        Long id = 2L;
        AnswerDto answerDto = new AnswerDto();
        Answer answer = new Answer();

        //THEN
        when(answerRepository.findById(id)).thenReturn(Optional.of(answer));
        when(answerMapper.AnswerToAnswerDto(answer)).thenReturn(answerDto);

        answerService.findById(id);

    }

    @Test
    public void findByIdError(){
        //GIVEN
        Long id = 2L;
        Exception npe = new NullPointerException();

        //THEN
        when(answerRepository.findById(id)).thenReturn(null);
        when(answerMapper.AnswerToAnswerDto(null)).thenThrow(npe);

        answerService.findById(id);
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

        answerService.save(answerDto);

    }

    @Test
    public void saveError(){
        //GIVEN
        Exception npe = new NullPointerException();
        //THEN
        when(answerMapper.AnswerToAnswerDto(null)).thenThrow(npe);

        answerService.save(null);

    }




}
