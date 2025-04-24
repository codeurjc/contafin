package com.daw.contafin.dto;

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
