package com.daw.contafin.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserConfigDto implements Serializable {
    private String name;
    private String email;
    private String pass;
    private String oldpass;
    private byte[] file;
}
