package com.daw.contafin.exercise;

import com.daw.contafin.answer.Answer;
import com.daw.contafin.answer.AnswerDto;
import com.daw.contafin.completedExercise.CompletedExerciseService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ExerciseTests {

    @Test
    public void ExerciseTest(){
        Exercise e = new Exercise();
        e.setId(1L);
        e.setAnswer(new Answer());
        e.setImage1(new byte[1]);
        e.setImage2(new byte[1]);
        e.setImage3(new byte[1]);
        e.setKind(1);
        e.setStatement("");
        e.setTexts(new ArrayList<>());

        e.toString();
    }
}
