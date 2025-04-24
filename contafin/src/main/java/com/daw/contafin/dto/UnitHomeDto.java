package com.daw.contafin.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UnitHomeDto implements Serializable {

    private Long unitId;
    private int nCompleted;

}
