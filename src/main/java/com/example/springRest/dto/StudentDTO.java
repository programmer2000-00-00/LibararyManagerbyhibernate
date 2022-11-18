package com.example.springRest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentDTO {
    private Integer id;
    private String name;
    private String surname;
    private String phone;
    private LocalDate createdDate;
}

