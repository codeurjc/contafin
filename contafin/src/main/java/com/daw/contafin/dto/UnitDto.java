package com.daw.contafin.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UnitDto implements Serializable {

    private long id;
    private String name;
    private List<LessonDto> lessons;


    @Override
    public String toString() {
        return "Unit [id=" + id + ", name=" + name + "]";
    }
}
