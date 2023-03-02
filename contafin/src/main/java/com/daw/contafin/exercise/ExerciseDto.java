package com.daw.contafin.exercise;

import com.daw.contafin.answer.AnswerDto;
import com.daw.contafin.completedExercise.CompletedExerciseDto;
import com.daw.contafin.lesson.LessonDto;
import lombok.Data;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Data
public class ExerciseDto implements Serializable {

    private long id;
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

    @Override
    public String toString() {
        return "ExerciseDto [id=" + id + ", kind=" + kind + ", statement=" + statement + ", texts=" + texts + ", answer=" + answer + "]";
    }
}
