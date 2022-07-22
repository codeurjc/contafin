package com.daw.contafin.unit;

import com.daw.contafin.lesson.Lesson;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UnitDto implements Serializable {

    private long id;
    private String name;
    private List<Lesson> lessons;

    public UnitDto() {
    }

    public UnitDto(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Unit [id=" + id + ", name=" + name + "]";
    }
}
