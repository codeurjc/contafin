package com.daw.contafin.dto;


import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class LessonDto implements Serializable {

    private Long id;
    private String name;
    private List<ExerciseDto> exercises;

    public LessonDto() {}
    public LessonDto(String name, List<ExerciseDto> exercises) {
        this.name=name;
        this.exercises = exercises;
    }
}
