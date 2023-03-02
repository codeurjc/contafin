package com.daw.contafin.answer;

import com.daw.contafin.exercise.Exercise;
import com.daw.contafin.exercise.ExerciseDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

@Data
public class AnswerDto implements Serializable {

    private long id;
    private String result;

    public AnswerDto() {}
    public AnswerDto(String result) {

        this.result=result;

    }
}
