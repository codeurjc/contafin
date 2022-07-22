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
    private LessonDto lesson;
    private int kind;
    private String statement;
    private byte[] image1;
    private byte[] image2;
    private byte[] image3;
    private List<String> texts;
    private List<CompletedExerciseDto> completedExercises;
    private AnswerDto answer;

    public ExerciseDto() {

    }

    public ExerciseDto(int kind, String statement, List<String> texts, AnswerDto answer, LessonDto lesson) {
        this.kind = kind;
        this.statement = statement;
        this.texts = texts;
        this.setLesson(lesson);
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "Exercise [id=" + id + ", lesson=" + lesson + ", kind=" + kind + ", statement=" + statement + ", image1="
                + Arrays.toString(image1) + ", image2=" + Arrays.toString(image2) + ", image3="
                + Arrays.toString(image3) + ", texts=" + texts + ", completedExercises="
                + completedExercises + ", answer=" + answer + "]";
    }
}
