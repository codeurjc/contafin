package com.daw.contafin;

import com.daw.contafin.entity.Answer;
import com.daw.contafin.entity.Exercise;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
