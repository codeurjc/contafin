package com.daw.contafin.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Answer {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private long id;
    private String result;


    public Answer() {}
    public Answer(String result) {

        this.result=result;

    }
}
