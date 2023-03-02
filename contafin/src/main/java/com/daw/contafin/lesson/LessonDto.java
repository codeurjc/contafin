package com.daw.contafin.lesson;


import com.daw.contafin.exercise.ExerciseDto;
import com.daw.contafin.unit.UnitDto;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class LessonDto implements Serializable {

    public interface LessonBasic {}
    private long id;
    private String name;
    private List<ExerciseDto> exercises;

    public LessonDto() {}
    public LessonDto(String name, List<ExerciseDto> exercises) {
        this.name=name;
        this.exercises = exercises;
    }
}
