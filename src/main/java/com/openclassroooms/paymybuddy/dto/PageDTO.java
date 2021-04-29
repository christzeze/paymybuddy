package com.openclassroooms.paymybuddy.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
public class PageDTO {
    @Min(0)
    private int page = 0;

    @Min(5)
    @Max(100)
    private int size = 5;
}
