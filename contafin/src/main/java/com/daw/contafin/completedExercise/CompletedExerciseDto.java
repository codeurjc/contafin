package com.daw.contafin.completedExercise;


import com.daw.contafin.exercise.ExerciseDto;
import com.daw.contafin.user.UserDto;
import lombok.Data;

import java.io.Serializable;

@Data
public class CompletedExerciseDto implements Serializable {

    private long id;
    private UserDto user;
    private ExerciseDto exercise;
    private long error;

    public CompletedExerciseDto() {

    }
    public CompletedExerciseDto(UserDto user, ExerciseDto exercise, long error) {
        this.user=user;
        this.error=error;
        this.exercise=exercise;
    }
}
