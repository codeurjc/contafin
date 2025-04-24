package com.daw.contafin.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ExerciseDto implements Serializable {

    private Long id;
    private int kind;
    private String statement;
    private byte[] image1;
    private byte[] image2;
    private byte[] image3;
    private List<String> texts;
    private AnswerDto answer;

    public ExerciseDto() {

    }

    public ExerciseDto(int kind, String statement, List<String> texts, AnswerDto answer) {
        this.kind = kind;
        this.statement = statement;
        this.texts = texts;
        this.answer = answer;
    }
}
