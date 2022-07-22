package com.daw.contafin.completedLesson;


import com.daw.contafin.lesson.LessonDto;
import com.daw.contafin.user.UserDto;
import lombok.Data;
import java.io.Serializable;
import java.sql.Date;

@Data
public class CompletedLessonDto implements Serializable {

    private long id;
    private UserDto user;
    private LessonDto lesson;
    private Date date;

    public CompletedLessonDto() {

    }
    public CompletedLessonDto(UserDto user, LessonDto lesson, Date date) {
        this.user = user;
        this.lesson = lesson;
        this.date = date;
    }
}
